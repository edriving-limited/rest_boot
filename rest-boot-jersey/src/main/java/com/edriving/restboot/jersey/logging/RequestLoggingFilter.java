package com.edriving.restboot.jersey.logging;

import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import org.slf4j.Logger;

import java.time.Instant;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

public class RequestLoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {
    private final Provider<RequestLoggingMessage> messageProvider;

    @Inject
    public RequestLoggingFilter(Provider<RequestLoggingMessage> messageProvider) {
        this.messageProvider = messageProvider;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) {
        RequestLoggingMessage message = messageProvider.get();

        if (message != null) {
            message.setRequestId(UUID.randomUUID().toString());
            message.setPath(requestContext.getUriInfo().getPath());
            message.setMethod(requestContext.getMethod());
            message.setQuery(requestContext.getUriInfo().getRequestUri().getQuery());
            message.setRequestTimestamp(Instant.now().toEpochMilli());
            message.setPathTemplate(null);
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        RequestLoggingMessage message = messageProvider.get();
        Instant instant = Instant.now();
        if (message != null) {
            message.setHttpStatus(responseContext.getStatus());
            message.setResponseTime(instant.toString());
            message.setResponseTimestamp(instant.toEpochMilli());
            if (message.getRequestTimestamp() != null) {
                message.setElapsedTime(instant.toEpochMilli() - message.getRequestTimestamp());
            }

            LOGGER.info("{}", gson.toJson(message.toMap()));
        }
    }

    private static final Logger LOGGER = getLogger(RequestLoggingFilter.class);
    private static final Gson gson = new Gson();
}
