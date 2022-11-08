package com.ouyang.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.test3")
public class Test3 {


    public static void main(String[] args) throws Exception {

//        Thread t1 = new Thread(() -> {
//            while (true) {
//                if (Thread.currentThread().isInterrupted()) {
//                    log.info("t1 has been interrupted...");
//                    break;
//                } else {
//                    log.info("t1 is going on...");
//                }
//            }
//        }, "t1");
//
//        t1.start();
//
//        Thread.sleep(1000);
//        log.info("interrupting t1...");
//        t1.interrupt();
        twoPhaseTerminationTest();

    }

    public static void twoPhaseTerminationTest() throws InterruptedException {
        TwoPhaseTermination obj = new TwoPhaseTermination();
        obj.start();
        Thread.sleep(3500);
        obj.stop();
    }
}
