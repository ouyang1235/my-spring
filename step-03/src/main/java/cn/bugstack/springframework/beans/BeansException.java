package cn.bugstack.springframework.beans;

public class BeansException extends RuntimeException {
    public BeansException(String msg, Throwable e) {

    }

    public BeansException() {
    }

    public BeansException(String message) {
        super(message);
    }
}
