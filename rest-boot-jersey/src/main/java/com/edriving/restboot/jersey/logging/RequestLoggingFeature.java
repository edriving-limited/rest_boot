package com.edriving.restboot.jersey.logging;

import com.edriving.restboot.inject.AbstractBinder;
import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;
import org.glassfish.jersey.process.internal.RequestScoped;

public class RequestLoggingFeature implements Feature {
    @Override
    public boolean configure(FeatureContext context) {
        context.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bindAsContract(RequestLoggingMessage.class).in(RequestScoped.class);
            }
        });
        context.register(RequestLoggingFilter.class);
        context.register(ExceptionLoggingListener.class);
        return true;
    }
}
