package com.ouyang.springframework.beans.factory.config;

/**
 * 定义获取单例对象方法
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
