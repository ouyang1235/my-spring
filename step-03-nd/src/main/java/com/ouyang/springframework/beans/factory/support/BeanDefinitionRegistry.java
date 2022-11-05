package com.ouyang.springframework.beans.factory.support;

import com.ouyang.springframework.beans.factory.config.BeanDefinition;

/**
 * bean定义注册器
 */
public interface BeanDefinitionRegistry {
    /**
     * 注册bean定义
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
