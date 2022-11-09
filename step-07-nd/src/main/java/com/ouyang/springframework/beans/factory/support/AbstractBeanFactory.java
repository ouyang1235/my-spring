package com.ouyang.springframework.beans.factory.support;




import com.ouyang.springframework.beans.BeansException;
import com.ouyang.springframework.beans.factory.config.BeanDefinition;
import com.ouyang.springframework.beans.factory.config.BeanPostProcessor;
import com.ouyang.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 顶层beanFactory抽象类
 * 使用模板模式，规定了获取bean对象的流程：
 * 1.获取单例对象
 * 2.如果未获取到则先获取beanDefinition
 * 3.根据定义获取bean对象
 *
 * **获取单例对象、获取bean定义、创建bean对象的实现交给了其他类，自己并不完成，只做模板策划的本职工作
 *
 * 06更新：
 * 1.添加了一个List用于存放注册的BeanPostProcessor，新增一个获取这个列表的方法
 * 2.实现接口更改： BeanFactory----> ConfigurableBeanFactory，相当于需要实现的功能增加：添加BeanPostProcessor
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();


    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name,null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name,args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(final String name, final Object[] args){
        Object bean = getSingleton(name);
        if (bean != null){
            return (T)bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name,beanDefinition,args);
    }

    protected abstract BeanDefinition getBeanDefinition(String name) throws BeansException;

    protected  abstract Object createBean(String name,BeanDefinition beanDefinition,Object[] args) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors(){
        return this.beanPostProcessors;
    }
}
