package com.ouyang.springframework.beans.factory;

import com.ouyang.springframework.beans.BeansException;
import com.ouyang.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public class JDKCreateBeanStrategy implements CreateBeanStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) {
        Class clazz = beanDefinition.getClazz();
        try{
            if (null != constructor){
                return clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            }else {
                return clazz.getDeclaredConstructor().newInstance();
            }
        }catch (Exception e){
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }
}
