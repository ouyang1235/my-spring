package cn.bugstack.springframework.beans;

public class BeansException extends Throwable {
    public BeansException(String msg, Throwable e) {

    }

    public BeansException() {
    }

    public BeansException(String message) {
        super(message);
    }
}
