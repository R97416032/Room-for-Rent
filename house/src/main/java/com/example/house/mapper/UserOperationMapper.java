package com.example.house.mapper;

import com.example.house.entity.ContractEntity;
import com.example.house.entity.RoomEntity;
import com.example.house.entity.UserEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface UserOperationMapper {

    @Select("select * from room where id = #{id}")
    RoomEntity getRoomById(int id);//通过id得到房间信息
    @Select("select * from room")
    ArrayList<RoomEntity> getAllRooms();//检索所有房间记录
    @Select("select * from room where state = #{state}")
    ArrayList<RoomEntity> getRoomsByState(String state);//根据状态检索所有房间记录
    @Select("select * from room where name like '%${name}%'")
    ArrayList<RoomEntity> getRoomsByName(String name);//通过房间名模糊查询筛选对应房间
    @Select("select * from room where address like '%${address}%'")
    ArrayList<RoomEntity> getRoomsByAddress(String address);//通过地址模糊查询筛选对应区域房间
    @Select("select * from room where structure = #{structure}")
    ArrayList<RoomEntity> getRoomsByStructure(String structure);//通过房间结构查询筛选对应房间
    @Select("select * from room where price >= #{arg0} and price <= #{arg1}")
    ArrayList<RoomEntity> getRoomsByPrice(float min,float max);//得到对应价格区间内的房间

    @Select("select * from user where id = #{id}")
    UserEntity getUserById(int id);//通过账户名得到对应用户姓名
    @Select("select * from user where account = #{account}")
    UserEntity getUserByAccount(String account);//通过账户名得到对应用户姓名
    @Select("select * from contract where useraccount = #{useraccount}")
    ArrayList<ContractEntity> getContractForUser(String useraccount);//通过用户名得到合同表中对应用户的记录

    @Update("update user set name = #{name},phone = #{phone},password = #{password} where account = #{account}")
    int updataUser(UserEntity user);//修改对应account的用户信息

    @Delete("delete from orders where id=#{id}")
    int delOrderById(int id);

    @Select("select * from orders where useraccount = #{useraccount}")
    ArrayList<ContractEntity> getOrderbyUseraccount(String useraccount);


}
