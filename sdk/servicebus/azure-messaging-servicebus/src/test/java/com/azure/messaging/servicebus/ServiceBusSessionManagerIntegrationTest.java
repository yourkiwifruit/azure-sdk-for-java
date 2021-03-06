// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.messaging.servicebus;

import com.azure.core.util.CoreUtils;
import com.azure.core.util.logging.ClientLogger;
import com.azure.messaging.servicebus.ServiceBusClientBuilder.ServiceBusSessionReceiverClientBuilder;
import com.azure.messaging.servicebus.implementation.MessagingEntityType;
import com.azure.messaging.servicebus.models.CompleteOptions;
import com.nimbusds.jose.util.StandardCharset;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static com.azure.messaging.servicebus.TestUtils.getServiceBusMessage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Integration tests for {@link ServiceBusSessionManager}.
 */
@Tag("integration")
class ServiceBusSessionManagerIntegrationTest extends IntegrationTestBase {
    private final AtomicInteger messagesPending = new AtomicInteger();

    private ServiceBusReceiverAsyncClient receiver;
    private ServiceBusSenderAsyncClient sender;
    private ServiceBusSessionReceiverAsyncClient sessionReceiver;

    ServiceBusSessionManagerIntegrationTest() {
        super(new ClientLogger(ServiceBusSessionManagerIntegrationTest.class));
    }

    @Override
    protected void beforeTest() {
        sessionId = UUID.randomUUID().toString();
    }

    @Override
    protected void afterTest() {
        final int pending = messagesPending.get();
        logger.info("Pending messages: {}", pending);
        try {
            dispose(receiver, sender, sessionReceiver);
        } catch (Exception e) {
            logger.warning("Error occurred when draining queue.", e);
        }
    }

    @ParameterizedTest
    @MethodSource("com.azure.messaging.servicebus.IntegrationTestBase#messagingEntityProvider")
    void singleUnnamedSession(MessagingEntityType entityType) {
        // Arrange
        final int entityIndex = TestUtils.USE_CASE_SINGLE_SESSION;
        final String messageId = "singleUnnamedSession";
        final String contents = "Some-contents";
        final int numberToSend = 5;

        setSender(entityType, entityIndex);
        final Disposable subscription = Flux.interval(Duration.ofMillis(500))
            .take(numberToSend)
            .flatMap(index -> {
                final ServiceBusMessage message = getServiceBusMessage(contents, messageId)
                    .setSessionId(sessionId);
                messagesPending.incrementAndGet();
                return sender.sendMessage(message).thenReturn(index);
            })
            .subscribe(
                number -> logger.info("sessionId[{}] sent[{}] Message sent.", sessionId, number),
                error -> logger.error("sessionId[{}] Error encountered.", sessionId, error),
                () -> logger.info("sessionId[{}] Finished sending.", sessionId));

        setReceiver(entityType, entityIndex, Function.identity());

        // Act & Assert
        StepVerifier.create(receiver.receiveMessages().concatMap(
            receivedMessage -> receiver.complete(receivedMessage).thenReturn(receivedMessage)
        ))
            .assertNext(serviceBusReceivedMessage ->
                assertMessageEquals(sessionId, messageId, contents, serviceBusReceivedMessage))
            .assertNext(serviceBusReceivedMessage ->
                assertMessageEquals(sessionId, messageId, contents, serviceBusReceivedMessage))
            .assertNext(serviceBusReceivedMessage ->
                assertMessageEquals(sessionId, messageId, contents, serviceBusReceivedMessage))
            .assertNext(serviceBusReceivedMessage ->
                assertMessageEquals(sessionId, messageId, contents, serviceBusReceivedMessage))
            .assertNext(serviceBusReceivedMessage ->
                assertMessageEquals(sessionId, messageId, contents, serviceBusReceivedMessage))
            .thenCancel()
            .verify(Duration.ofMinutes(2));
    }

