package com.ouyang.springframework.aop;

import java.lang.reflect.Method;

/**
 * 方法过滤器
 */
public interface MethodMatcher {

    boolean matches(Method method,Class<?> targetClass);

}
