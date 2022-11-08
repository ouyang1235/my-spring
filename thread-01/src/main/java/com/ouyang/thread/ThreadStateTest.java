package com.ouyang.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.ThreadStateTest")
public class ThreadStateTest {

    public static void main(String[] args) {
        //new
        Thread t1 = new Thread(() -> {

        }, "t1");

        //terminated
        Thread t7 = new Thread(() -> {

        }, "t7");
        t7.start();

        //runnable
        Thread t2 = new Thread(() -> {
            while (true){

            }
        }, "t2");
        t2.start();

        //timed-waiting
        Thread t3 = new Thread(() -> {
            try {
                Thread.sleep(16000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t3");
        t3.start();

        //waiting
        Thread t4 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t4");
        t4.start();

        Thread t5 = new Thread(() -> {
            synchronized (ThreadStateTest.class){
                while (true){

                }
            }
        }, "t5");
        t5.start();

        //blocked
        Thread t6 = new Thread(() -> {
            synchronized (ThreadStateTest.class){
                while (true){

                }
            }
        }, "t6");
        t6.start();

        log.info("t1 state:{}",t1.getState());
        log.info("t2 state:{}",t2.getState());
        log.info("t3 state:{}",t3.getState());
        log.info("t4 state:{}",t4.getState());
        log.info("t6 state:{}",t6.getState());
        log.info("t7 state:{}",t7.getState());

    }

}
