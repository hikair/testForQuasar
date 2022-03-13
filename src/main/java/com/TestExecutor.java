package com;

import java.util.concurrent.*;

public class TestExecutor {

    private static final Integer THREAD_COUNT = 10000;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(10000);
        long start = System.currentTimeMillis();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2000, 2000, 5, TimeUnit.SECONDS, workQueue, new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPoolExecutor.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println(String.format("cost: %s ms", end - start));
    }
}
