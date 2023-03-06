package com.example.house.mapper;

import com.example.house.entity.ContractEntity;
import com.example.house.entity.RoomEntity;
import com.example.house.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface SalesOperationMapper {
    @Select("select * from user where account = #{account}")
    UserEntity getUserByAccount(String account);//根据账号得到业务员信息

    @Select("select * from room where id = #{id}")
    RoomEntity getRoomById(int id);//通过id得到房间信息
    @Select("select * from room")
    ArrayList<RoomEntity> getAllRooms();//检索所有房间记录
    @Select("select * from room where name like '%${name}%'")
    ArrayList<RoomEntity> getRoomsByName(String name);//通过房间名模糊查询筛选对应房间

    @Select("select * from contract where salesmanaccount = #{salesmanaccount}")
    ArrayList<ContractEntity> getContractForSales(String salesmanaccount);//根据业务员id得到账单信息

    @Update("update room set state = #{arg1},user = #{arg2} where id = #{arg0}")
    int rentRoom(int id,String state,String name);//租住、退租修改房间状态、用户
    @Insert("insert into contract (num,starttime,stoptime,price,roomid,useraccount,salesmanaccount) values (#{num},#{starttime},#{stoptime},#{price},#{roomid},#{useraccount},#{salesmanaccount})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int checkinRoom(ContractEntity c);//租住，插入一条合同信息
    @Insert("insert into orders (num,starttime,stoptime,price,roomid,useraccount,salesmanaccount) values (#{num},#{starttime},#{stoptime},#{price},#{roomid},#{useraccount},#{salesmanaccount})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertOrder(ContractEntity c);//租住，插入一条合同信息到order
    @Select("select max(id) from contract where starttime = (SELECT MAX(starttime) FROM contract WHERE roomid =#{id})")
    int getNewestContractIdByRoomId(int id);
    @Select("select * from contract where id = #{id}")
    ContractEntity getContractById(int id);
    @Select("select * from orders where num=#{num}")
    ContractEntity getOrderbyNum(String num);
    @Update("update contract set stoptime = #{arg1},price=#{arg2} where id = #{arg0}")
    int checkoutRoom(int id,String stoptime,float price);//退租，根据对应房间id的最后开始时间得到合同单id更新其结束时间
    @Update("update orders set stoptime=#{arg1},price=#{arg2} where num=#{arg0}")
    int updateOrder(String num,String stoptime,float price);
}
