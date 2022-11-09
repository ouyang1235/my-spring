package com.ouyang.springframework.context;

import com.ouyang.springframework.beans.BeansException;

/**
 * SPI interface to be implemented by most if not all application contexts.
 * Provides facilities to configure an application context in addition
 * to the application context client methods in the
 * {@link ApplicationContext} interface.
 *
 *   可配置的应用上下文
 *  定义了refresh()功能
 *
 * 07更新：
 *  新增了两个功能：
 *  1.registerShutdownHook()  向虚拟机注册停止运行时的钩子函数
 *  2.close() 关闭spring容器方法
 */
public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();

}
