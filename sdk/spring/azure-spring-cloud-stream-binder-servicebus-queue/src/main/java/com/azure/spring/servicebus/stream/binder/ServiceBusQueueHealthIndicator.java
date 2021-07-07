package com.azure.spring.servicebus.stream.binder;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.cloud.stream.config.BindingServiceProperties;

public class ServiceBusQueueHealthIndicator extends AbstractHealthIndicator {

    private BindingServiceProperties bindingServiceProperties;

    public ServiceBusQueueHealthIndicator(BindingServiceProperties bindingServiceProperties) {
        super("healthCheckFailedMessage");
        this.bindingServiceProperties = bindingServiceProperties;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up()
            .withDetail("up","chenggong");
    }
}
