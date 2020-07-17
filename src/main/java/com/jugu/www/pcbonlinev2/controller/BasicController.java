package com.jugu.www.pcbonlinev2.controller;

import org.springframework.beans.BeanUtils;

public class BasicController<T, E> {
    T conversionDO(T doObject, E dtoObject) {
        BeanUtils.copyProperties(dtoObject, doObject);
        return doObject;
    }
}
