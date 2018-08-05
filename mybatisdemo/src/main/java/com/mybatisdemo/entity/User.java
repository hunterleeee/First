package com.mybatisdemo.entity;

import lombok.Data;

import java.util.Date;


//@Setter
//@Getter
//@ToString
@Data
public class User {
    private int id;
    private String name;
    private int age;
    private Date ctime;
    private Date utime;


}
