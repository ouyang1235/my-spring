package com.ouyang.springframework.test.common;

import com.ouyang.springframework.beans.BeansException;
import com.ouyang.springframework.beans.factory.config.BeanPostProcessor;
import com.ouyang.springframework.test.bean.UserService;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)){
            UserService userService = (UserService) bean;
            userService.setLocation("BeanPostProcessor篡改了注入属性，现在值为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
