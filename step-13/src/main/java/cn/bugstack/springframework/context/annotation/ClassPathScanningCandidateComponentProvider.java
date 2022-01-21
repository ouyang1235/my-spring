package cn.bugstack.springframework.context.annotation;

import cn.bugstack.springframework.beans.factory.config.BeanDefinition;
import cn.bugstack.springframework.steretype.Component;
import cn.hutool.core.util.ClassUtil;

import java.util.LinkedHashSet;
import java.util.Set;

public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponent(String basePackage){
        LinkedHashSet<BeanDefinition> candidate = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidate.add(new BeanDefinition(clazz));
        }
        return candidate;
    }


}
