package model;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;

/**
 * desc 字面意思是可循环（Cyclic）使用的屏障（Barrier）。它要做的事情是，
 * 让一线线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，
 * 屏障才会开门，所有被屏障拦截的线程才会继续干活，线程进入屏障通过CyclicBarrier的await()方法。
 * @author zyq
 * @version 1.0
 * @date 2020/4/14
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("整体启动");
        });
        for (int i = 1; i <= 7; i++) {
            final int temInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t开启第" + temInt + "条");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }


}
