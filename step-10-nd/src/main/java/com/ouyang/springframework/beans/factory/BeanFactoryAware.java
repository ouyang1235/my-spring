package com.ouyang.springframework.beans.factory;

import com.ouyang.springframework.beans.BeansException;

/**
 * 获取BeanFactory的感知接口
 */
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;


}
