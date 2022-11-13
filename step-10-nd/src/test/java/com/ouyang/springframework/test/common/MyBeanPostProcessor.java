package com.ouyang.springframework.test.common;

import com.ouyang.springframework.beans.BeansException;
import com.ouyang.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)){
            System.out.println("执行：MyBeanPostProcessor.postProcessBeforeInitialization()方法....");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)){
            System.out.println("执行：MyBeanPostProcessor.postProcessAfterInitialization()方法....");
        }
        return bean;
    }
}
