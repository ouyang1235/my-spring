package com.ouyang.springframework.beans.factory.config;

import com.ouyang.springframework.beans.BeansException;

/**
 * 实例化感知bean处理器
 * 1.spring中暂时用于aop代理对象的实例化处理，而不走普通的创建+注入
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    /**
     * bean对象执行初始化方法之前，执行此方法
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessorBeforeInstantiation(Class<?> beanClass,String beanName) throws BeansException;

}
