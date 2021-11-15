package com.ouyang.springframework.beans.factory;

import com.ouyang.springframework.beans.factory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonFactory implements BeanFactory{


    @Override
    public Object getBean(String name) {
        return doGetBean(name,null);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name,args);
    }

    protected Object doGetBean(String beanName,Object[] args){
        Object bean = getsingleton(beanName);
        if (bean != null){
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanDefinition,beanName,args);
    }

    protected abstract Object createBean(BeanDefinition beanDefinition, String beanName, Object[] args);

    protected abstract BeanDefinition getBeanDefinition(String name);

}
