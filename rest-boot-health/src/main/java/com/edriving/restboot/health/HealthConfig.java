package com.edriving.restboot.health;

import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:env",
        "system:properties",
        "classpath:health-config.properties",
        "classpath:default/health-config.properties", // default properties
})
public interface HealthConfig extends Config {
    @Key("health.port")
    @DefaultValue("8080")
    int port();
}
