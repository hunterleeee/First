package com.testng.paramter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 测试的参数化，可以在配置文件中配置，查看paramterTest.xml配置
 */
public class ParamterTest {
    @Test
    @Parameters({"name","age"})
    public void parmTest1(String name,int age){
        System.out.println("ParamterTest.parmTest1  name = "+name +"\t age = "+age);
    }
}
