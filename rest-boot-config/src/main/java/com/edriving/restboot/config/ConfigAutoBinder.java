package com.edriving.restboot.config;

import com.edriving.restboot.inject.ApplicationContext;
import com.edriving.restboot.inject.spi.AutoBinder;

public class ConfigAutoBinder implements AutoBinder {

    @Override
    public void bind(ApplicationContext applicationContext) {
        applicationContext.bind(new ConfigBinder());
    }
}
