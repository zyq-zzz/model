package model;

import java.io.InterruptedIOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * desc 信号量主要用于两个目的
 * 一个是用于多个共享资源的互斥使用
 * 另一个用于并发线程数的控制
 *
 * @author zyq
 * @version 1.0
 * @date 2020/4/14
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢到车位");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t停车3秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
