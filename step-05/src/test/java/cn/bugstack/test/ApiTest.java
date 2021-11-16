package cn.bugstack.test;



import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.PropertyValue;
import cn.bugstack.springframework.beans.PropertyValues;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;
import cn.bugstack.springframework.beans.factory.config.BeanReference;
import cn.bugstack.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.bugstack.test.bean.UserDao;
import cn.bugstack.test.bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_BeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(UserDao.class);
        beanFactory.registerBeanDefinition("userDao",beanDefinition);

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name","10002"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));
        BeanDefinition beanDefinitionService = new BeanDefinition(UserService.class,propertyValues);
        beanFactory.registerBeanDefinition("userService",beanDefinitionService);
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }


}
