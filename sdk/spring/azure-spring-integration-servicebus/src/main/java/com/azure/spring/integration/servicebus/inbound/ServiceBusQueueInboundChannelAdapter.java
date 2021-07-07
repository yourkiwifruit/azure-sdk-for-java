// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.spring.integration.servicebus.inbound;

import com.azure.spring.integration.core.AbstractInboundChannelAdapter;
import com.azure.spring.integration.core.api.SubscribeOperation;
import com.azure.spring.integration.servicebus.metrics.Instrumentation;
import com.azure.spring.integration.servicebus.metrics.InstrumentationManager;
import org.springframework.lang.NonNull;

/**
 * Inbound channel adapter for Service Bus Queue.
 */
public class ServiceBusQueueInboundChannelAdapter extends AbstractInboundChannelAdapter {

    private InstrumentationManager instrumentationManager;

    public ServiceBusQueueInboundChannelAdapter(String destination, @NonNull SubscribeOperation subscribeOperation,
                                                InstrumentationManager instrumentationManager) {
        super(destination);
        this.subscribeOperation = subscribeOperation;
        this.instrumentationManager = instrumentationManager;
    }

    @Override
    public void doStart() {
        super.doStart();
        instrumentationManager
            .addHealthInstrumentation(new Instrumentation(this.destination + this.consumerGroup));
    }
}
