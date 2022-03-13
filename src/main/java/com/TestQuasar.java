package com;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableCallable;
import co.paralleluniverse.strands.SuspendableRunnable;

import java.util.concurrent.CountDownLatch;

public class TestQuasar {

    private static final Integer THREAD_COUNT = 10000;

//    public static void main(String[] args) throws InterruptedException {
//        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < THREAD_COUNT; i++) {
//            new Fiber<>(new SuspendableRunnable() {
//                @Override
//                public void run() throws SuspendExecution, InterruptedException {
//                    Fiber.sleep(1000);
//                    countDownLatch.countDown();
//                }
//            }).start();
//        }
//        countDownLatch.await();
//        long end = System.currentTimeMillis();
//        System.out.println(String.format("cost: %s ms", end - start));
//    }

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch=new CountDownLatch(10000);
        for (int i = 0; i < 1000000; i++) {
            int finalI = i;
            new Fiber<>((SuspendableCallable<Integer>)()->{
                Fiber.sleep(100000);
                countDownLatch.countDown();
                return finalI;
            }).start();
        }
        countDownLatch.await();
        System.out.println("end");
    }
}
