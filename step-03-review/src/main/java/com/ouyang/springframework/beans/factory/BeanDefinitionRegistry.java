package com.ouyang.springframework.beans.factory;

import com.ouyang.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String name,BeanDefinition beanDefinition);

}
