package com.ouyang.springframework.beans.factory;

import com.ouyang.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public class DefaultListableBeanFactory extends AbstractAutowireBeanFactory implements BeanDefinitionRegistry {

    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();


    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name,beanDefinition);
    }

    @Override
    protected BeanDefinition getBeanDefinition(String name) {
        return beanDefinitionMap.get(name);
    }
}
