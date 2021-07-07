// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.spring.integration.servicebus.factory;


import com.azure.core.amqp.AmqpTransportType;
import com.azure.messaging.servicebus.ServiceBusErrorContext;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;
import com.azure.messaging.servicebus.models.ServiceBusReceiveMode;
import com.azure.messaging.servicebus.models.SubQueue;
import com.azure.spring.integration.servicebus.ServiceBusClientConfig;
import com.azure.spring.integration.servicebus.ServiceBusMessageProcessor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Default implementation of {@link AbstractServiceBusQueueClientFactory}. Client will be cached to improve performance
 *
 * @author Warren Zhu
 */
public class DefaultServiceBusQueueClientFactory extends AbstractServiceBusQueueClientFactory<SubQueue> {

    private final Map<String, ServiceBusProcessorClient> processorClientMap = new ConcurrentHashMap<>();

    public DefaultServiceBusQueueClientFactory(String connectionString, AmqpTransportType amqpTransportType) {
        super(connectionString, amqpTransportType);
    }

    @Override
    public ServiceBusProcessorClient getOrCreateProcessor(
        String name,
        ServiceBusClientConfig clientConfig,
        ServiceBusMessageProcessor<ServiceBusReceivedMessageContext, ServiceBusErrorContext> messageProcessor) {
        return this.processorClientMap.computeIfAbsent(name,
            n -> createProcessorClient(n, clientConfig, messageProcessor));
    }

    private ServiceBusProcessorClient createProcessorClient(
        String name,
        ServiceBusClientConfig clientConfig,
        ServiceBusMessageProcessor<ServiceBusReceivedMessageContext, ServiceBusErrorContext> messageProcessor) {
        if (clientConfig.isSessionsEnabled()) {
            return serviceBusClientBuilder.sessionProcessor()
                                          .queueName(name)
                                          .receiveMode(ServiceBusReceiveMode.PEEK_LOCK)
                                          .maxConcurrentCalls(1)
                                          // TODO, make it a constant or get it from clientConfig. And it looks like
                                          //  max auto renew duration is not exposed
                                          .maxConcurrentSessions(clientConfig.getConcurrency())
                                          .prefetchCount(clientConfig.getPrefetchCount())
                                          .disableAutoComplete()
                                          .processMessage(messageProcessor.processMessage())
                                          .processError(messageProcessor.processError())
                                          .buildProcessorClient();

        } else {
            return serviceBusClientBuilder.processor()
                                          .queueName(name)
                                          .receiveMode(ServiceBusReceiveMode.PEEK_LOCK)
                                          .maxConcurrentCalls(clientConfig.getConcurrency())
                                          .prefetchCount(clientConfig.getPrefetchCount())
                                          .disableAutoComplete()
                                          .processMessage(messageProcessor.processMessage())
                                          .processError(messageProcessor.processError())
                                          .buildProcessorClient();
        }

    }
}
