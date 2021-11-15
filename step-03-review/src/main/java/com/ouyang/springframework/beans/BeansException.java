package com.ouyang.springframework.beans;

public class BeansException extends RuntimeException{
    public BeansException() {
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeansException(String message) {
        super(message);
    }
}
