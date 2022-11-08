package com.ouyang.springframework.beans.factory;


import com.ouyang.springframework.beans.BeansException;

/**
 * BeanFactory
 *
 * 05新增：
 * 根据bean名称和型获取对象
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name,Object... args) throws BeansException;

    <T> T getBean(String name,Class<T> requiredType) throws BeansException;
}
