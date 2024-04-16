package com.edriving.restboot.health.internal;

import com.edriving.restboot.health.HealthConfig;
import com.edriving.restboot.health.HealthResource;
import com.edriving.restboot.http.HttpChannel;
import com.edriving.restboot.http.HttpServer;
import com.edriving.restboot.inject.Initializer;
import com.edriving.restboot.jersey.RestBootJerseyServlet;
import jakarta.inject.Inject;
import jakarta.servlet.Servlet;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;

public class HealthInitializer implements Initializer {
    private final ServiceLocator serviceLocator;
    private final HttpServer httpServer;
    private final HealthConfig healthConfig;

    @Inject
    public HealthInitializer(ServiceLocator serviceLocator,
                             HttpServer httpServer,
                             HealthConfig healthConfig) {
        this.serviceLocator = serviceLocator;
        this.httpServer = httpServer;
        this.healthConfig = healthConfig;
    }

    public void initialize() {
        HttpChannel httpChannel = initHttpChannel();

        initServlet(httpChannel);
    }

    private HttpChannel initHttpChannel() {
        HttpChannel httpChannel = httpServer.createOrGetChannel(healthConfig.port());
        if (httpChannel.getAttribute(ServletProperties.SERVICE_LOCATOR) == null) {
            httpChannel.setAttribute(ServletProperties.SERVICE_LOCATOR, serviceLocator);
        }
        return httpChannel;
    }

    private void initServlet(HttpChannel httpChannel) {
        Servlet servlet = httpChannel.getServlet("/*");
        if (servlet == null) {
            servlet = new RestBootJerseyServlet();
            httpChannel.addServlet("/*", servlet);
        }

        if (servlet instanceof RestBootJerseyServlet) {
            ResourceConfig resourceConfig = ((RestBootJerseyServlet) servlet).getResourceConfig();

            resourceConfig.packages(HealthResource.class.getPackageName());
        }
    }
}
