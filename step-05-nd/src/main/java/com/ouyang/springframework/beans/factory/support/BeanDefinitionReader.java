package com.ouyang.springframework.beans.factory.support;

import com.ouyang.springframework.beans.BeansException;
import com.ouyang.springframework.core.io.Resource;
import com.ouyang.springframework.core.io.ResourceLoader;

/**
 * BeanDefinition读取器
 * 从资源对象中解析出BeanDefinitions，并将其加载、注册进BeanFactory中
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resource) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;


}
