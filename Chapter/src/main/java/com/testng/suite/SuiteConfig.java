package com.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

/**
 * 测试套件执行之前执行的哪些方法，注意可以执行多个方法，如下结果
 * BeforeSuite1
 * BeforeSuite2
 * LogInTest-loginSOHU ——第1个类的测试
 * LogInTest-loginTaobao —— 第1个类的测试
 * PayTest-paySucess ——第二个类的测试方法
 * AfterSuite
 */
public class SuiteConfig {
    /**
     * 在测试套件之前执行
     */
    @BeforeSuite
    public void BeforeSuite1(){
        System.out.println("SuiteConfig.BeforeSuite1");
    }
    @BeforeSuite
    public void BeforeSuite2(){
        System.out.println("SuiteConfig.BeforeSuite2");
    }

    @AfterSuite
    public void AfterSuite(){
        System.out.println("SuiteConfig.AfterSuite");
    }

    /**
     * 在每个test tag之前执行，具体查看配置文件
     */
    @BeforeTest
    public void beforeTest(){
        System.out.println("SuiteConfig.beforeTest");
    }
    @AfterTest
    public void afterTeset(){
        System.out.println("SuiteConfig.afterTeset");
    }

}
