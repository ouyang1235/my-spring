package com.ouyang.springframework.beans.factory.config;

import com.ouyang.springframework.beans.BeansException;
import com.ouyang.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * bean工厂处理器
 * 作用：在factory读取了所有的BeanDefinition之后，对factory中所有的BeanDefinition进行修改操作
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的BeanDefinition加载完成之后，实例化Bean对象之前，提供修改BeanDefinition属性的机制
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
