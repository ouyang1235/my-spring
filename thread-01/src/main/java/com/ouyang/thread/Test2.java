package com.ouyang.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test2")
public class Test2 {

    private static  int r = 0;

    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(() -> {
            try {
                log.info("t1 start");
                Thread.sleep(2000);
                r= 10;
                log.info("t1 end");
            } catch (InterruptedException e) {
                log.info("t1 wake up");
                e.printStackTrace();
            }
        }, "t1");

        t1.start();
        t1.join();
        log.info("r:{}",r);



    }

}
