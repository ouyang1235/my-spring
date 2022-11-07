package com.ouyang.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.ouyang.springframework.beans.BeansException;
import com.ouyang.springframework.beans.PropertyValue;
import com.ouyang.springframework.beans.PropertyValues;
import com.ouyang.springframework.beans.factory.config.BeanDefinition;
import com.ouyang.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * 顾名思义，是带有自动装配功能的bean工厂
 * 他的任务是完成新bean对象的创建工作，并将新创建出来的bean对象注册到单例对象中
 * 如何实现：
 * 1.获取bean对象的类和其构造方法，使用策略模式来选择创建代理对象的方法，并创建新的bean对象
 * 2. （*04更新*） 在创建对象之后，给对象填充自动注入的属性
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean =null;
        try{
            //代理创建对象
            bean = createBeanInstance(beanDefinition,beanName,args);
            //给Bean填充属性
            applyPropertyValues(beanName,bean,beanDefinition);
        }catch (Exception e){
            throw new BeansException("Instantiation of bean failed",e);
        }
        addSingleton(beanName,bean);
        return bean;
    }

    /**
     * 填充属性
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition){
        try{
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {

                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference){
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                //属性填充
                BeanUtil.setFieldValue(bean,name,value);
            }
        }catch (Exception e){
            throw new BeansException("Error setting property values:" + beanName);
        }
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition,String beanName,Object[] args){
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        for (Constructor<?> ctor : beanClass.getDeclaredConstructors()) {
            if (null != args && ctor.getParameterTypes().length == args.length){
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition,beanName,constructorToUse,args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
