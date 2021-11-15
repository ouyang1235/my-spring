package com.ouyang.springframework.beans.factory;

import com.ouyang.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireBeanFactory extends AbstractBeanFactory{

    private CreateBeanStrategy createBeanStrategy = new CglibCreateBeanStrategy();

    @Override
    protected Object createBean(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Object bean =null;
        bean = createBeanInstance(beanDefinition,beanName,args);
        addSingleton(beanName,bean);
        return bean;
    }

    protected  Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args){
        Constructor ctor = null;
        Class clazz = beanDefinition.getClazz();
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length){
                ctor = declaredConstructor;
                break;
            }
        }
        return getCreateBeanStrategy().instantiate(beanDefinition,beanName,ctor,args);
    }

    public CreateBeanStrategy getCreateBeanStrategy() {
        return createBeanStrategy;
    }
}
