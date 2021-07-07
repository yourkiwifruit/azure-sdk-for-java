package com.azure.spring.integration.servicebus.factory;

import com.azure.core.amqp.AmqpTransportType;
import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusErrorContext;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;
import com.azure.messaging.servicebus.ServiceBusReceiverAsyncClient;
import com.azure.messaging.servicebus.ServiceBusReceiverClient;
import com.azure.messaging.servicebus.ServiceBusSenderAsyncClient;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.azure.messaging.servicebus.ServiceBusSessionReceiverAsyncClient;
import com.azure.messaging.servicebus.ServiceBusSessionReceiverClient;
import com.azure.spring.cloud.context.core.util.Tuple;
import com.azure.spring.integration.servicebus.ServiceBusClientConfig;
import com.azure.spring.integration.servicebus.ServiceBusMessageProcessor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractServiceBusTopicClientFactory<T extends String> extends AbstractServiceBusFactory<T> {

    protected final Map<String, ServiceBusSenderAsyncClient> topicSenderAsyncMap = new ConcurrentHashMap<>();

    protected final Map<String, ServiceBusSenderClient> topicSenderMap = new ConcurrentHashMap<>();

    protected final Map<Tuple<String, String>, ServiceBusSessionReceiverAsyncClient> topicSessionReceiverAsyncMap =
        new ConcurrentHashMap<>();

    protected final Map<Tuple<String, String>, ServiceBusSessionReceiverClient> topicSessionReceiverMap =
        new ConcurrentHashMap<>();

    protected final Map<Tuple<String, T>, ServiceBusReceiverAsyncClient> topicReceiverAsyncMap =
        new ConcurrentHashMap<>();

    protected final Map<Tuple<String, T>, ServiceBusReceiverClient> topicReceiverMap = new ConcurrentHashMap<>();

    protected final ServiceBusClientBuilder serviceBusClientBuilder;

    AbstractServiceBusTopicClientFactory(String connectionString, AmqpTransportType amqpTransportType) {
        super(connectionString);
        this.serviceBusClientBuilder = new ServiceBusClientBuilder().connectionString(connectionString);
        this.serviceBusClientBuilder.transportType(amqpTransportType);
    }

    @Override
    public ServiceBusSenderAsyncClient getOrCreateSenderAsyncClient(String name) {
        return topicSenderAsyncMap.computeIfAbsent(name, this::createSenderAsyncClient);
    }

    private ServiceBusSenderAsyncClient createSenderAsyncClient(String topicName) {
        return serviceBusClientBuilder.sender()
                                      .topicName(topicName)
                                      .buildAsyncClient();
    }

    @Override
    public ServiceBusSenderClient getOrCreateSenderClient(String name) {
        return topicSenderMap.computeIfAbsent(name, this::createSenderClient);
    }

    private ServiceBusSenderClient createSenderClient(String topicName) {
        return serviceBusClientBuilder.sender()
                                      .topicName(topicName)
                                      .buildClient();
    }

    @Override
    public ServiceBusReceiverAsyncClient getOrCreateReceiverAsyncClient(String name, T subscription) {
        return topicReceiverAsyncMap.computeIfAbsent(Tuple.of(name, subscription), this::createReceiverAsyncClient);
    }

    private ServiceBusReceiverAsyncClient createReceiverAsyncClient(Tuple<String, T> tuple) {
        return serviceBusClientBuilder.receiver()
                                      .topicName(tuple.getFirst())
                                      .subscriptionName(tuple.getSecond())
                                      .buildAsyncClient();
    }

    @Override
    public ServiceBusReceiverClient getOrCreateReceiverClient(String name, T subscription) {
        return topicReceiverMap.computeIfAbsent(Tuple.of(name, subscription), this::createReceiverClient);
    }

    private ServiceBusReceiverClient createReceiverClient(Tuple<String, T> tuple) {
        return serviceBusClientBuilder.receiver()
                                      .topicName(tuple.getFirst())
                                      .subscriptionName(tuple.getSecond())
                                      .buildClient();
    }

    @Override
    public ServiceBusSessionReceiverAsyncClient getOrCreateSessionReceiverAsyncClient(String name,
                                                                                      String subscription) {
        return topicSessionReceiverAsyncMap.computeIfAbsent(Tuple.of(name, subscription),
            this::createSessionReceiverAsyncClient);
    }

    private ServiceBusSessionReceiverAsyncClient createSessionReceiverAsyncClient(Tuple<String, String> tuple) {
        return serviceBusClientBuilder.sessionReceiver()
                                      .topicName(tuple.getFirst())
                                      .subscriptionName(tuple.getSecond())
                                      .buildAsyncClient();
    }

    @Override
    public ServiceBusSessionReceiverClient getOrCreateSessionReceiverClient(String name, String subscription) {
        return topicSessionReceiverMap.computeIfAbsent(Tuple.of(name, subscription),
            this::createSessionReceiverClient);
    }

    private ServiceBusSessionReceiverClient createSessionReceiverClient(Tuple<String, String> tuple) {
        return serviceBusClientBuilder.sessionReceiver()
                                      .topicName(tuple.getFirst())
                                      .subscriptionName(tuple.getSecond())
                                      .buildClient();
    }

    public abstract ServiceBusProcessorClient getOrCreateProcessor(String topic,
                                                   String subscription,
                                                   ServiceBusClientConfig clientConfig,
                                                   ServiceBusMessageProcessor<ServiceBusReceivedMessageContext,
                                                                                                          ServiceBusErrorContext> messageProcessor);
}
