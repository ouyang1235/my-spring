package com.ouyang.springframework.beans.factory;

/**
 * 获取bean的类加载器的感知接口
 */
public interface BeanClassLoaderAware extends Aware{

    void setBeanClassLoader(ClassLoader classLoader);

}
