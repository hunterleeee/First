package com.mybatisdemo.service;

import com.mybatisdemo.dao.mapper.UserMapper;
import com.mybatisdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public List<User> getUserList(){
        return userMapper.getUserList();
    }

}
