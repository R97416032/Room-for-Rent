package com.example.house.controller;


import com.example.house.entity.RoomEntity;
import com.example.house.entity.UserEntity;
import com.example.house.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/manage")
public class ManageController {
    @Autowired
    private ManageService manage;
//用户、业务员管理部分
    @RequestMapping("/getAllUsersByType")
    public ArrayList<UserEntity> getAllUsers(int type) {
        return manage.getAllUsers(type);
    }
    @RequestMapping("/delUserByAccount")
    public String delUserByAccount(String account) {
        return manage.delUserByAccount(account);
    }
    @RequestMapping("/searchSalesByName")
    public ArrayList<UserEntity> searchSalesByName(String name) {
        return manage.searchOperaters(name);
    }
    @RequestMapping("/searchUsersByName")
    public ArrayList<UserEntity> searchUsersByName(String name) {
        return manage.searchUsers(name);
    }
    @RequestMapping("/insertOperater")
    public void insertOperater(UserEntity operater){
        manage.insertOperater(operater);
    }


//房间管理部分
    @RequestMapping("/getAllRooms")
    public ArrayList<RoomEntity> getAllRooms() {
        return manage.getAllRooms();
    }
    @RequestMapping("/searchRoomsByName")
    public ArrayList<RoomEntity> searchRoomsByName(String name) {
        return manage.searchRooms(name);
    }
    @RequestMapping("/delRoomById")
    public String delRoomById(int id) {
        return manage.delRoomById(id);
    }
    @RequestMapping("/searchRoomById")
    public RoomEntity searchRoomById(int id) {
        System.out.println(manage.searchRoomById(id).getOwnerphone());
        return manage.searchRoomById(id);
    }
    @RequestMapping("/updateRoom")
    public String updateRoom(@RequestBody RoomEntity room) {
        return manage.updateRoom(room);
    }
    @RequestMapping("/insertRoom")
    public String insertRoom(RoomEntity room) {
        return manage.insertRoom(room);
    }



}
