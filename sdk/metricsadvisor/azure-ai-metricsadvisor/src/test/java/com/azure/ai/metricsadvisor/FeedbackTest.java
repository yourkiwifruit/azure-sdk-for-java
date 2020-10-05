// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.ai.metricsadvisor;

import com.azure.ai.metricsadvisor.models.DimensionKey;
import com.azure.ai.metricsadvisor.models.FeedbackType;
import com.azure.ai.metricsadvisor.models.ListMetricFeedbackFilter;
import com.azure.ai.metricsadvisor.models.ListMetricFeedbackOptions;
import com.azure.ai.metricsadvisor.models.MetricFeedback;
import com.azure.ai.metricsadvisor.models.MetricsAdvisorServiceVersion;
import com.azure.core.http.HttpClient;
import com.azure.core.http.rest.PagedResponse;
import com.azure.core.http.rest.Response;
import com.azure.core.util.Context;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.azure.ai.metricsadvisor.MetricsSeriesTestBase.METRIC_ID;
import static com.azure.ai.metricsadvisor.TestUtils.DISPLAY_NAME_WITH_ARGUMENTS;
import static com.azure.ai.metricsadvisor.models.FeedbackType.ANOMALY;
import static com.azure.ai.metricsadvisor.models.FeedbackType.CHANGE_POINT;
import static com.azure.ai.metricsadvisor.models.FeedbackType.COMMENT;
import static com.azure.ai.metricsadvisor.models.FeedbackType.PERIOD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FeedbackTest extends FeedbackTestBase {
    private MetricsAdvisorClient client;

    @BeforeAll
    static void beforeAll() {
        StepVerifier.setDefaultTimeout(Duration.ofSeconds(30));
    }

    @AfterAll
    static void afterAll() {
        StepVerifier.resetDefaultTimeout();
    }

    /**
     * Verifies the result of the list metric feedback method when no options specified.
     */
    @ParameterizedTest(name = DISPLAY_NAME_WITH_ARGUMENTS)
    @MethodSource("com.azure.ai.metricsadvisor.TestUtils#getTestParameters")
    void testListMetricFeedback(HttpClient httpClient, MetricsAdvisorServiceVersion serviceVersion) {
        // Arrange
        client = getMetricsAdvisorBuilder(httpClient, serviceVersion).buildClient();

        listMetricFeedbackRunner(inputMetricFeedbackList -> {
            List<MetricFeedback> actualMetricFeedbackList = new ArrayList<>();
            List<MetricFeedback> expectedMetricFeedbackList = inputMetricFeedbackList.stream()
                .map(metricFeedback -> client.createMetricFeedback(METRIC_ID, metricFeedback))
                .collect(Collectors.toList());

            // Act & Assert
            client.listMetricFeedbacks(METRIC_ID).forEach(actualMetricFeedbackList::add);

            final List<String> expectedMetricFeedbackIdList = expectedMetricFeedbackList.stream()
                .map(MetricFeedback::getId)
                .collect(Collectors.toList());
            final List<MetricFeedback> actualList = actualMetricFeedbackList.stream()
                .filter(metricFeedback -> expectedMetricFeedbackIdList.contains(metricFeedback.getId()))
                .collect(Collectors.toList());

            assertEquals(inputMetricFeedbackList.size(), actualList.size());
            expectedMetricFeedbackList
                .sort(Comparator.comparing(metricFeedback -> metricFeedback.getFeedbackType().toString()));
            actualList.sort(Comparator.comparing(metricFeedback -> metricFeedback.getFeedbackType().toString()));

            final AtomicInteger i = new AtomicInteger(-1);

            final List<FeedbackType> metricFeedbackTypes = Arrays.asList(COMMENT, COMMENT);
            expectedMetricFeedbackList.forEach(expectedMetricFeedback ->
                validateMetricFeedbackResult(expectedMetricFeedback,
                    actualList.get(i.incrementAndGet()), metricFeedbackTypes.get(i.get())));
        });
    }

    /**
     * Verifies the result of the list metric feedback method to return only 3 results using
     * {@link ListMetricFeedbackOptions#setTop(int)}.
     */
    @ParameterizedTest(name = DISPLAY_NAME_WITH_ARGUMENTS)
    @MethodSource("com.azure.ai.metricsadvisor.TestUtils#getTestParameters")
    void testListMetricFeedbackTop3(HttpClient httpClient, MetricsAdvisorServiceVersion serviceVersion) {
        // Arrange
        client = getMetricsAdvisorBuilder(httpClient, serviceVersion).buildClient();

        // Act & Assert
        for (PagedResponse<MetricFeedback> metricFeedbackPagedResponse
            : client.listMetricFeedbacks(METRIC_ID, new ListMetricFeedbackOptions().setTop(3), Context.NONE)
                .iterableByPage()) {
            assertTrue(3 >= metricFeedbackPagedResponse.getValue().size());
        }
    }

    /**
     * Verifies the result of the list metric feedback method using skip and top options.
     */
    // TODO (savaity) Need concrete list results for testing skip
    // @ParameterizedTest(name = DISPLAY_NAME_WITH_ARGUMENTS)
    // @MethodSource("com.azure.ai.metricsadvisor.TestUtils#getTestParameters")
    // void testListMetricFeedbackSkip(HttpClient httpClient, MetricsAdvisorServiceVersion serviceVersion) {
    //     // Arrange
    //     client = getMetricsAdvisorBuilder(httpClient, serviceVersion).buildClient();
    //     final ArrayList<MetricFeedback> actualMetricFeedbackList = new ArrayList<>();
    //     final ArrayList<MetricFeedback> expectedList = new ArrayList<>();
    //
    //     client.listMetricFeedbacks().stream().iterator().forEachRemaining(expectedList::add);
    //
    //     // Act & Assert
    //     client.listMetricFeedbacks(new ListMetricFeedbackOptions().setSkip(3), Context.NONE)
    //         .stream().iterator().forEachRemaining(actualMetricFeedbackList::add);
    //
    //     assertEquals(expectedList.size() - 3, actualMetricFeedbackList.size());
    // }

    /**
     * Verifies the result of the list metric feedback method to filter results using
     * {@link ListMetricFeedbackFilter#setDimensionFilter(DimensionKey)}.
     */
    @ParameterizedTest(name = DISPLAY_NAME_WITH_ARGUMENTS)
    @MethodSource("com.azure.ai.metricsadvisor.TestUtils#getTestParameters")
    void testListMetricFeedbackFilterByDimensionFilter(HttpClient httpClient,
        MetricsAdvisorServiceVersion serviceVersion) {
        // Arrange
        client = getMetricsAdvisorBuilder(httpClient, serviceVersion).buildClient();

        // Act & Assert
        client.listMetricFeedbacks(METRIC_ID, new ListMetricFeedbackOptions()
                .setFilter(new ListMetricFeedbackFilter()
                    .setDimensionFilter(new DimensionKey(DIMENSION_FILTER))).setTop(10),
            Context.NONE)
            .stream().iterator()
            .forEachRemaining(metricFeedback ->
                metricFeedback.getDimensionFilter().asMap().keySet().stream().anyMatch(DIMENSION_FILTER::containsKey));
    }

    /**
     * Verifies the result of the list metric feedback method to filter results using
     * {@link ListMetricFeedbackFilter#setFeedbackType(FeedbackType)} .
     */
    @ParameterizedTest(name = DISPLAY_NAME_WITH_ARGUMENTS)
    @MethodSource("com.azure.ai.metricsadvisor.TestUtils#getTestParameters")
    void testListMetricFeedbackFilterByFeedbackType(HttpClient httpClient,
        MetricsAdvisorServiceVersion serviceVersion) {
        // Arrange
        client = getMetricsAdvisorBuilder(httpClient, serviceVersion).buildClient();

        // Act & Assert
        client.listMetricFeedbacks(METRIC_ID,
            new ListMetricFeedbackOptions().setFilter(new ListMetricFeedbackFilter().setFeedbackType(ANOMALY)),
            Context.NONE)
            .stream().iterator()
            .forEachRemaining(metricFeedback -> assertEquals(ANOMALY, metricFeedback.getFeedbackType()));
    }

    /**
     * Verifies the result of the list metric feedback method to filter results using
     * {@link ListMetricFeedbackFilter#setStartTime(OffsetDateTime)}.
     */
    @ParameterizedTest(name = DISPLAY_NAME_WITH_ARGUMENTS)
    @MethodSource("com.azure.ai.metricsadvisor.TestUtils#getTestParameters")
    void testListMetricFeedbackFilterStartTime(HttpClient httpClient, MetricsAdvisorServiceVersion serviceVersion) {
        // Arrange
        client = getMetricsAdvisorBuilder(httpClient, serviceVersion).buildClient();

        creatMetricFeedbackRunner(inputMetricFeedback -> {
            final MetricFeedback createdMetricFeedback = client.createMetricFeedback(METRIC_ID, inputMetricFeedback);

            // Act & Assert
            client.listMetricFeedbacks(METRIC_ID,
                new ListMetricFeedbackOptions().setFilter(new ListMetricFeedbackFilter()
                    .setStartTime(createdMetricFeedback.getCreatedTime())), Context.NONE)
                .stream().iterator().forEachRemaining(metricFeedback ->
                assertTrue(metricFeedback.getCreatedTime().isAfter(createdMetricFeedback.getCreatedTime())
                    || metricFeedback.getCreatedTime().isEqual(createdMetricFeedback.getCreatedTime())));
        }, COMMENT);
    }

    // Get Feedback

    /**
     * Verifies that an exception is thrown for null metric feedback Id parameter.
     */
    @ParameterizedTest(name = DISPLAY_NAME_WITH_ARGUMENTS)
    @MethodSource("com.azure.ai.metricsadvisor.TestUtils#getTestParameters")
    public void getMetricFeedbackNullId(HttpClient httpClient, MetricsAdvisorServiceVersion serviceVersion) {
        // Arrange
        client = getMetricsAdvisorBuilder(httpClient, serviceVersion).buildClient();

        // Act & Assert
        Exception exception = assertThrows(NullPointerException.class, () -> client.getMetricFeedback(null));
        assertEquals("'feedbackId' is required.", exception.getMessage());
    }

    /**
     * Verifies metric feedback info returned with response for a valid metric feedback Id.
     */
    @ParameterizedTest(name = DISPLAY_NAME_WITH_ARGUMENTS)
    @MethodSource("com.azure.ai.metricsadvisor.TestUtils#getTestParameters")
    public void getMetricFeedbackValidId(HttpClient httpClient, MetricsAdvisorServiceVersion serviceVersion) {
        // Arrange
        client = getMetricsAdvisorBuilder(httpClient, serviceVersion).buildClient();

        // Act & Assert
        final Response<MetricFeedback> metricFeedbackResponse =
            client.getMetricFeedbackWithResponse(COMMENT_FEEDBACK_ID, Context.NONE);
        assertEquals(metricFeedbackResponse.getStatusCode(), HttpResponseStatus.OK.code());
        validateMetricFeedbackResult(getCommentFeedback(), metricFeedbackResponse.getValue(), COMMENT);

    }

    // Create metric feedback

    /**
     * Verifies valid comment metric feedback created for required metric feedback details.
     */
    @ParameterizedTest(name = DISPLAY_NAME_WITH_ARGUMENTS)
    @MethodSource("com.azure.ai.metricsadvisor.TestUtils#getTestParameters")
    public void createCommentMetricFeedback(HttpClient httpClient, MetricsAdvisorServiceVersion serviceVersion) {
        // Arrange
        client = getMetricsAdvisorBuilder(httpClient, serviceVersion).buildClient();
        creatMetricFeedbackRunner(expectedMetricFeedback -> {
            // Act & Assert
            final MetricFeedback createdMetricFeedback = client.createMetricFeedback(METRIC_ID, expectedMetricFeedback);
            validateMetricFeedbackResult(expectedMetricFeedback, createdMetricFeedback, COMMENT);
        }, COMMENT);
    }

    /**
     * Verifies valid anomaly metric feedback created for required metric feedback details.
     */
    // TODO (savaity) update this to include detection configuration
    @ParameterizedTest(name = DISPLAY_NAME_WITH_ARGUMENTS)
    @MethodSource("com.azure.ai.metricsadvisor.TestUtils#getTestParameters")
    public void createAnomalyFeedback(HttpClient httpClient, MetricsAdvisorServiceVersion serviceVersion) {
        // Arrange
        client = getMetricsAdvisorBuilder(httpClient, serviceVersion).buildClient();

        creatMetricFeedbackRunner(expectedMetricFeedback -> {
            // Act & Assert
            final MetricFeedback createdMetricFeedback = client.createMetricFeedback(METRIC_ID, expectedMetricFeedback);

            validateMetricFeedbackResult(expectedMetricFeedback, createdMetricFeedback, ANOMALY);
        }, ANOMALY);
    }

    /**
     * Verifies valid period metric feedback created for required metric feedback details.
     */
    @ParameterizedTest(name = DISPLAY_NAME_WITH_ARGUMENTS)
    @MethodSource("com.azure.ai.metricsadvisor.TestUtils#getTestParameters")
    public void createPeriodMetricFeedback(HttpClient httpClient, MetricsAdvisorServiceVersion serviceVersion) {
        // Arrange
        client = getMetricsAdvisorBuilder(httpClient, serviceVersion).buildClient();

        creatMetricFeedbackRunner(expectedMetricFeedback -> {

            // Act & Assert
            final MetricFeedback createdMetricFeedback = client.createMetricFeedback(METRIC_ID, expectedMetricFeedback);
            validateMetricFeedbackResult(expectedMetricFeedback, createdMetricFeedback, PERIOD);

        }, PERIOD);
    }

    /**
     * Verifies valid change point metric feedback created for required metric feedback details.
     */
    @ParameterizedTest(name = DISPLAY_NAME_WITH_ARGUMENTS)
    @MethodSource("com.azure.ai.metricsadvisor.TestUtils#getTestParameters")
    public void createChangePointMetricFeedback(HttpClient httpClient, MetricsAdvisorServiceVersion serviceVersion) {
        // Arrange
        client = getMetricsAdvisorBuilder(httpClient, serviceVersion).buildClient();

        creatMetricFeedbackRunner(expectedMetricFeedback -> {

            // Act & Assert
            final MetricFeedback createdMetricFeedback = client.createMetricFeedback(METRIC_ID, expectedMetricFeedback);
            validateMetricFeedbackResult(expectedMetricFeedback, createdMetricFeedback, CHANGE_POINT);
        }, CHANGE_POINT);
    }
}