package com.ouyang.springframework.beans.factory;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonFactory implements SingletonFactory {

    private Map<String,Object> singletonMap = new HashMap<>();

    @Override
    public Object getsingleton(String name) {
        return singletonMap.get(name);
    }

    protected void addSingleton(String name,Object object){
        singletonMap.put(name,object);
    }



}
