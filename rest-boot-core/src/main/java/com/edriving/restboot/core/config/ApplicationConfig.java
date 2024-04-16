package com.edriving.restboot.core.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:env",
        "system:properties",
        "classpath:application-config.properties",
        "classpath:default/application-config.properties", // default properties
})
public interface ApplicationConfig extends Config {
    String APP_NAME = "app.name";
    String APP_PORT = "app.port";
    String APP_PACKAGES = "app.packages";


    @Key(APP_NAME)
    String appName();

    @Key(APP_PORT)
    @DefaultValue("8080")
    int port();

    @Key(APP_PACKAGES)
    String packages();
}
