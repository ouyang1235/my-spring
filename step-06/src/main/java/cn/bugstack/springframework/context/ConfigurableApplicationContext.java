package cn.bugstack.springframework.context;

import cn.bugstack.springframework.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     * 主要功能：
     * 1.实现读取文件加载Definition到BeanFactory
     * 2.实现自定义修改BeanDefinition
     * 3.实现自定义在Bean的初始化前后进行操作
     * @throws BeansException
     */
    void refresh() throws BeansException;

}
