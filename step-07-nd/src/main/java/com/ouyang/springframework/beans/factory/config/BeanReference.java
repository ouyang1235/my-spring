package com.ouyang.springframework.beans.factory.config;

/**
 * bean的引用，用于标识该属性也是一个自动注入的bean对象
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
