package com.edriving.restboot.health.internal;

import com.edriving.restboot.inject.ApplicationContext;
import com.edriving.restboot.inject.Initializer;
import com.edriving.restboot.inject.spi.AutoBinder;

public class HealthAutoBinder implements AutoBinder {
    @Override
    public void bind(ApplicationContext applicationContext) {
        applicationContext.bindSingleton(HealthInitializer.class, Initializer.class);
    }
}
