package cn.bugstack.springframework.beans.factory.config;

import cn.bugstack.springframework.beans.BeansException;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {


    /**
     * 在Bean对象执行初始化方法之前，执行此方法
     * 生成代理对象
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass,String beanName) throws BeansException;
}
