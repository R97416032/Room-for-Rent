package com.example.house.mapper;

import com.example.house.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("select * from user where account = #{account} and password = #{password}")
    UserEntity loginCheck(UserEntity user);//根据账号、密码检验是否存在该用户

    @Select("select account from user where account = #{account}")
    String checkUserAccount(String account);//检测用户表中账户名是否已存在
    @Insert("insert into user (name,phone,account,password) values (#{name},#{phone},#{account},#{password})")
    @Options(useGeneratedKeys = true,keyColumn = "id", keyProperty = "id")
    int register(UserEntity user);//注册  插入一条用户数据
}
