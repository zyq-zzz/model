package model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareDate//资源类
{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            //1判断
            while (number != 0) {
                //等待，不能生产
                condition.await();
            }
            //2干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void decrement() throws Exception {
        lock.lock();
        try {
            //1判断
            while (number == 0) {
                //等待，不能生产
                condition.await();
            }
            //2干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}


/**
 * @author zyq
 * @version 1.0
 * @date 2020/4/14
 * 题目：一个初始值为零的变量，两个线程对其交替操作，一个加1一个减1，来5轮
 * 1         线程       操作（方法）    资源类
 * 2         判断       干活            通知
 * 3         防止虚假唤醒机制
 */
public class ProdConsumer_tradtionDemo {
    public static void main(String[] args) {
        ShareDate shareDate=new ShareDate();
        new Thread(()->{
            for (int i = 0; i <5; i++) {
                try {
                    shareDate.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
         },"AA").start();
        new Thread(()->{
            for (int i = 0; i <5; i++) {
                try {
                    shareDate.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 0; i <5; i++) {
                try {
                    shareDate.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
        new Thread(()->{
            for (int i = 0; i <5; i++) {
                try {
                    shareDate.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();
    }
}
