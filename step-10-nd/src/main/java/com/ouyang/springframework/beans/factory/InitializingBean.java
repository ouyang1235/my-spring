package com.ouyang.springframework.beans.factory;

/**
 * 自定义初始化接口
 * 在createBean中执行afterPropertiesSet()方法
 */
public interface InitializingBean {

    /**
     * Bean属性填充后调用
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;



}
