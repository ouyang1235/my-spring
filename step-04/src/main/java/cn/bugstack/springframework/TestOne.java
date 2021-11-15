package cn.bugstack.springframework;



import cn.bugstack.springframework.beans.PropertyValue;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;

import java.util.ArrayList;

public class TestOne {
    public static void main(String[] args) {
        ArrayList<BeanDefinition> list = new ArrayList<>();
        BeanDefinition beanDefinition = new BeanDefinition(String.class);
        list.add(beanDefinition);
        list.add(beanDefinition);
        beanDefinition.setBeanClass(Object.class);

        System.out.println(list);
    }
}
