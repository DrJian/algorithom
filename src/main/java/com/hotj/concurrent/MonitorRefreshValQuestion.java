package com.hotj.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程进入 Synchronized 临界区会重新刷新load的数据，验证是否成立
 *
 * @author hongjian@youzan.com
 * @date 2019/4/25
 */
public class MonitorRefreshValQuestion {
    private int val = 0;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int i = 1;
        MonitorRefreshValQuestion monitorRefreshValQuestion = new MonitorRefreshValQuestion();
        while (i++ <= 10) {
            executorService.execute(() -> {
                for (int j = 1; j <= 10000000; j++) {
                    monitorRefreshValQuestion.increase();
                }

                countDownLatch.countDown();
            });
        }

        countDownLatch.await();

        //这里输出的结果是1 0000 0000
        //上面输出的结果，符合线程安全的结果，这里印证了新JMM模型以及JSR133提案里的一个定理
        // 当进入monitor区域时，monitor区域内要用到的变量会重新load到其最新的值
        // 退出monitor区域时，会强制刷新本次所有的写缓冲
        System.out.println(monitorRefreshValQuestion.val
                + " Class:" + new Throwable().getStackTrace()[0].getClassName()
                + " Line:" + new Throwable().getStackTrace()[0].getLineNumber());

        //线程池需要关闭，否则主线程无法退出，因为main线程是后续所有其他线程的守护线程，在守护线程的线程尚有存活时，守护线程不会退出。
        executorService.shutdown();
    }

    private void increase() {
        synchronized (this) {
            val++;
        }
    }
}
