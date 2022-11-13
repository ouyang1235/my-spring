package com.ouyang.springframework.beans.factory.support;




import com.ouyang.springframework.beans.BeansException;
import com.ouyang.springframework.beans.factory.FactoryBean;
import com.ouyang.springframework.beans.factory.config.BeanDefinition;
import com.ouyang.springframework.beans.factory.config.BeanPostProcessor;
import com.ouyang.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.ouyang.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 顶层beanFactory抽象类
 * 使用模板模式，规定了获取bean对象的流程：
 * 1.获取单例对象
 * 2.如果未获取到则先获取beanDefinition
 * 3.根据定义获取bean对象
 *
 * **获取单例对象、获取bean定义、创建bean对象的实现交给了其他类，自己并不完成，只做模板策划的本职工作
 *
 * 06更新：
 * 1.添加了一个List用于存放注册的BeanPostProcessor，新增一个获取这个列表的方法
 * 2.实现接口更改： BeanFactory----> ConfigurableBeanFactory，相当于需要实现的功能增加：添加BeanPostProcessor
 *
 * 08更新：
 * 新增了getClassLoader()方法，来获取类加载器
 *
 * 09更新:
 * 继承类型DefaultSingletonBeanRegistry --->FactoryBeanRegistrySupport
 * doGetBean流程中增加了对FactoryBean类型的判断,优化了存储和取出的逻辑
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name,null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name,args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(final String name, final Object[] args){
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null){
            //如果是 FactoryBean，则需要调用 FactoryBean#getObject
            return (T)getObjectForBeanInstance(sharedInstance,name);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean,name);
    }

    private Object getObjectForBeanInstance(Object beanInstance,String beanName){
        if (!(beanInstance instanceof FactoryBean)){
            return beanInstance;
        }

        Object object = getCacheObjectForFactoryBean(beanName);
        if (object == null){
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean,beanName);
        }

        return object;
    }

    protected abstract BeanDefinition getBeanDefinition(String name) throws BeansException;

    protected  abstract Object createBean(String name,BeanDefinition beanDefinition,Object[] args) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors(){
        return this.beanPostProcessors;
    }

    public ClassLoader getBeanClassLoader() {
        return beanClassLoader;
    }
}
