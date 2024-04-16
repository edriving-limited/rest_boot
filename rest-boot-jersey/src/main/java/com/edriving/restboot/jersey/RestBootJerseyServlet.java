package com.edriving.restboot.jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class RestBootJerseyServlet extends ServletContainer {

    private final ResourceConfig resourceConfig;

    public RestBootJerseyServlet() {
        this(new RestBootResourceConfig());
    }

    public RestBootJerseyServlet(ResourceConfig resourceConfig) {
        super(resourceConfig);
        this.resourceConfig = resourceConfig;
    }

    public ResourceConfig getResourceConfig() {
        if (getWebComponent() != null
                && getWebComponent().getAppHandler() != null
                && getConfiguration() != null) {
            return getConfiguration();
        } else {
            return resourceConfig;
        }
    }
}
