package com.ouyang.springframework.beans.factory.config;

import com.ouyang.springframework.beans.BeansException;

/**
 * Bean对象处理器
 * 作用：在Bean实例化前后，能对其进行修改处理的处理器
 */
public interface BeanPostProcessor {


    /**
     * 在Bean对象执行初始化方法之前，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException;

    /**
     * 在Bean对象执行初始化方法之后，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean,String beanName) throws BeansException;


}
