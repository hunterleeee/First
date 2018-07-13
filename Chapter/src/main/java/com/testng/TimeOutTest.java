package com.testng;

import org.testng.annotations.Test;

/**
 * 超时测试使用    @Test(timeOut = 3000) 控制
 */
public class TimeOutTest {
    @Test(timeOut = 3000)
    public  void  successTest() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("success Test");
    }

    @Test(timeOut = 1000)
    public  void  failedTest() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("failed Test");

    }
}
