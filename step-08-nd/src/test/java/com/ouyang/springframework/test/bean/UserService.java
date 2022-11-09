package com.ouyang.springframework.test.bean;

import com.ouyang.springframework.beans.BeansException;
import com.ouyang.springframework.beans.factory.*;
import com.ouyang.springframework.context.ApplicationContext;
import com.ouyang.springframework.context.ApplicationContextAware;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
public class UserService implements InitializingBean, DisposableBean, BeanFactoryAware, BeanClassLoaderAware, BeanNameAware, ApplicationContextAware {

    private String uId;
    private String company;
    private String location;
    private UserDao userDao;

    private ClassLoader classLoader;
    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;
    private String beanName;

    public String queryUserInfo() {
        return userDao.queryUserName(uId) + "," + company + "," + location;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：销毁方法.....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：初始化方法...");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("获取到了感知类加载器..");
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("获取到了感知bean工厂..");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("获取到了感知beanName..");
        this.beanName = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("获取到了感知应用上下文..");
        this.applicationContext = applicationContext;
    }
}
