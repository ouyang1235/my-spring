package com.ouyang.springframework.context.event;

import com.ouyang.springframework.context.ApplicationEvent;
import com.ouyang.springframework.context.ApplicationListener;

/**
 * 事件广播器接口
 * 属于事件上下文，有添加、去除监听器功能、发布事件功能
 */
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);

}
