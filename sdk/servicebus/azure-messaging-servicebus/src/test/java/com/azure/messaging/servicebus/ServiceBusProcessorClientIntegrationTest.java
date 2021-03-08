package com.azure.messaging.servicebus;

import com.azure.core.util.logging.ClientLogger;
import com.azure.messaging.servicebus.implementation.MessagingEntityType;
import com.azure.messaging.servicebus.models.CompleteOptions;
import com.azure.messaging.servicebus.models.ServiceBusReceiveMode;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import reactor.test.StepVerifier;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

class ServiceBusProcessorClientIntegrationTest extends IntegrationTestBase {
    private ServiceBusSenderAsyncClient sender;
    private ServiceBusReceiverAsyncClient receiver;

    protected ServiceBusProcessorClientIntegrationTest() {
        super(new ClientLogger(ServiceBusProcessorClientIntegrationTest.class));
    }

    @BeforeAll
    static void beforeAll() {
        StepVerifier.setDefaultTimeout(Duration.ofSeconds(100));
    }

    @AfterAll
    static void afterAll() {
        StepVerifier.resetDefaultTimeout();
    }


    /**
     * Test cross transaction entity
     */
    @MethodSource("com.azure.messaging.servicebus.IntegrationTestBase#messagingEntityProvider")
    @ParameterizedTest
    void crossEntityTransactionTest(MessagingEntityType entityType) throws InterruptedException {
        // Arrange
        final boolean useCredentials = false;
        final Duration shortTimeout = Duration.ofSeconds(15);
        final int viaIntermediateEntity = TestUtils.USE_CASE_TXN_QUEUE_1;
        //final int destinationEntity = TestUtils.USE_CASE_SEND_VIA_QUEUE_2;
        final boolean isSessionEnabled = false;
        final int total = 2;
        final Duration shortWait = Duration.ofSeconds(3);
        //boolean sharedConnection, int entityIndex, boolean useCredentials, boolean isSessionAware
        //TestConnectionOptions connectionOptions = new TestConnectionOptions(sharedConnection, TestUtils.USE_CASE_TXN_QUEUE_1, useCredentials, isSessionAware);

        setSenderAndReceiver(entityType, TestUtils.USE_CASE_SCHEDULE_MESSAGES, isSessionEnabled);

        int destination1_Entity = 0;
        int destination2_Entity = 2;
        int destination3_Entity = 3;
        String queue1 = "queue-1";
        String queue2 = "queue-2";
        String queue3 = "queue-3";

        final boolean shareConnection = true;
        final String messageId = UUID.randomUUID().toString();
        final byte[] CONTENTS_BYTES1 = "Some-contents 1".getBytes(StandardCharsets.UTF_8);

        final byte[] CONTENTS_BYTES2 = "Some-contents 2".getBytes(StandardCharsets.UTF_8);
        final byte[] CONTENTS_BYTES3 = "Some-contents 3".getBytes(StandardCharsets.UTF_8);
        final String transactionGroup = "coordinator";
        final List<ServiceBusMessage> messages1 = TestUtils.getServiceBusMessages(total, messageId, CONTENTS_BYTES1);
        final List<ServiceBusMessage> messages2 = TestUtils.getServiceBusMessages(total, messageId, CONTENTS_BYTES2);
        final List<ServiceBusMessage> messages3 = TestUtils.getServiceBusMessages(total, messageId, CONTENTS_BYTES3);

        ServiceBusClientBuilder builder = getBuilder(useCredentials);

        final ServiceBusSenderAsyncClient destination1_Sender = builder
            .sender()
            .enableCrossEntityTransactions()
            .queueName(queue1)
            .buildAsyncClient();

        final ServiceBusSenderAsyncClient destination2_Sender = builder
            .sender()
            .enableCrossEntityTransactions()
            .queueName(queue2)
            .buildAsyncClient();

        final ServiceBusSenderAsyncClient destination3_Sender = builder
            .sender()
            .enableCrossEntityTransactions()
            .queueName(queue3)
            .buildAsyncClient();

        final ServiceBusReceiverAsyncClient destination1_receiver = builder
            .receiver()
            .enableCrossEntityTransactions()
            .queueName(queue1)
            .disableAutoComplete()
            .buildAsyncClient();

        ServiceBusTransactionContext transactionId = destination1_Sender.createTransaction().block();

        StepVerifier.create(destination1_Sender.sendMessages(messages1, transactionId)).verifyComplete();

        destination2_Sender
            .sendMessages(messages2, transactionId)
            .block();

        destination1_receiver.receiveMessages().take(1).flatMap(message-> {
            return destination1_receiver.complete(message, new CompleteOptions().setTransactionContext(transactionId))
                .thenReturn(message);
        }).subscribe(message -> {
            System.out.println("!!!! Test Receiver completed message queue1, SQ " + message.getSequenceNumber() + "  :" + message.getBody().toString());
        });

        TimeUnit.SECONDS.sleep(8);

        destination3_Sender.sendMessages(messages3, transactionId)
            .then(destination3_Sender.commitTransaction(transactionId)
                .doOnSuccess(a -> {
                    System.out.println("!!!! rollbackTransaction     complete " + a);
                }))
            .subscribe();

        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * Sets the sender and receiver. If session is enabled, then a single-named session receiver is created.
     */
    private void setSenderAndReceiver(MessagingEntityType entityType, int entityIndex, boolean useCredentials) {
        final boolean isSessionAware = false;
        final boolean sharedConnection = true;

        this.sender = getSenderBuilder(useCredentials, entityType, entityIndex, isSessionAware, sharedConnection)
            .buildAsyncClient();
        this.receiver = getReceiverBuilder(useCredentials, entityType, entityIndex, sharedConnection)
            .receiveMode(ServiceBusReceiveMode.RECEIVE_AND_DELETE)
            .disableAutoComplete()
            .buildAsyncClient();
    }

/*
    private void setSenderAndReceiver(MessagingEntityType entityType, ServiceBusSenderAsyncClientIntegrationTest.TestConnectionOptions connectionOptions) {

        this.sender = getSenderBuilder(connectionOptions.isUseCredentials(), entityType, connectionOptions.getEntityIndex(),
            connectionOptions.isSessionAware(), connectionOptions.isSharedConnection())
            .buildAsyncClient();
        this.receiver = getReceiverBuilder(connectionOptions.isUseCredentials(), entityType, connectionOptions.getEntityIndex(), connectionOptions.isSessionAware())
            .receiveMode(ServiceBusReceiveMode.RECEIVE_AND_DELETE)
            .disableAutoComplete()
            .buildAsyncClient();
    }*/
}
