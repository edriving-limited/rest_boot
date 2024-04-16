package com.edriving.restboot.serde.gson.internal;

import com.edriving.restboot.serde.gson.GsonMessageBodyHandler;
import jakarta.ws.rs.core.FeatureContext;
import org.glassfish.jersey.internal.spi.AutoDiscoverable;

public class GsonAutoDiscoverable implements AutoDiscoverable {
    @Override
    public void configure(FeatureContext context) {
        context.register(GsonMessageBodyHandler.class);
    }
}
