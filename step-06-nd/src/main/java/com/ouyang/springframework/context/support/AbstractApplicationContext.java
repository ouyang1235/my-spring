package com.ouyang.springframework.context.support;

import com.ouyang.springframework.context.ConfigurableApplicationContext;
import com.ouyang.springframework.core.io.DefaultResourceLoader;

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
 *
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
}
