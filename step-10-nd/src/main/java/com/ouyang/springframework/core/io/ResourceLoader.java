package com.ouyang.springframework.core.io;


/**
 *  资源加载器
 *  作用：将路径中的内容封装为资源对象
 */
public interface ResourceLoader {

    /**
     * Pseudo URL prefix for loading from the class path: "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
