package com.edriving.restboot.inject;

public interface ApplicationContext extends Context {

    String DEFAULT_NAME = "application-context";

    String getName();

    void bind(AbstractBinder binder);

    <T> void bind(Class<T> clazz);

    <T> void bind(Class<? extends T> implClazz, Class<T> interfaceClass);

    <T> void bindSingleton(Class<T> clazz);

    <T> void bindSingleton(Class<? extends T> implClazz, Class<T> interfaceClass);

    <T> void bindObject(T object);

    <S, T extends S> void bindObject(T object, Class<S> interfaceClass);
}
