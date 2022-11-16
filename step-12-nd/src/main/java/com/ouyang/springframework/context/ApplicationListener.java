package com.ouyang.springframework.context;

import java.util.EventListener;

/**
 * Interface to be implemented by application event listeners.
 * Based on the standard <code>java.util.EventListener</code> interface
 * for the Observer design pattern.
 * @param <E>
 *
 * 事件监听器
 *
 */
public interface ApplicationListener <E extends ApplicationEvent> extends EventListener {

    /**
     * 事件响应
     * @param event
     */
    void onApplicationEvent(E event);

}
