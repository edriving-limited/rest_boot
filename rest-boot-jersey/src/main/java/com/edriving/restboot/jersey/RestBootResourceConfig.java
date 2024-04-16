package com.edriving.restboot.jersey;

import com.edriving.restboot.jersey.cors.CorsFilter;
import com.edriving.restboot.jersey.exception.DefaultExceptionMapper;
import com.edriving.restboot.jersey.exception.ValidationExceptionMapper;
import com.edriving.restboot.jersey.gzip.GZipFeature;
import com.edriving.restboot.jersey.logging.RequestLoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class RestBootResourceConfig extends ResourceConfig {

    public RestBootResourceConfig() {
        this.property(ServerProperties.WADL_FEATURE_DISABLE, Boolean.TRUE);

        register(RequestLoggingFeature.class);
        register(GZipFeature.class);
        register(CorsFilter.class);

        register(DefaultExceptionMapper.class);
        register(ValidationExceptionMapper.class);
    }
}
