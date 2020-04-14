package model;

import java.util.concurrent.*;

/**
 * @author zyq
 * @version 1.0
 * @date 2020/4/14
 * 第四种获得/使用java多线程的方式，线程池
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        /*ExecutorService threadPool= Executors.newCachedThreadPool();*/
        /*先查看一下cpu的核数
        * CPU密集的意思是该任务需要大量的运算，面没有阻塞，CPU一直处理全速运行
        * CPU密集任务只有在真正的多核CPU上才可能得到加速（通过多线程）
        * 而在单核CPU（悲剧吧）无论你开几个模拟的多线程该任务都不可能得到加速，因为CPU总的运算能力就那些
        *
        * IO密集型的
        * 1、如果是IO密集型任务线程并不是一直在执行任务，则应配置尽可能多的线程，如CPU核心数*2
        * 2、CPU核心数/1-阻塞系数   阻塞系数在0.8 ~0.9之间
        * 比如8核CPU：8/1-0.9=80个线程数
        * */

        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool= new ThreadPoolExecutor(
                2,
                5,
                1L ,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),

                /*
                new ThreadPoolExecutor.AbortPolicy()  =========java.util.concurrent.RejectedExecutionException
                new ThreadPoolExecutor.CallerRunsPolicy()
                new ThreadPoolExecutor.DiscardOldestPolicy()*/
                new ThreadPoolExecutor.DiscardPolicy());
        //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 1; i <=9; i++) {
                final int maxIntmax=i;
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t办理业务"+maxIntmax);
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
