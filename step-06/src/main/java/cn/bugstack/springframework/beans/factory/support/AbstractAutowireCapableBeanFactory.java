package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.PropertyValue;
import cn.bugstack.springframework.beans.PropertyValues;
import cn.bugstack.springframework.beans.factory.config.AutowireCapableBeanFactory;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;
import cn.bugstack.springframework.beans.factory.config.BeanPostProcessor;
import cn.bugstack.springframework.beans.factory.config.BeanReference;
import cn.hutool.core.lang.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import static cn.hutool.core.bean.BeanUtil.setFieldValue;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition,Object[] args) throws BeansException {
        Object bean =null;
        try{
            bean = createBeanInstance(beanDefinition,beanName,args);
            //给bean填充属性
            applyPropertyValues(beanName,bean,beanDefinition);
            //执行 Bean的初始化方法和BeanPostProcessor的前置和后置处理方法
            bean = initializeBean(beanName,bean,beanDefinition);
        }catch (Exception e){
            throw new BeansException("Instantiation of bean failed",e);
        }

        addSingleton(beanName,bean);
        return bean;
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition){
        //1.执行BeanPostProcessor Before处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        //2.待完成内容：invokeInitMethods(beanName,wrappedBean,beanDefinition)
        invokeInitMethods(beanName,wrappedBean,beanDefinition);
        //3.执行BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean,beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition){

    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition){
        try{
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValueList()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference){
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                setFieldValue(bean,name,value);
            }
        }catch (Exception e){
            throw new BeansException("Error setting property values：" + beanName);
        }
    }

    protected  Object createBeanInstance(BeanDefinition beanDefinition,String beanName,Object[] args) throws BeansException {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> ctor : declaredConstructors) {
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

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }
}
