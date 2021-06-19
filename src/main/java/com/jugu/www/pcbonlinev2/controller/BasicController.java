package com.jugu.www.pcbonlinev2.controller;

import com.jugu.www.pcbonlinev2.utils.ThreadSessionLocal;
import org.springframework.beans.BeanUtils;

public abstract class BasicController<T, E> {
    T conversionDO(T doObject, E dtoObject) {
        BeanUtils.copyProperties(dtoObject, doObject);
        return doObject;
    }

    protected Integer getUserId(){
        return ThreadSessionLocal.getUserInfo().getId();
    }

    protected String getUserSysName(){
        return ThreadSessionLocal.getUserInfo().getUserSystemId();
    }


}
