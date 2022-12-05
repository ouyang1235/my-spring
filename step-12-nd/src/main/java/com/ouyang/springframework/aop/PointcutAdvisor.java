package com.ouyang.springframework.aop;

/**
 * 切点访问者
 *
 * 多了一个持有切点的功能
 */
public interface PointcutAdvisor extends Advisor{


    Pointcut getPointcut();
}
