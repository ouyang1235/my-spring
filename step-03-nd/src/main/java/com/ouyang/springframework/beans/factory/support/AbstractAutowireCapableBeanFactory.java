package com.ouyang.springframework.beans.factory.support;

import com.ouyang.springframework.beans.BeansException;
import com.ouyang.springframework.beans.factory.config.BeanDefinition;

import java.beans.Beans;
import java.lang.reflect.Constructor;

/**
 * 顾名思义，是带有自动装配功能的bean工厂
 * 他的任务是完成新bean对象的创建工作，并将新创建出来的bean对象注册到单例对象中
 * 如何实现：获取bean对象的类和其构造方法，使用策略模式来选择创建代理对象的方法，并创建新的bean对象
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean =null;
        try{
            bean = createBeanInstance(beanDefinition,beanName,args);
        }catch (Exception e){
            throw new BeansException("Instantiation of bean failed",e);
        }
        addSingleton(beanName,bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition,String beanName,Object[] args){
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        for (Constructor<?> ctor : beanClass.getDeclaredConstructors()) {
            if (null != args && ctor.getParameterTypes().length == args.length){
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition,beanName,constructorToUse,args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
