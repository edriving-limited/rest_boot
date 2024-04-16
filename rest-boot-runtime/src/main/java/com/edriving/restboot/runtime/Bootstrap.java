package com.edriving.restboot.runtime;

import com.edriving.restboot.http.HttpServer;
import com.edriving.restboot.inject.ApplicationContext;
import com.edriving.restboot.inject.ApplicationContextBuilder;
import com.edriving.restboot.inject.Initializer;
import com.edriving.restboot.inject.spi.AutoBinder;

import java.util.ServiceLoader;

class Bootstrap {

    Bootstrap() {
    }

    public void run(String[] args) throws Exception {
        ApplicationContext applicationContext = new ApplicationContextBuilder()
                .withDefaultName()
                .build();

        ServiceLoader.load(AutoBinder.class)
                .forEach(autoBinder -> autoBinder.bind(applicationContext));

        applicationContext.getAllBeanOfType(Initializer.class)
                .forEach(Initializer::initialize);

        HttpServer httpServer = applicationContext.getBean(HttpServer.class);
        httpServer.start();
    }
}
