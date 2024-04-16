package com.edriving.restboot.config;

import com.edriving.restboot.inject.AbstractBinder;
import io.github.classgraph.ClassGraph;
import jakarta.inject.Singleton;
import org.aeonbits.owner.Config;

import java.util.List;

public class ConfigBinder extends AbstractBinder {

    @Override
    protected void configure() {
        var classGraph = new ClassGraph()
                .enableClassInfo()
                .rejectPackages("org.aeonbits.owner");

        try(var result = classGraph.scan()) {
            List<Class<?>> classes = result.getClassesImplementing(Config.class.getName()).loadClasses();
            for (Class<?> clazz : classes) {
                bindFactory(new ConfigFactory<>((Class<? extends Config>) clazz)).to(clazz).in(Singleton.class);
            }
        }
    }
}
