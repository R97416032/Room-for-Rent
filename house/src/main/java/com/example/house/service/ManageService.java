package com.example.house.service;

import com.example.house.entity.RoomEntity;
import com.example.house.entity.UserEntity;
import com.example.house.mapper.ManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ManageService {

    @Autowired
    private ManageMapper mapper;

    public ArrayList<UserEntity> getAllUsers(int type) {
        return mapper.getAllUsers(type);
    }
    public String delUserByAccount(String account){
        int i = mapper.delUserByAccount(account);
        if(i!=0) return "OK";
        else  return "ERROR";
    }
    public ArrayList<UserEntity> searchOperaters(String name) {
        name="%"+name+"%";
        return mapper.searchSales(name);
    }
    public ArrayList<UserEntity> searchUsers(String name) {
        name="%"+name+"%";
        return mapper.searchUsers(name);
    }

    public ArrayList<RoomEntity> getAllRooms(){
        return mapper.getAllRooms();
    }
    public ArrayList<RoomEntity> searchRooms(String name){
        name="%"+name+"%";
        return mapper.searchRooms(name);
    }
    public String delRoomById(int id){
        int i = mapper.delRoomById(id);
        if(i!=0) return "OK";
        else  return "ERROR";
    }
    public RoomEntity searchRoomById(int id){
        return mapper.searchRoomById(id);
    }
    public String updateRoom(RoomEntity room){
        System.out.println(room.getId());
        System.out.println(room.getAddress());
        System.out.println(room.getArea());
        System.out.println(room.getName());
        int i = mapper.updateRoom(room);
        if(i!=0) return "OK";
        else  return "ERROR";
    }
    public String insertRoom(RoomEntity room){
        int i = mapper.insertRoom(room);
        if(i!=0) return "OK";
        else  return "ERROR";
    }

    public void insertOperater(UserEntity operater) {
        mapper.insertOperater(operater);
    }
}
