package cn.bugstack.springframework.beans.factory.config;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {

    /**
     * 在所有的BeanDefinition加载完成后，实例化Bean对象之前，提供修改BeanDefinition属性的机制
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
