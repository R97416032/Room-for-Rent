package com.example.house.controller;

import com.example.house.entity.ContractEntity;
import com.example.house.entity.RoomEntity;
import com.example.house.entity.UserEntity;
import com.example.house.service.UserOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserOperationController {

    @Autowired
    private UserOperationService userService;

    @RequestMapping("/getAllRooms")
    public ArrayList<RoomEntity> getAllRooms(){
        return userService.getAllRooms();
    }

    @RequestMapping("/getRoomById")
    public RoomEntity getRoomById(int id){
        return userService.getRoomById(id);
    }

    @RequestMapping("/getRoomsByState")
    public ArrayList<RoomEntity> getRoomsByState(String state){
        return userService.getRoomsByState(state);
    }

    @RequestMapping("/getRoomsByName")
    public ArrayList<RoomEntity> getRoomsByName(String name){
        return userService.getRoomsByName(name);
    }

    @RequestMapping("/getRoomsByAddress")
    public ArrayList<RoomEntity> getRoomsByAddress(String address){
        return userService.getRoomsByAddress(address);
    }

    @RequestMapping("/getRoomsByPrice")
    public ArrayList<RoomEntity> getRoomsByPrice(float min,float max){
        return userService.getRoomsByPrice(min,max);
    }

    @RequestMapping("/getRoomsByStructure")
    public ArrayList<RoomEntity> getRoomsByStructure(String structure){
        return userService.getRoomsByStructure(structure);
    }

    @RequestMapping("/getContractForUser")
    public ArrayList<ContractEntity> getContractForUser(String account){
        return userService.getContractForUser(account);
    }

    @RequestMapping("/deleteOrderById")
    public String deleteOrderById(int id){
        return userService.deleteOrderById(id);
    }

    @RequestMapping("/getUserByAccount")
    public UserEntity getUserByAccount(String account){
        return userService.getUserByAccount(account);
    }

    @RequestMapping("/updataUser")
    public String updataUser(UserEntity user){
        return userService.updataUser(user);
    }
}
