package com.ouyang.springframework.context;

import com.ouyang.springframework.beans.factory.HierarchicalBeanFactory;
import com.ouyang.springframework.beans.factory.ListableBeanFactory;
import com.ouyang.springframework.core.io.ResourceLoader;

/**
 * Central interface to provide configuration for an application.
 * This is read-only while the application is running, but may be
 * reloaded if the implementation supports this.
 *
 * 应用上下文
 *
 * 10更新：
 * 继承接口新增：HierarchicalBeanFactory、ResourceLoader、ApplicationEventPublisher
 * 相当于需要加载资源（ResourceLoader）的能力和发布事件（ApplicationEventPublisher）的能力
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
