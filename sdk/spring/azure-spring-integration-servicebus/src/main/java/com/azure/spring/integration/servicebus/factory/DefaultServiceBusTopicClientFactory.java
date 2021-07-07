// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.spring.integration.servicebus.factory;

import com.azure.core.amqp.AmqpTransportType;
import com.azure.messaging.servicebus.*;
import com.azure.messaging.servicebus.models.ServiceBusReceiveMode;
import com.azure.spring.cloud.context.core.util.Tuple;
import com.azure.spring.integration.servicebus.ServiceBusClientConfig;
import com.azure.spring.integration.servicebus.ServiceBusMessageProcessor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Default implementation of {@link AbstractServiceBusTopicClientFactory}. Client will be cached to improve performance
 *
 * @author Warren Zhu
 */
public class DefaultServiceBusTopicClientFactory extends AbstractServiceBusTopicClientFactory<String> {

    private final Map<Tuple<String, String>, ServiceBusProcessorClient> topicProcessorMap = new ConcurrentHashMap<>();

    public DefaultServiceBusTopicClientFactory(String connectionString, AmqpTransportType amqpTransportType) {
        super(connectionString,amqpTransportType);
    }

    @Override
    public ServiceBusProcessorClient getOrCreateProcessor(
        String topic,
        String subscription,
        ServiceBusClientConfig clientConfig,
        ServiceBusMessageProcessor<ServiceBusReceivedMessageContext, ServiceBusErrorContext> messageProcessor) {
        return this.topicProcessorMap.computeIfAbsent(Tuple.of(topic, subscription),
            t -> createProcessor(t.getFirst(), t.getSecond(), clientConfig, messageProcessor));
    }

    private ServiceBusProcessorClient createProcessor(String topic,
                                                      String subscription,
                                                      ServiceBusClientConfig config,
                                                      ServiceBusMessageProcessor<ServiceBusReceivedMessageContext,
                                                          ServiceBusErrorContext> messageProcessor) {
        if (config.isSessionsEnabled()) {
            return serviceBusClientBuilder.sessionProcessor()
                                          .topicName(topic)
                                          .subscriptionName(subscription)
                                          .receiveMode(ServiceBusReceiveMode.PEEK_LOCK)
                                          .maxConcurrentCalls(1)
                                          // TODO, make it a constant or get duration is not exposed it from
                                          //  clientConfig. And it looks like max auto renew
                                          .maxConcurrentSessions(config.getConcurrency())
                                          .prefetchCount(config.getPrefetchCount())
                                          .disableAutoComplete()
                                          .processMessage(messageProcessor.processMessage())
                                          .processError(messageProcessor.processError())
                                          .buildProcessorClient();
        } else {
            return serviceBusClientBuilder.processor()
                                          .topicName(topic)
                                          .subscriptionName(subscription)
                                          .receiveMode(ServiceBusReceiveMode.PEEK_LOCK)
                                          .maxConcurrentCalls(config.getConcurrency())
                                          .prefetchCount(config.getPrefetchCount())
                                          .disableAutoComplete()
                                          .processMessage(messageProcessor.processMessage())
                                          .processError(messageProcessor.processError())
                                          .buildProcessorClient();
        }
    }

}
