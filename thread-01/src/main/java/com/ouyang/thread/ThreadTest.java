package com.ouyang.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j(topic = "c.ThreadTest")
public class ThreadTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                log.debug("running");
            }
        };
        thread.setName("t1");
        thread.start();
        log.debug("running");

        Thread thread1 = new Thread(() -> log.debug("hello"),"t2");
        thread1.start();


        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            log.debug("callable running...");
            return 200;
        });
        new Thread(futureTask,"t3").start();
        log.debug(String.valueOf(futureTask.get()));
    }
}
