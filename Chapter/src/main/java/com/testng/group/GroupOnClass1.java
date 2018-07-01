package com.testng.group;

import org.testng.annotations.Test;

@Test(groups = "student")
public class GroupOnClass1 {

    public void student1(){
        System.out.println("GroupOnClass1.student1 ---student group");
    }
    public void student2(){
        System.out.println("GroupOnClass1.student2 ---student group");
    }
}
