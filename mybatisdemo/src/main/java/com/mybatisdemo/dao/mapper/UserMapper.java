package com.mybatisdemo.dao.mapper;

import com.mybatisdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getUserList();

}
