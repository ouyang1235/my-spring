package com.ouyang.springframework.beans.factory.support;

import com.ouyang.springframework.beans.BeansException;
import com.ouyang.springframework.beans.factory.DisposableBean;
import com.ouyang.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * 默认单例注册器实现
 * 实现：使用HashMap存储单例对象
 * 并附带了注册单例的功能
 *
 * 07更新:
 * 1.新增了destroySingletons()方法实现，用于给子类AbstractBenFactory使用
 * 2.新增了registerDisposableBean()方法，注册需要执行销毁方法的Bean对象
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String,Object> singletonObjects = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName,Object singletonObject){
        singletonObjects.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName,DisposableBean disposableBean){
        disposableBeans.put(beanName, disposableBean);
    }

    public void destroySingletons(){
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();
        //为什么是倒序？
        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try{
                disposableBean.destroy();
            }catch (Exception e){
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }

    }


}
