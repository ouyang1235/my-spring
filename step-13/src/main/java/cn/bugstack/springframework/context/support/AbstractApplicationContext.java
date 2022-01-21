package cn.bugstack.springframework.context.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.bugstack.springframework.beans.factory.config.BeanFactoryPostProcessor;
import cn.bugstack.springframework.beans.factory.config.BeanPostProcessor;
import cn.bugstack.springframework.context.ApplicationEvent;
import cn.bugstack.springframework.context.ApplicationListener;
import cn.bugstack.springframework.context.ConfigurableApplicationContext;
import cn.bugstack.springframework.context.event.ApplicationEventMulticaster;
import cn.bugstack.springframework.context.event.ContextClosedEvent;
import cn.bugstack.springframework.context.event.ContextRefreshedEvent;
import cn.bugstack.springframework.context.event.SimpleApplicationEventMulticaster;
import cn.bugstack.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static  final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {
        //1.创建BeanFactory，并加载Bean Definition (未实现)
        refreshBeanFactory();
        //2.获取BeanFactory (未实现)
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        //添加ApplicationContextAwareProcessor
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        //3.在Bean实例化之前，执行BeanFactoryProcessor
        invokeBeanFactoryPostProcessor(beanFactory);
        //4.BeanPostProcessor需要提前于其他Bean对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);
        //5.初始化事件发布者
        initApplicationEventMulticaster();
        //6.注册事件监听器
        registerListeners();
        //7.提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
        //8.发布容器刷新完成事件
        finishRefresh();
    }

    protected  void finishRefresh(){
        publishEvent(new ContextRefreshedEvent(this));
    }

    protected  void registerListeners(){
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    protected void initApplicationEventMulticaster(){
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
    }

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    protected abstract void refreshBeanFactory() throws BeansException;

    private void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }


    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        //发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));
        //执行单例bean的销毁方法
        getBeanFactory().destroySingletons();
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
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name,requiredType);
    }
}
