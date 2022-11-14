package com.ouyang.springframework.context;


/**
 * 事件发布接口
 *
 * 实现此接口的类需要承担发布事件的功能
 */
public interface ApplicationEventPublisher {


    void publishEvent(ApplicationEvent event);
}
