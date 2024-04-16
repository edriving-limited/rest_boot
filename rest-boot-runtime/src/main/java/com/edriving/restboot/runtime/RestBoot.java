package com.edriving.restboot.runtime;

import com.edriving.restboot.core.config.ApplicationConfig;

import java.util.*;

public class RestBoot {
    private final List<String> packages = new ArrayList<>();

    public RestBoot scanPackage(String... packages) {
        Collections.addAll(this.packages, packages);
        System.setProperty(ApplicationConfig.APP_PACKAGES, String.join(";", this.packages));

        return this;
    }

    public RestBoot port(int port) {
        System.setProperty(ApplicationConfig.APP_PORT, String.valueOf(port));

        return this;
    }

    public void start(String[] args) throws Exception {
        new Bootstrap().run(args);
    }
}
