package com.edriving.restboot.inject;

import java.util.List;

public interface Context {

    <T> T getBean(Class<T> clazz);

    <T> List<T> getAllBeanOfType(Class<T> clazz);
}
