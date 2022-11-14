package com.ouyang.springframework.aop;

/**
 * 切点
 * 需要进行方法增强的点，相当于过滤器，但是它不直接进行甄别，而是拥有过滤器，用过滤器来甄别
 *
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
