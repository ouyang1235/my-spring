package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
