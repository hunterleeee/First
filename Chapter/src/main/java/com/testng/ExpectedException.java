package com.testng;

import org.testng.annotations.Test;

/**
 * 期望结果为某个异常时使用，如边界值测试时
 * @Test(expectedExceptions = RuntimeException.class)
 */
public class ExpectedException {

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed(){
        System.out.println("失败的异常测试");
    }
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess(){
        System.out.println("成功的异常测试");
        throw new RuntimeException("");
    }
}
