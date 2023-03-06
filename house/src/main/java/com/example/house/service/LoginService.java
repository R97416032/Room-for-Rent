package com.example.house.service;


import com.example.house.entity.UserEntity;
import com.example.house.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;

    /*
    根据账户、密码检查用户是否存在，存在返回用户类型，否则返回-1
    * */
    public UserEntity loginCheck(String account, String password){
        UserEntity user =new UserEntity();
        user.setAccount(account);
        user.setPassword(password);
        user = loginMapper.loginCheck(user);
        if(user != null)
            return user;
        else{
            user.setType(-1);
            return user;
        }

    }

    public String register(UserEntity user){
        int i=0;
        String result = null;
        String account = loginMapper.checkUserAccount(user.getAccount());
        if(account ==null)
            i = loginMapper.register(user);
        else
            result = "ERROR";
        if(i == 0)
            result = "ERROR";
        else
            result = "OK";
        return result;
    }

}
