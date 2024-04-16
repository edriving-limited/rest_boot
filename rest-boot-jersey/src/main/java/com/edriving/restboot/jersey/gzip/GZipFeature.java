package com.edriving.restboot.jersey.gzip;

import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;
import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.server.filter.EncodingFilter;

public class GZipFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        context.register(GZipEncoder.class);
        context.register(EncodingFilter.class);
        return true;
    }
}
