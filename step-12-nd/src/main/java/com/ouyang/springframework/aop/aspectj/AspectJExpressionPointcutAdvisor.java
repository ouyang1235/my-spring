package com.ouyang.springframework.aop.aspectj;

import com.ouyang.springframework.aop.Pointcut;
import com.ouyang.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * 整合了切点和织入方法的类
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut;

    private Advice advice;

    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut){
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
