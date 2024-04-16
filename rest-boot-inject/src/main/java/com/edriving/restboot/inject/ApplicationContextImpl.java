package com.edriving.restboot.inject;

import jakarta.inject.Singleton;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.api.ServiceLocatorFactory;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;

import java.util.List;

import static org.glassfish.hk2.api.ServiceLocatorFactory.CreatePolicy.DESTROY;

public class ApplicationContextImpl implements ApplicationContext {
    ApplicationContextImpl(String contextName, Context parentContext) {
        ServiceLocatorFactory serviceLocatorFactory = ServiceLocatorFactory.getInstance();

        ServiceLocator parentServiceLocator = null;

        if (parentContext instanceof ApplicationContextImpl) {
            parentServiceLocator = ((ApplicationContextImpl) parentContext).serviceLocator;
        }

        this.serviceLocator = serviceLocatorFactory.create(contextName, parentServiceLocator, null, DESTROY);
        ServiceLocatorUtilities.enableImmediateScope(this.serviceLocator);
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        return serviceLocator.getService(clazz);
    }

    @Override
    public <T> List<T> getAllBeanOfType(Class<T> clazz) {
        return serviceLocator.getAllServices(clazz);
    }

    @Override
    public String getName() {
        return serviceLocator.getName();
    }

    @Override
    public void bind(AbstractBinder binder) {
        ServiceLocatorUtilities.bind(serviceLocator, binder);
    }

    @Override
    public <T> void bind(Class<T> clazz) {
        bind(new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(clazz);
            }
        });
    }

    @Override
    public <T> void bind(Class<? extends T> implClazz, Class<T> interfaceClass) {
        bind(new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(implClazz).to(interfaceClass);
            }
        });
    }

    @Override
    public <T> void bindSingleton(Class<T> clazz) {
        bind(new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(clazz).in(Singleton.class);
            }
        });
    }

    @Override
    public <T> void bindSingleton(Class<? extends T> implClazz, Class<T> interfaceClass) {
        bind(new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(implClazz).to(interfaceClass).in(Singleton.class);
            }
        });
    }

    @Override
    public void bindObject(Object object) {
        bind(new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(object);
            }
        });
    }

    @Override
    public <S, T extends S> void bindObject(T object, Class<S> interfaceClass) {
        bind(new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(object).to(interfaceClass);
            }
        });
    }

    private final ServiceLocator serviceLocator;
}
