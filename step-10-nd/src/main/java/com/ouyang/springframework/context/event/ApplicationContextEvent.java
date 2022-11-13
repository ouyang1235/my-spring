package com.ouyang.springframework.context.event;

import com.ouyang.springframework.context.ApplicationContext;
import com.ouyang.springframework.context.ApplicationEvent;


/**
 * 增加了获取应用上下文的方法
 */
public class ApplicationContextEvent extends ApplicationEvent {


    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) getSource();
    }
}
