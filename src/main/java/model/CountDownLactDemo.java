package model;

import emum.CountTryEnum;
import lombok.Data;

import java.util.concurrent.CountDownLatch;

/**
 * desc 倒计时，初始化一个数据，
 * （countDown()）每次减一，
 * （await()）直到0，才能往下走
 * @author zyq
 * @version 1.0
 * @date 2020/4/13
 */
@Data
public class CountDownLactDemo {
    public static void main(String[] args) throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(4);
        for (int i = 1; i <= 4; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t过去了");
                countDownLatch.countDown();
            }, CountTryEnum.forEach_CountryEnum(i).getDesc()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t***********一年线束");
    }
}
