package com.ouyang.springframework.context.event;

import com.ouyang.springframework.beans.factory.BeanFactory;
import com.ouyang.springframework.context.ApplicationEvent;
import com.ouyang.springframework.context.ApplicationListener;

/**
 * 管理器最终实现
 *
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListener(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
