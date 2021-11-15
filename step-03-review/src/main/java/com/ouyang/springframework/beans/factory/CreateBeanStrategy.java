package com.ouyang.springframework.beans.factory;

import com.ouyang.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface CreateBeanStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor,Object[] args);

}
