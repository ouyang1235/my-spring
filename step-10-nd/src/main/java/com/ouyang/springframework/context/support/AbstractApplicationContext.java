package com.ouyang.springframework.context.support;

import com.ouyang.springframework.beans.BeansException;
import com.ouyang.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.ouyang.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.ouyang.springframework.beans.factory.config.BeanPostProcessor;
import com.ouyang.springframework.context.ApplicationEvent;
import com.ouyang.springframework.context.ApplicationListener;
import com.ouyang.springframework.context.ConfigurableApplicationContext;
import com.ouyang.springframework.context.event.ApplicationEventMulticaster;
import com.ouyang.springframework.context.event.ContextClosedEvent;
import com.ouyang.springframework.context.event.ContextRefreshedEvent;
import com.ouyang.springframework.context.event.SimpleApplicationEventMulticaster;
import com.ouyang.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * Abstract implementation of the {@link com.ouyang.springframework.context.ApplicationContext}
 * interface. Doesn't mandate the type of storage used for configuration; simply
 * implements common context functionality. Uses the Template Method design pattern,
 * requiring concrete subclasses to implement abstract methods.
 *
 * 核心抽象类
 * 1.制定了refresh流程模板
 * 2.使用桥接的设计模式，实现了ListableBeanFactory所需要的功能
 *
 * 07更新：
 * 新增了registerShutdownHook()和close()的实现
 *
 * 08更新：
 * refresh()中增添了加入感知处理器ApplicationContextAwareProcessor的步骤
 *
 * 10更新：
 * 1.成员变量新增了ApplicationEventMulticaster来提供事件管理的能力
 * 2.refresh()流程更新：初始化事件管理者initApplicationEventMulticaster、注册监听器registerListeners、发布完成容器刷新事件finishRefresh
 * 3.close()方法新增 发布容器关闭事件
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {
        //1.创建BeanFactory,并加载BeanDefinition
        refreshBeanFactory();
        //2.获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        //3.添加 ApplicationContextAwareProcessor，让继承自 ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        //4.在Bean实例化之前，执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);
        //5.BeanPostProcessor需要提前于其他Bean对象实例化之前执行注册操作
        registerBeanFactoryPostProcessors(beanFactory);
        // 6. 初始化事件发布者
        initApplicationEventMulticaster();
        // 7. 注册事件监听器
        registerListeners();
        // 8.提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
        // 9. 发布容器刷新完成事件
        finishRefresh();
    }


    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME,applicationEventMulticaster);
    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));

        // 执行销毁单例bean的销毁方法
        getBeanFactory().destroySingletons();
    }
}
