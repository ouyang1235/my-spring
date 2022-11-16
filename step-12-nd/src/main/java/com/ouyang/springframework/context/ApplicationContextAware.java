package com.ouyang.springframework.context;

import com.ouyang.springframework.beans.BeansException;
import com.ouyang.springframework.beans.factory.Aware;

/**
 * 应用上下文感知接口
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;


}
