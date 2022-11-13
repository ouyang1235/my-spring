package com.ouyang.springframework.beans.factory;

/**
 * 自定义销毁方法接口
 */
public interface DisposableBean {

    void destroy() throws Exception;
}
