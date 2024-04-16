package ${package}.service;

import ${package}.config.HelloConfig;
import jakarta.inject.Inject;

public class HelloWorldServiceImpl implements HelloWorldService {

    private final HelloConfig helloConfig;

    @Inject
    public HelloWorldServiceImpl(HelloConfig helloConfig) {
        this.helloConfig = helloConfig;
    }

    @Override
    public String getHelloMessage() {
        return helloConfig.message();
    }
}
