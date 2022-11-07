package com.ouyang.springframework.beans.factory.support;




import com.ouyang.springframework.beans.BeansException;
import com.ouyang.springframework.beans.factory.BeanFactory;
import com.ouyang.springframework.beans.factory.config.BeanDefinition;

/**
 * 顶层beanFactory抽象类
 * 使用模板模式，规定了获取bean对象的流程：
 * 1.获取单例对象
 * 2.如果未获取到则先获取beanDefinition
 * 3.根据定义获取bean对象
 *
 * **获取单例对象、获取bean定义、创建bean对象的实现交给了其他类，自己并不完成，只做模板策划的本职工作
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {


    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name,null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name,args);
    }

    protected <T> T doGetBean(final String name,final Object[] args){
        Object bean = getSingleton(name);
        if (bean != null){
            return (T)bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name,beanDefinition,args);
    }

    protected abstract BeanDefinition getBeanDefinition(String name) throws BeansException;

    protected  abstract Object createBean(String name,BeanDefinition beanDefinition,Object[] args) throws BeansException;


}
