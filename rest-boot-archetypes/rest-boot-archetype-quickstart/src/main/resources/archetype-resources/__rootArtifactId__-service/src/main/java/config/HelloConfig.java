package ${package}.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:HelloConfig.properties")
public interface HelloConfig extends Config {

    @DefaultValue("Hello, World!")
    String message();
}