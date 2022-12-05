package com.ouyang.springframework.aop.framework;

import com.ouyang.springframework.aop.AdvisedSupport;

/**
 * 代理工厂
 *
 * 根据AdvisedSupport来决定使用cglib还是jdk代理
 *
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy(){
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy(){
        if (advisedSupport.isProxyTargetClass()){
            return new Cglib2AopProxy(advisedSupport);
        }else {
            return new JdkDynamicAopProxy(advisedSupport);
        }
    }
}
