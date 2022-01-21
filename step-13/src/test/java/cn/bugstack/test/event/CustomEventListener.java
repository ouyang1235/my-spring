package cn.bugstack.test.event;

import cn.bugstack.springframework.context.ApplicationListener;

public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("接收到消息:"+event.getId()+",message:"+event.getMessage());
    }
}
