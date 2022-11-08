package com.ouyang.springframework.beans.factory;

import com.ouyang.springframework.beans.BeansException;
import com.ouyang.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.ouyang.springframework.beans.factory.config.BeanDefinition;

/**
 * Configuration interface to be implemented by most listable bean factories.
 * In addition to {@link com.ouyang.springframework.beans.factory.config.ConfigurableBeanFactory}, it provides facilities to
 * analyze and modify bean definitions, and to pre-instantiate singletons.
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory,HierarchicalBeanFactory, AutowireCapableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

}
