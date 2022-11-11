package com.ouyang.springframework.beans.factory.config;


import com.ouyang.springframework.beans.BeansException;
import com.ouyang.springframework.beans.factory.BeanFactory;

/**
 * Extension of the {@link BeanFactory}
 * interface to be implemented by bean factories that are capable of
 * autowiring, provided that they want to expose this functionality for
 * existing bean instances.
 *
 * 06更新：
 * 新增方法：
 *     applyBeanPostProcessorsBeforeInitialization 与 applyBeanPostProcessorsAfterInitialization
 *     在createBean流程中使用，分别用于执行BeanPostProcessors接口实现类的postProcessBeforeInitialization方法与 postProcessorsAfterInitialization方法
 *
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;

}
