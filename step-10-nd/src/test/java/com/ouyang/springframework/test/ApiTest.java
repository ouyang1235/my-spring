package com.ouyang.springframework.test;

import com.ouyang.springframework.context.support.ClassPathXmlApplicationContext;
import com.ouyang.springframework.test.bean.UserService;
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
}
