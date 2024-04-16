package com.edriving.restboot.jetty.internal;

import com.edriving.restboot.http.HttpServer;
import com.edriving.restboot.inject.ApplicationContext;
import com.edriving.restboot.inject.spi.AutoBinder;
import com.edriving.restboot.jetty.JettyHttpServer;

public class JettyHttpServerAutoBinder implements AutoBinder {

    @Override
    public void bind(ApplicationContext applicationContext) {
        applicationContext.bindSingleton(JettyHttpServer.class, HttpServer.class);
    }
}
