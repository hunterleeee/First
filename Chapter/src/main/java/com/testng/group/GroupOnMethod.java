package com.testng.group;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/**
 * 测试组测试，注意看testGroup.xml文件 如何只执行server group
 *
 */
public class GroupOnMethod {

    @Test(groups = "server")
    public void test1(){
        System.out.println("GroupOnMethod.test1----server group");
    }
    @Test(groups = "client")
    public void test3(){
        System.out.println("GroupOnMethod.test3----client group");
    }
    @Test(groups = "server")
    public void test2(){
        System.out.println("GroupOnMethod.test2----server group");
    }
    @Test(groups = {"client"})
    public void test4(){
        System.out.println("GroupOnMethod.test4----client group");
    }

    @BeforeGroups("server")
    public  void beforeGroupTest(){
        System.out.println("GroupOnMethod.beforeGroupTest----server group");
    }
    @AfterGroups("server")
    public  void afterGroupTest(){
        System.out.println("GroupOnMethod.afterGroupTest----server group");
    }

    @Test(groups = {"client","server"})
    public void test5(){
        System.out.println("GroupOnMethod.test4----client and server group");
    }
}
