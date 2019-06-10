package com.hotj.java.algorithom.actuator.log4j;

import java.util.concurrent.*;

/**
 * @author hongjian@youzan.com
 * @date 2019/4/7
 */
public class TestMain {
    private static final ScheduledExecutorService scheduledExecutorService =
            Executors.newScheduledThreadPool(1);


    public static void main(String[] args) {
        final Runnable beeper = new Runnable() {
            @Override
            public void run() {
                System.out.println("beep"
                        + " Class:" + new Throwable().getStackTrace()[0].getClassName()
                        + " Line:" + new Throwable().getStackTrace()[0].getLineNumber());
            }
        };

        assert false;

        final ScheduledFuture<?> beeperHandler =
                scheduledExecutorService.scheduleAtFixedRate(beeper, 1, 1, TimeUnit.SECONDS);
        //scheduledExecutorService.schedule(new Runnable() {
        //    @Override
        //    public void run() {
        //        beeperHandler.cancel(true);
        //    }
        //}, 10, TimeUnit.SECONDS);


    }
}
