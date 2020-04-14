package model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * desc 同步队列每次只能进一个，
 * 只有待消费完毕之后才能放第二个
 * @author zyq
 * @version 1.0
 * @date 2020/4/14
 */
public class SynchronousQueueDemo {
    public static void main(String[] args)throws Exception {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\tput a");
                blockingQueue.put("a");
                System.out.println(Thread.currentThread().getName() + "\tput b");
                blockingQueue.put("b");
                System.out.println(Thread.currentThread().getName() + "\tput c");
                blockingQueue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();
        new Thread(() -> {
            try {
                try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+"\ttake "+blockingQueue.take());
                try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+"\ttake "+blockingQueue.take());
                try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+"\ttake "+blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();
    }
}
