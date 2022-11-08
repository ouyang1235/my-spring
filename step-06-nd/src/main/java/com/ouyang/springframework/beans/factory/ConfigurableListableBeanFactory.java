package com.ouyang.springframework.beans.factory;

import com.ouyang.springframework.beans.BeansException;
import com.ouyang.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.ouyang.springframework.beans.factory.config.BeanDefinition;
import com.ouyang.springframework.beans.factory.config.BeanPostProcessor;
import com.ouyang.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * Configuration interface to be implemented by most listable bean factories.
 * In addition to {@link com.ouyang.springframework.beans.factory.config.ConfigurableBeanFactory}, it provides facilities to
 * analyze and modify bean definitions, and to pre-instantiate singletons.
 *
 * 06更新:
 * 添加了新功能：
 *  1.preInstantiateSingletons()   提前实例化单例Bean对象
 *  2.addBeanPostProcessor(BeanPostProcessor beanPostProcessor) 提供在工厂中添加Bean处理器的方法(疑问：为何在父类接口中已经定义了功能，在此会再定义一次？个人猜想：在Spring框架进化重构中出现的冗余现象)
 *
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, ConfigurableBeanFactory, AutowireCapableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
