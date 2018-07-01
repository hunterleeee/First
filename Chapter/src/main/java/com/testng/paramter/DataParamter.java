package com.testng.paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 参数化，可以在类中配置多个去循环执行
 */
public class DataParamter {

    @Test(dataProvider = "testData")
    public void testDataprivider(String name,int age){
        System.out.println("DataParamter.testDataprivider  name = "+name +"\t age = "+age);
    }
    @DataProvider(name = "testData")
    public Object[][] providerData(){
        Object[][] o = new Object[][]{
                {"zhangsan",10},
                {"lisi",18}
        };
        return o;
    }
}