    @Test
    void transactionQueueMessageSendTest() throws InterruptedException {
        // Arrange
        final boolean useCredentials = false;
        String queue1 = "session-queue-1";
        String queue2 = "session-queue-2";

        final String messageId = UUID.randomUUID().toString();
        final String session1 = "1";
        final String transactionGroup = "coordinator1";

        final byte[] CONTENTS_BYTES1 = "Some-contents 1".getBytes(StandardCharsets.UTF_8);
        final byte[] CONTENTS_BYTES2 = "Some-contents 2".getBytes(StandardCharsets.UTF_8);
        final ServiceBusMessage message1 = getServiceBusMessage(CONTENTS_BYTES1, messageId).setSessionId(session1);
        final ServiceBusMessage message2 = getServiceBusMessage(CONTENTS_BYTES2, messageId).setSessionId(session1);

        final List<ServiceBusMessage> messages1 = Collections.singletonList(message1);
        final List<ServiceBusMessage> messages2 = Collections.singletonList(message2);

        ServiceBusClientBuilder builder = getBuilder(useCredentials);

        final ServiceBusSenderAsyncClient destination1_Sender = builder
            .sender()
            .transactionGroup(transactionGroup)
            .queueName(queue1)
            .buildAsyncClient();

        final ServiceBusSenderAsyncClient destination2_Sender = builder
            .sender()
            .transactionGroup(transactionGroup)
            .queueName(queue2)
            .buildAsyncClient();

        final ServiceBusReceiverAsyncClient destination1_receiver = builder
            .sessionReceiver()
            .transactionGroup(transactionGroup)
            .queueName(queue1)
            .disableAutoComplete()
            .buildAsyncClient()
            .acceptSession(session1).block();

        System.out.println("!!!! Got receiver and one session locked for " + session1);

        ServiceBusTransactionContext transactionId = destination1_Sender.createTransaction().block();

        StepVerifier.create(destination1_Sender.sendMessages(messages1, transactionId)).verifyComplete();

        destination2_Sender
            .sendMessages(messages2, transactionId)
            .block();

        destination1_receiver.receiveMessages().take(1).flatMap(message-> {
            System.out.println("!!!! Received completed message queue1, SQ " + message.getSequenceNumber());
            return destination1_receiver.complete(message, new CompleteOptions().setTransactionContext(transactionId))
                .thenReturn(message);
        }).subscribe(message -> {
            System.out.println("!!!! Test Receiver completed message queue1, SQ " + message.getSequenceNumber());
        });

        TimeUnit.SECONDS.sleep(4);

        destination1_Sender.sendMessages(messages1, transactionId)
            .then(destination1_Sender.commitTransaction(transactionId)
                .doOnSuccess(a -> {
                    System.out.println("!!!! Transaction complete " + a);
                }))
            .subscribe();

        TimeUnit.SECONDS.sleep(16);
    }

    /**
     * Sets the sender and receiver. If session is enabled, then a single-named session receiver is created.
     */
    private void setSender(MessagingEntityType entityType, int entityIndex) {

        this.sender = getSenderBuilder(false, entityType, entityIndex, true, false)
            .buildAsyncClient();
    }
    private void setReceiver(MessagingEntityType entityType, int entityIndex,
                             Function<ServiceBusSessionReceiverClientBuilder, ServiceBusSessionReceiverClientBuilder> onBuild) {
        ServiceBusSessionReceiverClientBuilder sessionBuilder = getSessionReceiverBuilder(false,
            entityType, entityIndex, false).disableAutoComplete();

        this.sessionReceiver = onBuild.apply(sessionBuilder).buildAsyncClient();
        this.receiver = this.sessionReceiver.acceptSession(sessionId).block();
    }

    private static void assertMessageEquals(String sessionId, String messageId, String contents, ServiceBusReceivedMessage message) {

        assertNotNull(message, "'message' should not be null.");

        if (!CoreUtils.isNullOrEmpty(sessionId)) {
            assertEquals(sessionId, message.getSessionId());
        }

        assertEquals(messageId, message.getMessageId());
        assertEquals(contents, message.getBody().toString());
    }
}
