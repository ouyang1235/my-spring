package com.ouyang.thread;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TwoPhaseTermination")
public class TwoPhaseTermination {
    private Thread monitor;

    public void start(){
        monitor = new Thread(()->{
            while (true){
                Thread thread = Thread.currentThread();
                if (thread.isInterrupted()){
                    log.debug("料理后事");
                    break;
                }
                try {
                    Thread.sleep(2000);
                    log.debug("执行监控记录...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    stop();
                }
            }
        });
        monitor.start();
    }

    public void stop(){
        monitor.interrupt();
    }


}
