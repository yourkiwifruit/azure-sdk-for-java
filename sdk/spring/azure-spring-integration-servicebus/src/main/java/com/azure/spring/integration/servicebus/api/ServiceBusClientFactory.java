package com.azure.spring.integration.servicebus.api;

import com.azure.messaging.servicebus.ServiceBusReceiverAsyncClient;
import com.azure.messaging.servicebus.ServiceBusReceiverClient;
import com.azure.messaging.servicebus.ServiceBusSenderAsyncClient;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.azure.messaging.servicebus.ServiceBusSessionReceiverAsyncClient;
import com.azure.messaging.servicebus.ServiceBusSessionReceiverClient;

public interface ServiceBusClientFactory<T> {

    ServiceBusSenderAsyncClient getOrCreateSenderAsyncClient(String name);

    ServiceBusSenderClient getOrCreateSenderClient(String name);

    ServiceBusReceiverAsyncClient getOrCreateReceiverAsyncClient(String name, T subscription);

    ServiceBusReceiverClient getOrCreateReceiverClient(String name, T subscription);

    ServiceBusSessionReceiverAsyncClient getOrCreateSessionReceiverAsyncClient(String name, String subscription);

    ServiceBusSessionReceiverClient getOrCreateSessionReceiverClient(String name, String subscription);

}
