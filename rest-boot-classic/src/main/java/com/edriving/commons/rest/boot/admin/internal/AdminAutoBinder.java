package com.edriving.commons.rest.boot.admin.internal;

import com.edriving.restboot.inject.ApplicationContext;
import com.edriving.restboot.inject.Initializer;
import com.edriving.restboot.inject.spi.AutoBinder;

public class AdminAutoBinder implements AutoBinder {
    @Override
    public void bind(ApplicationContext applicationContext) {
        applicationContext.bindSingleton(AdminInitializer.class, Initializer.class);
    }
}
