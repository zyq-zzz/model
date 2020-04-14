package model;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class MyThread implements Runnable {

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("come in callable");
        return 1024;
    }
}

/**
 * @author zyq
 * @version 1.0
 * @date 2020/4/14
 */
public class CallableDemo {
    public static void main(String[] args)throws Exception {
        FutureTask futureTask = new FutureTask(new MyThread2());
        Thread t1 = new Thread(futureTask, "AA");
        t1.start();
        //futureTask.get()要求获得Callable的计算结果，如果没有计算完成就要去强求，会导致堵塞，直到计算完成。
        System.out.println("result==="+futureTask.get());

        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
