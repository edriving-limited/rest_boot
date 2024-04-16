package com.edriving.restboot.runtime.internal;

import com.edriving.restboot.inject.ApplicationContext;
import com.edriving.restboot.inject.Initializer;
import com.edriving.restboot.inject.spi.AutoBinder;

public class RuntimeAutoBinder implements AutoBinder {
    @Override
    public void bind(ApplicationContext applicationContext) {
        applicationContext.bindSingleton(RuntimeInitializer.class, Initializer.class);
    }
}
