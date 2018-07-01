package com.testng.suite;

import org.testng.annotations.Test;

public class IgnoreTest {

    @Test
    public void ignore1(){
        System.out.println("IgnoreTest.ignore1");
    }

    /**
     * enable=false  不执行该测试
     */
    @Test(enabled = false)
    public void ignore2(){
        System.out.println("IgnoreTest.ignore2");
    }

    @Test(enabled = true)
    public void ignore3(){
        System.out.println("IgnoreTest.ignore3");
    }
}
