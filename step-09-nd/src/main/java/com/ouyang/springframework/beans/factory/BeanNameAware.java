package com.ouyang.springframework.beans.factory;

/**
 * 获取bean注册名称的感知接口
 */
public interface BeanNameAware extends Aware{

    void setBeanName(String name);

}
