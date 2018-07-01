package com.testng;

import org.testng.annotations.Test;

/**
 * 依赖测试。test1和test2执行前需要先执行test3
 * 依赖测试 分方法依赖，test依赖 group依赖 此处一方法依赖举例
 * 测试结果为Total tests run: 3, Failures: 1, Skips: 2
 */
public class DependTest {
    @Test(dependsOnMethods = {"test3"})
    public void test1(){
        System.out.println("DependTest.test1");
    }
    @Test(dependsOnMethods = "test3")
    public void test2(){
        System.out.println("DependTest.test2");
    }
    @Test
    public void test3(){
        System.out.println("DependTest.test3");
        throw  new  RuntimeException();
    }

}
