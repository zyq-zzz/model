package model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * desc 队列
 * 当阻塞队列为空时，从队列中获取元素的操作被阻塞
 * 当阻塞队列是满时，往队列中添加元素的操作被阻塞
 *
 * @author zyq
 * @version 1.0
 * @date 2020/4/14
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws Exception {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        /*  System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //add 多于队列长度会报java.lang.IllegalStateException: Queue full异常
        //remove队列为空时直接报NoSuchElementException异常
       System.out.println(blockingQueue.add("d"));
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.element());*/
        //存值（返回true或false）
       /* System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        //取值（返回数据或null）
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        //检查
        blockingQueue.peek();*/
    /*   //put 如果多于队列长度的话线程一直等待
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        //take 如果队列为空时再取就一直等待
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();*/
        //加时间取折中法设置时间段，如时间如果成功就成功，如果失败就false
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
    }
}
