package cn.bugstack.test;



import cn.bugstack.springframework.aop.AdvisedSupport;
import cn.bugstack.springframework.aop.MethodMatcher;
import cn.bugstack.springframework.aop.TargetSource;
import cn.bugstack.springframework.aop.aspectj.AspectJExpressionPointcut;
import cn.bugstack.springframework.aop.framework.Cglib2AopProxy;
import cn.bugstack.springframework.aop.framework.JdkDynamicAopProxy;
import cn.bugstack.springframework.aop.framework.ReflectiveMethodInvocation;
import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.PropertyValue;
import cn.bugstack.springframework.beans.PropertyValues;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;
import cn.bugstack.springframework.beans.factory.config.BeanReference;
import cn.bugstack.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.bugstack.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import cn.bugstack.springframework.context.ApplicationContext;
import cn.bugstack.springframework.context.support.ClassPathXmlApplicationContext;
import cn.bugstack.springframework.core.io.DefaultResourceLoader;
import cn.bugstack.springframework.core.io.Resource;
import cn.bugstack.test.bean.IUserService;
import cn.bugstack.test.bean.UserDao;
import cn.bugstack.test.bean.UserService;
import cn.bugstack.test.bean.UserServiceInterceptor;
import cn.bugstack.test.common.MyBeanFactoryPostProcessor;
import cn.bugstack.test.common.MyBeanPostProcessor;
import cn.bugstack.test.event.CustomEvent;
import cn.hutool.core.io.IoUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Before;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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

    private DefaultResourceLoader resourceLoader;

    @Before
    public  void init(){
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_classpath()throws IOException{

    }

    @Test
    public void test_xml() {
        UserService userService = new UserService();
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* cn.bugstack.test.bean.IUserService.*(..))"));

        IUserService proxy = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println(proxy.queryUserInfo());

        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_cglib.register("花花"));
    }

    @Test
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor(){
        //1.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //2.读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");
        //3.BeanDefinition 加载完成 & Bean 实例化之前，修改BeanDefinition的属性值
        MyBeanFactoryPostProcessor myBeanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        myBeanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        //4.Bean实例化之后，修改Bean属性值
        MyBeanPostProcessor myBeanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(myBeanPostProcessor);
        //5.获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("result:"+result);
    }

    @Test
    public void test_xmlPro(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("result:"+result);
    }

    @Test
    public void test_proxy() throws NoSuchMethodException {
        UserService targetObject = new UserService();
        IUserService proxy = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObject.getClass().getInterfaces(), new InvocationHandler() {

            // 方法匹配器
            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* cn.bugstack.test.bean.IUserService.*(..))");

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (methodMatcher.matches(method, targetObject.getClass())) {
                    MethodInterceptor methodInterceptor = invocation -> {
                        long start = System.currentTimeMillis();
                        try {
                            return invocation.proceed();
                        } finally {
                            System.out.println("监控 - Begin By AOP");
                            System.out.println("方法名称：" + invocation.getMethod().getName());
                            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
                            System.out.println("监控 - End\r\n");
                        }
                    };
                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObject, method, args));
                }
                return method.invoke(targetObject, args);
            }
        });

        String s = proxy.queryUserInfo();
        System.out.println(s);


    }

}
