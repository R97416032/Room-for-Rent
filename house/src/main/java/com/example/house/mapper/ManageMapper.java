package com.example.house.mapper;

import com.example.house.entity.RoomEntity;
import com.example.house.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface ManageMapper {
    @Select("select * from user where account=#{account}")
    UserEntity getUserByAccount(String account);
    @Select("select * from user where type=#{type}")
    ArrayList<UserEntity> getAllUsers(int type);
    @Select("select * from user where name like #{name} and type=1")
    ArrayList<UserEntity> searchSales(String name);
    @Select("select * from user where name like #{name} and type=2")
    ArrayList<UserEntity> searchUsers(String name);
    @Delete("delete from user where account=#{account}")
    int delUserByAccount(String account);
    @Insert("insert into user(id,name,phone,account,password,type) values(#{id},#{name},#{phone},#{account},#{password},#{type})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insertOperater(UserEntity operater);


    @Select("select max(id) from room")
    int getMaxRoomId();

    @Select("select * from room where name like #{name}")
    ArrayList<RoomEntity> searchRooms(String name);
    @Select("select * from room ")
    ArrayList<RoomEntity> getAllRooms();
    @Delete("Delete from room where id=#{id}")
    int delRoomById(int id);
    @Select("select * from room where id=#{id}")
    RoomEntity searchRoomById(int id);
    @Update("update room set name=#{name},state=#{state},user=#{user},address=#{address},area=#{area},price=#{price},structure=#{structure},owner=#{owner},ownerphone=#{ownerphone} where id=#{id} ")
    int updateRoom(RoomEntity room);
    @Insert("insert into room(id,name,state,user,address,area,price,structure,owner,ownerphone) values (#{id},#{name},#{state},#{user},#{address},#{area},#{price},#{structure},#{owner},#{ownerphone})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertRoom(RoomEntity room);


}
