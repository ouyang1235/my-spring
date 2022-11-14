package com.ouyang.springframework.aop;

/**
 * 类过滤器
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);

}
