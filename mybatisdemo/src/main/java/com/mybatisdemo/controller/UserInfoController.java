package com.mybatisdemo.controller;

import com.mybatisdemo.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserInfoController {


    @RequestMapping(value = "getusers",method = RequestMethod.GET)
    public User getUserlist(){
        User user=new User();
        user.setId(1);
        user.setName("lijunfeng");
        user.setAge(18);

        System.out.println(user);
        return user;
    }






}
