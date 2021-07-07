// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.spring.integration.servicebus.inbound;

import com.azure.spring.integration.core.AbstractInboundChannelAdapter;
import com.azure.spring.integration.core.api.SubscribeByGroupOperation;
import com.azure.spring.integration.servicebus.metrics.Instrumentation;
import com.azure.spring.integration.servicebus.metrics.InstrumentationManager;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

/**
 * Inbound channel adapter for Service Bus Topic.
 */
public class ServiceBusTopicInboundChannelAdapter extends AbstractInboundChannelAdapter {

    private InstrumentationManager instrumentationManager;

    public ServiceBusTopicInboundChannelAdapter(String destination,
                                                @NonNull SubscribeByGroupOperation subscribeByGroupOperation,
                                                String consumerGroup, InstrumentationManager instrumentationManager) {
        super(destination);
        Assert.hasText(consumerGroup, "consumerGroup cannot be null or empty");
        this.subscribeByGroupOperation = subscribeByGroupOperation;
        this.consumerGroup = consumerGroup;
        this.instrumentationManager = instrumentationManager;
    }

    @Override
    public void doStart() {
        super.doStart();
        instrumentationManager
            .addHealthInstrumentation(new Instrumentation(this.destination + this.consumerGroup));
    }
}
