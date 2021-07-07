package com.azure.spring.servicebus.stream.binder.config;


import com.azure.spring.servicebus.stream.binder.ServiceBusQueueHealthIndicator;
import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.stream.config.BindingServiceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnEnabledHealthIndicator("binders")
@ConditionalOnClass(HealthIndicator.class)
public class ServiceBusQueueHealthIndicatorConifguration {
    @Bean
    public ServiceBusQueueHealthIndicator serviceBusQueueHealthIndicator(
        BindingServiceProperties bindingServiceProperties){
        return new ServiceBusQueueHealthIndicator(bindingServiceProperties);
    }
}
