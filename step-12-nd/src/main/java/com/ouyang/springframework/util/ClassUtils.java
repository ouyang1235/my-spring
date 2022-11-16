package com.ouyang.springframework.util;

/**
 * 10更新：
 * 增加判断对类是否是Cglib代理类的判断
 */
public class ClassUtils {


    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try{
            cl = Thread.currentThread().getContextClassLoader();
        }catch (Throwable ex){
            //
        }
        if (cl == null){
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

    public static boolean isCglibProxyClass(Class<?> clazz){
        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

    public static boolean isCglibProxyClassName(String className){
        return (className != null && className.contains("$$"));
    }

}
