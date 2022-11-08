package com.ouyang.springframework.beans.factory.support;

import com.ouyang.springframework.beans.BeansException;
import com.ouyang.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.ouyang.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * 暂时未理解类名称，可能真正的核心功能还没有展示出来
 * 实现的功能：
 * 1.获取beanDefinition
 * 2.注册beanDefinition
 *
 * 05拓展：
 * 1.新实现了ConfigurableListableBeanFactory接口
 *  暂时增加了getBeansOfType的方法
 * 2.由于 BeanDefinitionRegistry 的功能增加了，所以跟随着增加了以下几个方法的实现：
 *  containsBeanDefinition
 *  getBeanDefinitionNames
 *
 *  06更新：
 *  由于ConfigurableListableBeanFactory接口的新增功能，新增了如下实现：
 *  1.preInstantiateSingletons()  提前初始化所有单例对象
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String name) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) throw new BeansException("No bean named '"+ name +"' is defined");
        return beanDefinition;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        HashMap<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)){
                result.put(beanName,(T)getBean(beanName));
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {
        beanDefinitionMap.keySet().forEach(this::getBean);
    }
}
