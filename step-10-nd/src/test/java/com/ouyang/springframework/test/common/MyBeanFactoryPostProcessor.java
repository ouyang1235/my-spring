package com.ouyang.springframework.test.common;

import com.ouyang.springframework.beans.BeansException;
import com.ouyang.springframework.beans.PropertyValue;
import com.ouyang.springframework.beans.PropertyValues;
import com.ouyang.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.ouyang.springframework.beans.factory.config.BeanDefinition;
import com.ouyang.springframework.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition definition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = definition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company","postProcessBeanFactory篡改了注入属性，现在的值为：字节跳动"));
    }
}
