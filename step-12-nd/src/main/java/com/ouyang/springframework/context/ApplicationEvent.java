package com.ouyang.springframework.context;

import java.util.EventObject;

/**
 * Spring基础event类
 */
public abstract class ApplicationEvent extends EventObject {


    public ApplicationEvent(Object source) {
        super(source);
    }


}
