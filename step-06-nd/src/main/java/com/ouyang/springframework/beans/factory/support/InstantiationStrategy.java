package com.ouyang.springframework.beans.factory.support;

import com.ouyang.springframework.beans.BeansException;
import com.ouyang.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 实例化策略接口
 * 定义实例化bean对象的接口
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor,Object[] args) throws BeansException;

}
