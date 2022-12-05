package com.ouyang.springframework.aop;

import java.lang.reflect.Method;

/**
 * 前置切入方法
 * 在原方法之前执行
 */
public interface MethodBeforeAdvice extends BeforeAdvice{

    void before(Method method, Object[] args,Object target) throws Throwable;
}
