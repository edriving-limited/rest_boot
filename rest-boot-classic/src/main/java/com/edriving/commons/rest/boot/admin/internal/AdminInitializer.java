package com.edriving.commons.rest.boot.admin.internal;

import com.edriving.commons.rest.boot.admin.HeartbeatResource;
import com.edriving.restboot.http.HttpChannel;
import com.edriving.restboot.http.HttpServer;
import com.edriving.restboot.inject.Initializer;
import com.edriving.restboot.jersey.RestBootJerseyServlet;
import jakarta.inject.Inject;
import jakarta.servlet.Servlet;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;

public class AdminInitializer implements Initializer {
    private final ServiceLocator serviceLocator;
    private final HttpServer httpServer;
    private final AdminConfig adminConfig;

    @Inject
    public AdminInitializer(ServiceLocator serviceLocator,
                            HttpServer httpServer,
                            AdminConfig adminConfig) {
        this.serviceLocator = serviceLocator;
        this.httpServer = httpServer;
        this.adminConfig = adminConfig;
    }

    public void initialize() {
        HttpChannel httpChannel = initHttpChannel();

        initServlet(httpChannel);
    }

    private HttpChannel initHttpChannel() {
        HttpChannel httpChannel = httpServer.createOrGetChannel(adminConfig.port());
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

            resourceConfig.packages(HeartbeatResource.class.getPackageName());
            if (adminConfig.packages() != null) {
                resourceConfig.packages(adminConfig.packages());
            }
        }
    }
}
