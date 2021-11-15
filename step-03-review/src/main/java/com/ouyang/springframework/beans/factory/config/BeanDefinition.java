package com.ouyang.springframework.beans.factory.config;

public class BeanDefinition {

    private Class clazz;

    public BeanDefinition(Class clazz) {
        this.clazz = clazz;
    }

    public BeanDefinition() {
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
