package  com.testng;
import org.testng.annotations.*;

public class BaseAnnotation {

    @Test //测试用例注解
    public void testcase1(){
        System.out.println("BaseAnnotation.testcase1");
    }

    @Test
    public void testcase2(){
        System.out.println("BaseAnnotation.testcase2");

    }
    @BeforeClass //在类测试方法执行前运行
    public void  BeforeClass(){
        System.out.println("BeforeClass BaseAnnotation.testcasexxx");
    }
    @AfterClass
    public void  AfterClass(){
        System.out.println("AfterClass BaseAnnotation.testcasexxx");
    }

    @BeforeSuite //在类运行之前 执行，so多个clasee组成测试套件
    public void BeforeSuite(){
        System.out.println("BeforeSuite");
    }

    @AfterSuite
    public void AfterSuite(){
        System.out.println("AfterSuite");
    }



    @BeforeMethod //每个测试方法之前都会执行
    public  void BeforeMethod(){
        System.out.println("BeforeMethod testcase1");
    }
    @AfterMethod //每个测试方法之后都会执行
    public  void AfterMethod(){
       System.out.println("AfterMethod testcase1");
    }
}
