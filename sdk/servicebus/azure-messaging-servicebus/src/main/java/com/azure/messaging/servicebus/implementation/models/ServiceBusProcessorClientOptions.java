// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.messaging.servicebus.implementation.models;

import com.azure.core.amqp.implementation.TracerProvider;
import com.azure.core.annotation.Fluent;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;

import java.time.Duration;
import java.util.Objects;

/**
 * Additional options to configure {@link ServiceBusProcessorClient}.
 */
@Fluent
public final class ServiceBusProcessorClientOptions {

    private int maxConcurrentCalls = 1;
    private boolean disableAutoComplete;
    private Duration operationTimeout;

    private TracerProvider tracerProvider;

    /**
     * The operation timeout.
     *
     * @return the operation timeout.
     */
    public Duration getOperationTimeout() {
        return operationTimeout;
    }

    /**
     * The operation timeout.
     *
     * @param operationTimeout the operation timeout.
     */
    public ServiceBusProcessorClientOptions setOperationTimeout(Duration operationTimeout) {
        this.operationTimeout = operationTimeout;
        return this;
    }

    /**
     * Returns true if the auto-complete and auto-abandon feature is disabled.
     * @return true if the auto-complete and auto-abandon feature is disabled.
     */
    public boolean isDisableAutoComplete() {
        return disableAutoComplete;
    }

    /**
     * Disables auto-complete and auto-abandon feature if this is set to {@code true}.
     * @param disableAutoComplete Disables auto-complete and auto-abandon feature if this is set to {@code true}.
     */
    public ServiceBusProcessorClientOptions setDisableAutoComplete(boolean disableAutoComplete) {
        this.disableAutoComplete = disableAutoComplete;
        return this;
    }

    /**
     * The max concurrent messages that should be processed by the processor.
     * @return The max concurrent message that should be processed by the processor.
     */
    public int getMaxConcurrentCalls() {
        return maxConcurrentCalls;
    }

    /**
     * Sets the max concurrent messages that can be processed by the processor. If not set, the default value will be 1.
     *
     * @param maxConcurrentCalls The max concurrent messages that can be processed by the processor.
     * @return The updated instance of {@link ServiceBusProcessorClientOptions}.
     */
    public ServiceBusProcessorClientOptions setMaxConcurrentCalls(int maxConcurrentCalls) {
        this.maxConcurrentCalls = maxConcurrentCalls;
        return this;
    }

    /**
     * Returns the {@link TracerProvider} instance that is used in {@link ServiceBusProcessorClient}.
     *
     * @return The {@link TracerProvider} instance that is used in {@link ServiceBusProcessorClient}.
     */
    public TracerProvider getTracerProvider() {
        return tracerProvider;
    }

    /**
     * Sets the {@link TracerProvider} instance to use in {@link ServiceBusProcessorClient}.
     *
     * @param tracerProvider The {@link TracerProvider} instance to use in {@link ServiceBusProcessorClient}.
     * @return The updated instance of {@link ServiceBusProcessorClientOptions}.
     */
    public ServiceBusProcessorClientOptions setTracerProvider(TracerProvider tracerProvider) {
        this.tracerProvider = tracerProvider;
        return this;
    }
}
