package com.azure.spring.integration.servicebus.handler;

import com.azure.spring.integration.core.AzureHeaders;
import com.azure.spring.integration.core.AzureSendFailureException;
import com.azure.spring.integration.core.DefaultMessageHandler;
import com.azure.spring.integration.core.api.PartitionSupplier;
import com.azure.spring.integration.core.api.SendOperation;
import com.azure.spring.integration.servicebus.metrics.Instrumentation;
import com.azure.spring.integration.servicebus.metrics.InstrumentationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.MessageTimeoutException;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.util.StringUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DefaultServiceBusMessageHandler extends DefaultMessageHandler {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultServiceBusMessageHandler.class);

    private InstrumentationManager instrumentationManager;

    public DefaultServiceBusMessageHandler(String destination, SendOperation sendOperation,
                                           InstrumentationManager instrumentationManager) {
        super(destination, sendOperation);
        this.instrumentationManager = instrumentationManager;
    }

    @Override
    protected void handleMessageInternal(Message<?> message) {

        PartitionSupplier partitionSupplier = toPartitionSupplier(message);
        String destination = toDestination(message);

        instrumentationManager.addHealthInstrumentation(new Instrumentation(destination));


        CompletableFuture<?> future = this.sendOperation.sendAsync(destination, message, partitionSupplier);
        instrumentationManager.getHealthInstrumentation(destination)
                              .markStartedSuccessfully();
        if (this.sync) {
            waitingSendResponse(future, message);
            return;
        }
        handleSendResponseAsync(message, future);
    }


    private String toDestination(Message<?> message) {
        if (message.getHeaders().containsKey(AzureHeaders.NAME)) {
            return message.getHeaders().get(AzureHeaders.NAME, String.class);
        }

        return this.destination;
    }


    private PartitionSupplier toPartitionSupplier(Message<?> message) {
        PartitionSupplier partitionSupplier = new PartitionSupplier();
        String partitionKey = message.getHeaders().get(AzureHeaders.PARTITION_KEY, String.class);

        if (!StringUtils.hasText(partitionKey) && this.partitionKeyExpression != null) {
            partitionKey = this.partitionKeyExpression.getValue(this.evaluationContext, message, String.class);
        }

        if (StringUtils.hasText(partitionKey)) {
            partitionSupplier.setPartitionKey(partitionKey);
        }

        if (message.getHeaders().containsKey(AzureHeaders.PARTITION_ID)) {
            partitionSupplier
                .setPartitionId(message.getHeaders().get(AzureHeaders.PARTITION_ID, String.class));
        }
        return partitionSupplier;
    }


    private void waitingSendResponse(CompletableFuture<?> future, Message<?> message) {
        Long sendTimeout = this.sendTimeoutExpression.getValue(this.evaluationContext, message, Long.class);
        if (sendTimeout == null || sendTimeout < 0) {
            try {
                future.get();
            } catch (Exception e) {
                instrumentationManager.getHealthInstrumentation(destination)
                                      .markStartFailed(e);
                throw new MessageDeliveryException(e.getMessage());
            }
        } else {
            try {
                future.get(sendTimeout, TimeUnit.MILLISECONDS);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("{} sent successfully in sync mode", message);
                }
            } catch (TimeoutException e) {
                throw new MessageTimeoutException(message, "Timeout waiting for send event hub response", e);
            } catch (Exception e) {
                throw new MessageDeliveryException(e.getMessage());
            }
        }
    }

    private void handleSendResponseAsync(Message<?> message, CompletableFuture<?> future) {
        future.handle((t, ex) -> {
            if (ex != null) {
                instrumentationManager.getHealthInstrumentation(destination)
                                      .markStartFailed(new Exception(ex.getMessage()));
                if (LOG.isWarnEnabled()) {
                    LOG.warn("{} sent failed in async mode due to {}", message, ex.getMessage());
                }
                if (this.sendCallback != null) {
                    this.sendCallback.onFailure(ex);
                }

                if (getSendFailureChannel() != null) {
                    this.messagingTemplate.send(getSendFailureChannel(),
                        getErrorMessageStrategy()
                            .buildErrorMessage(new AzureSendFailureException(message, ex), null));
                }
            } else {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("{} sent successfully in async mode", message);
                }
                if (this.sendCallback != null) {
                    this.sendCallback.onSuccess((Void) t);
                }
            }

            return null;
        });
    }

}
