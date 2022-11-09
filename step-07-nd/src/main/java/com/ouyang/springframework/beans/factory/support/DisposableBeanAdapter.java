package com.ouyang.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.ouyang.springframework.beans.BeansException;
import com.ouyang.springframework.beans.factory.DisposableBean;
import com.ouyang.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * 带有销毁方法Bean的适配器，使用该类注册进入容器后，在调用destroy()方法时，可以适配两种实现方法
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        //1.接口实现
        if (bean instanceof DisposableBean){
            ((DisposableBean)bean).destroy();
        }

        //2.配置destroy-method方法名(判断是为了避免二次执行销毁)
        //暂时未接入注解实现方式
        if(StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))){
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (null == destroyMethod){
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
