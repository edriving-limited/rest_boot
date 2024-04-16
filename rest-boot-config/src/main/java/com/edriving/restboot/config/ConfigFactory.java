package com.edriving.restboot.config;

import com.edriving.restboot.inject.Factory;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigCache;

public class ConfigFactory<T extends Config> implements Factory<T> {

    private final Class<T> clazz;

    public ConfigFactory(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T provide() {
        return ConfigCache.getOrCreate(clazz);
    }

    @Override
    public void dispose(Config instance) {

    }
}
