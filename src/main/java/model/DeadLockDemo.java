package model;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t自己持有："+lockA+"\t尝试获得"+lockB);
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t自己持有："+lockB+"\t尝试获得"+lockA);
            }
        }
    }
}
/**
 * @author zyq
 * @version 1.0
 * @date 2020/4/15
 * desc 死锁是指两个或两个以上的进程在执行的过程中，
 * 因争夺资源面造成的一种互相等待的现象，
 * 若无外力的干涉那它们都将无法推进下去
 * jps查看进程号
 * jstack(找到死锁)
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA="lockA";
        String lockB="lockB";
        new Thread(new HoldLockThread(lockA,lockB),"ThreadAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"ThreadBB").start();
    }
}
