package com.testng.group;

import org.testng.annotations.Test;

@Test(groups = "teacher")
public class GroupOnClass2 {
    public void teacher1(){
        System.out.println("GroupOnClass2.teacher1 ---  teacher group");
    }
    public void teacher2(){
        System.out.println("GroupOnClass2.teacher2 ---  teacher group");
    }
}
