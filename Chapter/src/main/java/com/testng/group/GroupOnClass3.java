package com.testng.group;

import org.testng.annotations.Test;

@Test(groups = {"teacher","student"})
public class GroupOnClass3 {
    public void teacherAndStudent(){
        System.out.println("GroupOnClass3.teacherAndStudent  --- teacher student groups");
    }
}
