package com.ouyang.springframework.aop.aspectj;

import com.ouyang.springframework.aop.ClassFilter;
import com.ouyang.springframework.aop.MethodMatcher;
import com.ouyang.springframework.aop.Pointcut;

import java.lang.reflect.Method;

/**
 * 切点表达式
 * 分了半天层级，活还是丢给了我一个人做，tnnd
 *
 *
 */
public class AspectJExpressionPointcut implements Pointcut, MethodMatcher, ClassFilter {


    @Override
    public boolean matches(Class<?> clazz) {
        return false;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return false;
    }

    @Override
    public ClassFilter getClassFilter() {
        return null;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return null;
    }
}
