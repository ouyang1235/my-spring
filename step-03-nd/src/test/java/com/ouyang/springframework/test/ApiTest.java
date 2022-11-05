package com.ouyang.springframework.test;

import com.ouyang.springframework.beans.factory.config.BeanDefinition;
import com.ouyang.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.ouyang.springframework.test.bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_BeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        //获取
        UserService userService = (UserService) beanFactory.getBean("userService", "欧阳！");
        userService.queryUserInfo();

    }
}
