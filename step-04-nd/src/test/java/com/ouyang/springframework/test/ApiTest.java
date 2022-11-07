package com.ouyang.springframework.test;

import com.ouyang.springframework.beans.PropertyValue;
import com.ouyang.springframework.beans.PropertyValues;
import com.ouyang.springframework.beans.factory.config.BeanDefinition;
import com.ouyang.springframework.beans.factory.config.BeanReference;
import com.ouyang.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.ouyang.springframework.test.bean.UserDao;
import com.ouyang.springframework.test.bean.UserService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ApiTest {


    @Test
    public void test_BeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //注册UserDao
        beanFactory.registerBeanDefinition("userDao",new BeanDefinition(UserDao.class));
        //注册UserService
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId","10002"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));
        BeanDefinition userServiceDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService",userServiceDefinition);

        //获取
        UserService userService = (UserService)beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
