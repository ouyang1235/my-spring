package com.ouyang.springframework.test;

import com.ouyang.springframework.aop.AdvisedSupport;
import com.ouyang.springframework.aop.TargetSource;
import com.ouyang.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.ouyang.springframework.aop.framework.Cglib2AopProxy;
import com.ouyang.springframework.aop.framework.JdkDynamicAopProxy;
import com.ouyang.springframework.context.support.ClassPathXmlApplicationContext;
import com.ouyang.springframework.test.bean.IUserService;
import com.ouyang.springframework.test.bean.UserService;
import com.ouyang.springframework.test.bean.UserServiceInterceptor;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_BeanFactory_xml(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService.queryUserInfo());
        System.out.println(context.getClass().getSuperclass().getName());
        System.out.println(context.getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getName());
    }

    @Test
    public void test_proxy(){
        UserService userService = new UserService();

        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.ouyang.springframework.test.bean.IUserService.*(..))"));

        IUserService proxy = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println("result:" + proxy.queryUserInfo());

        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println("result2:" + proxy_cglib.register("huhu"));

//        UserService serv = (UserService) proxy;
//        System.out.println(serv.name);
//        System.out.println(serv.getName());

        UserService serv2 = (UserService) proxy_cglib;
        System.out.println(serv2.name);
        System.out.println(serv2.getName());
    }
}
