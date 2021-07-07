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
import com.azure.messaging.servicebus.models.SubQueue;
import com.azure.spring.cloud.context.core.util.Tuple;
import com.azure.spring.integration.servicebus.ServiceBusClientConfig;
import com.azure.spring.integration.servicebus.ServiceBusMessageProcessor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractServiceBusQueueClientFactory<T extends SubQueue> extends AbstractServiceBusFactory<T> {

    protected final Map<String, ServiceBusSenderAsyncClient> queueSenderAsyncMap = new ConcurrentHashMap<>();

    protected final Map<String, ServiceBusSenderClient> queueSenderMap = new ConcurrentHashMap<>();

    protected final Map<Tuple<String, String>, ServiceBusSessionReceiverAsyncClient> queueSessionReceiverAsyncMap =
        new ConcurrentHashMap<>();

    protected final Map<Tuple<String, String>, ServiceBusSessionReceiverClient> queueSessionReceiverMap =
        new ConcurrentHashMap<>();

    protected final Map<Tuple<String, T>, ServiceBusReceiverAsyncClient> queueReceiverAsyncMap =
        new ConcurrentHashMap<>();

    protected final Map<Tuple<String, T>, ServiceBusReceiverClient> queueReceiverMap = new ConcurrentHashMap<>();

    protected final ServiceBusClientBuilder serviceBusClientBuilder;

    AbstractServiceBusQueueClientFactory(String connectionString, AmqpTransportType amqpTransportType) {
        super(connectionString);
        this.serviceBusClientBuilder = new ServiceBusClientBuilder().connectionString(connectionString);
        this.serviceBusClientBuilder.transportType(amqpTransportType);
    }


    @Override
    public ServiceBusSenderAsyncClient getOrCreateSenderAsyncClient(String queueName) {
        return queueSenderAsyncMap.computeIfAbsent(queueName, this::createSenderAsyncClient);
    }

    private ServiceBusSenderAsyncClient createSenderAsyncClient(String queueName) {
        return serviceBusClientBuilder.sender()
                                      .queueName(queueName)
                                      .buildAsyncClient();
    }

    @Override
    public ServiceBusSenderClient getOrCreateSenderClient(String queueName) {
        return queueSenderMap.computeIfAbsent(queueName, this::createSenderClient);
    }

    private ServiceBusSenderClient createSenderClient(String queueName) {
        return serviceBusClientBuilder.sender()
                                      .queueName(queueName)
                                      .buildClient();
    }

    @Override
    public ServiceBusReceiverAsyncClient getOrCreateReceiverAsyncClient(String queueName, T subscription) {
        return queueReceiverAsyncMap.computeIfAbsent(Tuple.of(queueName, subscription),
            this::createReceiverAsyncClient);
    }

    private ServiceBusReceiverAsyncClient createReceiverAsyncClient(Tuple<String, T> tuple) {
        return serviceBusClientBuilder.receiver()
                                      .queueName(tuple.getFirst())
                                      .subQueue(tuple.getSecond())
                                      .buildAsyncClient();
    }

    @Override
    public ServiceBusReceiverClient getOrCreateReceiverClient(String name, T subscription) {
        return queueReceiverMap.computeIfAbsent(Tuple.of(name, subscription), this::createReceiverClient);
    }

    private ServiceBusReceiverClient createReceiverClient(Tuple<String, T> tuple) {
        return serviceBusClientBuilder.receiver()
                                      .queueName(tuple.getFirst())
                                      .subQueue(tuple.getSecond())
                                      .buildClient();
    }

    @Override
    public ServiceBusSessionReceiverAsyncClient getOrCreateSessionReceiverAsyncClient(String name,
                                                                                      String subscription) {
        return queueSessionReceiverAsyncMap.computeIfAbsent(Tuple.of(name, subscription),
            this::createSessionReceiverAsyncClient);
    }

    private ServiceBusSessionReceiverAsyncClient createSessionReceiverAsyncClient(Tuple<String, String> tuple) {
        return serviceBusClientBuilder.sessionReceiver()
                                      .queueName(tuple.getFirst())
                                      .buildAsyncClient();
    }

    @Override
    public ServiceBusSessionReceiverClient getOrCreateSessionReceiverClient(String name, String subscription) {
        return queueSessionReceiverMap.computeIfAbsent(Tuple.of(name, subscription),
            this::createSessionReceiverClient);
    }

    private ServiceBusSessionReceiverClient createSessionReceiverClient(Tuple<String, String> tuple) {
        return serviceBusClientBuilder.sessionReceiver()
                                      .queueName(tuple.getFirst())
                                      .buildClient();
    }

    public abstract ServiceBusProcessorClient getOrCreateProcessor(String name,
                                                                   ServiceBusClientConfig clientConfig,
                                                                   ServiceBusMessageProcessor<ServiceBusReceivedMessageContext,
                                                                       ServiceBusErrorContext> messageProcessor);
}
