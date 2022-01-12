package cn.bugstack.springframework.context.event;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.BeanFactory;
import cn.bugstack.springframework.beans.factory.BeanFactoryAware;
import cn.bugstack.springframework.context.ApplicationEvent;
import cn.bugstack.springframework.context.ApplicationListener;
import cn.bugstack.springframework.utils.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>)listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    protected Collection<ApplicationListener> getApplicationListener(ApplicationEvent event){
        LinkedList<ApplicationListener> allListener = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            if (supportsEvent(listener,event)) allListener.add(listener);
        }
        return allListener;
    }

    /**
     * 监听器是否对该事件感兴趣
     * 用泛型类型判断
     * @param applicationListener
     * @param event
     * @return
     */
    protected  boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event){
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();

        //判断代理类
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        Type genericInterface = targetClass.getGenericInterfaces()[0];

        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try{
            eventClassName = Class.forName(className);
        }catch (ClassNotFoundException e){
            throw new BeansException("wrong event class name: " + className);
        }

        // 判定此 eventClassName 对象所表示的类或接口与指定的 event.getClass() 参数所表示的类或接口是否相同，或是否是其超类或超接口。
        // isAssignableFrom是用来判断子类和父类的关系的，或者接口的实现类和接口的关系的，默认所有的类的终极父类都是Object。如果A.isAssignableFrom(B)结果是true，证明B可以转换成为A,也就是A可以由B转换而来。
        return eventClassName.isAssignableFrom(event.getClass());

    }

}
