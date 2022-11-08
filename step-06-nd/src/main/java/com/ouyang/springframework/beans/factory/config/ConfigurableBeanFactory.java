package com.ouyang.springframework.beans.factory.config;

import com.ouyang.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * Configuration interface to be implemented by most bean factories. Provides
 * facilities to configure a bean factory, in addition to the bean factory
 * client methods in the {@link com.ouyang.springframework.beans.factory.BeanFactory}
 * interface.
 *
 * 06更新：
 * 新增功能：
 *  addBeanPostProcessor  工厂添加Bean实例化处理器
 *
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory,SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
