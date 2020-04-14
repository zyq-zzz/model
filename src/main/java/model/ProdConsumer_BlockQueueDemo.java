package model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
    private volatile boolean FLAG=true;//默认开启进行生产+消费
    private AtomicInteger atomicInteger=new AtomicInteger();
    BlockingQueue<String> blockingQueue=null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }
    public void myprod()throws Exception{
        String date=null;
        boolean reValue;
        while (FLAG){
            date=atomicInteger.incrementAndGet()+"";
            reValue=blockingQueue.offer(date,2L, TimeUnit.SECONDS);
            if (reValue){
                System.out.println(Thread.currentThread().getName()+"\t插入队列"+date+"成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t插入队列"+date+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t大老板叫挺了，表示FLAG=false,生产动作结束");
    }
    public void myConsumer()throws Exception{
        String result=null;
        while (FLAG){
            result=blockingQueue.poll(2L,TimeUnit.SECONDS);
            if (null==result||result.equalsIgnoreCase("")){
                FLAG=false;
                System.out.println(Thread.currentThread().getName()+"\t 超过2秒钟没有取到数据，消费退出");
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t消费队列"+result+"成功");
        }
    }
    public void stop()throws Exception{
        this.FLAG=false;
    }
}

/**
 * @author zyq
 * @version 1.0
 * @date 2020/4/14
 * volatile/CAS/atomicInteger/BlockQueue/线程交互
 */
public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args)throws Exception {
      MyResource myResource=new MyResource(new ArrayBlockingQueue<>(10));
      new Thread(()->{
          System.out.println(Thread.currentThread().getName()+"\t生产线程启动");
          try {
              myResource.myprod();
          } catch (Exception e) {
              e.printStackTrace();
          }
      },"prpd").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t消费线程启动");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"myCon").start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5秒钟时间到，大老板main线程叫停，活动结束");
        myResource.stop();
    }
}
