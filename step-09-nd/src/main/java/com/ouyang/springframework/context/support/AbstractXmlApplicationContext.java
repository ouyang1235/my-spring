package com.ouyang.springframework.context.support;

import com.ouyang.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.ouyang.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 *  * Convenient base class for {@link com.ouyang.springframework.context.ApplicationContext}
 *  * implementations, drawing configuration from XML documents containing bean definitions
 *  * understood by an {@link XmlBeanDefinitionReader}.
 *
 *  规定了基于xml文件解析bean定义的context如何加载资源、加载bean定义
 *
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinition(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();

}
