package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * post请求 包括表单和json  包括有cookie和没有cookie和返回cookie信息
 */
@RestController
@RequestMapping(value = "/v1")
@Api(value = "/",description = "我的post 请求接口！！")
public class MyPostMethod {
    private static Cookie cookie ;

    /**
     * 创建form表单的post请求
     * @param response
     * @param userName
     * @param passWord
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录post接口，成功后获取cookies")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName",required = true) String userName,
                        @RequestParam(value = "passWord",required = true) String passWord){
        if(userName.equals("zhangsan")&&passWord.equals("123456")){
            cookie= new Cookie("login","true");
            response.addCookie(cookie);
            return "login success!!";
        }
        return  "登录失败";
    }

    /**
     * 使用json形式的post接口 需要验证cookie的
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(value = "/getuserlist",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表的接口",httpMethod = "post")
    public String getUserList(HttpServletRequest request, @RequestBody User user){
        User u =new User();
        u.setAge("18");
        u.setName("张三");
        u.setSex("men");
        Cookie []cookies= request.getCookies();
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("zhangsan")&&cookie.getValue().equals("true")
                    &&user.getUserName().equals("zhangsan")
                    &&user.getPassWord().equals("123456")){
                return u.toString();
            }
        }
        return "你的请求信息不正确";
    }

}
