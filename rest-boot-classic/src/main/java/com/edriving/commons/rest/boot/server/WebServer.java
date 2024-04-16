package com.edriving.commons.rest.boot.server;

import com.edriving.restboot.runtime.RestBoot;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigCache;

@Config.Sources({"system:properties"})
interface AppConfig extends Config {
    String API_PORT_PROPERTY = "api-port";
    String API_PACKAGE_PROPERTY = "api-package";

    @Key(API_PORT_PROPERTY)
    @DefaultValue("8080")
    int port();

    @Key(API_PACKAGE_PROPERTY)
    String packages();
}

public class WebServer {

    public static void main(String[] args) throws Exception {
        AppConfig config = ConfigCache.getOrCreate(AppConfig.class);
        if (config.packages() == null || config.packages().trim().isEmpty()) {
            throw new IllegalArgumentException("PLEASE SPECIFY VALUE FOR '" + AppConfig.API_PACKAGE_PROPERTY + "' property. (Example: -D" + AppConfig.API_PACKAGE_PROPERTY + "=com.domain.api)");
        }

        new RestBoot()
                .scanPackage(config.packages())
                .port(config.port())
                .start(args);
    }
}
