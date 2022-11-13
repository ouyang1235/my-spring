package com.ouyang.springframework.context.event;

import com.ouyang.springframework.context.ApplicationContext;

/**
 * 上下文关闭事件
 */
public class ContextClosedEvent extends ApplicationContextEvent{

    public ContextClosedEvent(Object source) {
        super(source);
    }

}
