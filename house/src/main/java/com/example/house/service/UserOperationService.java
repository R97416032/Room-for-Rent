package com.example.house.service;


import com.example.house.entity.ContractEntity;
import com.example.house.entity.RoomEntity;
import com.example.house.entity.UserEntity;
import com.example.house.mapper.UserOperationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserOperationService {

    @Autowired
    private UserOperationMapper userOperationMapper;

    public ArrayList<RoomEntity> getAllRooms(){
        return userOperationMapper.getAllRooms();
    }

    public RoomEntity getRoomById(int id){
        return userOperationMapper.getRoomById(id);
    }

    public ArrayList<RoomEntity> getRoomsByState(String state){
        return userOperationMapper.getRoomsByState(state);
    }

    public ArrayList<RoomEntity> getRoomsByStructure(String structure){
        return userOperationMapper.getRoomsByStructure(structure);
    }

    public ArrayList<RoomEntity> getRoomsByName(String name){
        return userOperationMapper.getRoomsByName(name);
    }

    public ArrayList<RoomEntity> getRoomsByAddress(String address){
        return userOperationMapper.getRoomsByAddress(address);
    }

    public ArrayList<RoomEntity> getRoomsByPrice(float min,float max){
        return userOperationMapper.getRoomsByPrice(min, max);
    }

    public ArrayList<ContractEntity> getContractForUser(String account){
        if(userOperationMapper.getUserByAccount(account) == null)
            return null;
        ArrayList<ContractEntity> result = userOperationMapper.getOrderbyUseraccount(account);//通过用户名得到对应租房记录
        for(ContractEntity item : result) {//将结果集里的房间名、用户名、业务员名通过其id设置好
            item.setUser(userOperationMapper.getUserByAccount(item.getUseraccount()).getName());
            if(userOperationMapper.getRoomById(item.getRoomid())!=null)
                item.setRoom(userOperationMapper.getRoomById(item.getRoomid()).getName());
            else
                item.setRoom(null);
            if(userOperationMapper.getUserByAccount(item.getSalesmanaccount())!=null)
                item.setSalesman(userOperationMapper.getUserByAccount(item.getSalesmanaccount()).getName());
            else
                item.setSalesman(null);
        }
        return result;
    }
    public String deleteOrderById(int id){
        int i = userOperationMapper.delOrderById(id);
        if(i!=0)
            return "OK";
        else
            return "ERROR";

    }


    public UserEntity getUserByAccount(String account){
        return userOperationMapper.getUserByAccount(account);
    }

    public String updataUser(UserEntity user){
        System.out.println("AA.getAccount>>>"+user.getAccount());
        UserEntity theUser = userOperationMapper.getUserByAccount(user.getAccount());
        if(theUser.getName().equals(user.getName())
                &&theUser.getPhone().equals(user.getPhone())
                &&theUser.getPassword().equals(user.getPassword())){
            return "ERROR";
        }
        int i = userOperationMapper.updataUser(user);
        System.out.println("CC>>>"+i);
        if(i == 0)
            return "ERROR";
        else
            return "OK";
    }
}
