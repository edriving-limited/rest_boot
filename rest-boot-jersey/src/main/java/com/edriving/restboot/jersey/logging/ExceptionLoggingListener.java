package com.edriving.restboot.jersey.logging;

import jakarta.inject.Inject;
import jakarta.inject.Provider;
import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class ExceptionLoggingListener implements ApplicationEventListener, RequestEventListener {
    private final Provider<RequestLoggingMessage> messageProvider;

    @Inject
    public ExceptionLoggingListener(Provider<RequestLoggingMessage> messageProvider) {
        this.messageProvider = messageProvider;
    }

    @Override
    public void onEvent(RequestEvent event) {
        if (event.getType() == RequestEvent.Type.ON_EXCEPTION) {
            RequestLoggingMessage message = messageProvider.get();
            if (message != null) {
                LOGGER.error("Error in request {}: {}", message.getRequestId(), event.getException().getMessage(), event.getException());
            }
        }
    }

    @Override
    public void onEvent(ApplicationEvent event) {
    }

    @Override
    public RequestEventListener onRequest(RequestEvent requestEvent) {
        return this;
    }

    public static final Logger LOGGER = getLogger(ExceptionLoggingListener.class);
}
