package com.example.house.controller;

import com.example.house.entity.UserEntity;
import com.example.house.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loginPage")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public UserEntity login(String account, String password){//调用业务处理层检查函数，对account, password检验
        return loginService.loginCheck(account, password);
    }

    @RequestMapping("/register")
    public String register(UserEntity user){//传入一个user数据，判断是否可以插入到用户表里（用户名是否已存在）
        return loginService.register(user);
    }
}
