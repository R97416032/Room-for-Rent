package com.example.house.controller;


import com.example.house.entity.ContractEntity;
import com.example.house.entity.RoomEntity;
import com.example.house.service.SalesOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/sales")
public class SalesOperationController {

    @Autowired
    private SalesOperationService sales;

    @RequestMapping("/getAllRooms")
    public ArrayList<RoomEntity> getAllRooms(){
        return sales.getAllRooms();
    }
    @RequestMapping("/getRoomById")
    public RoomEntity getRoomById(int id){
        return sales.getRoomById(id);
    }
    @RequestMapping("/getRoomsByName")
    public ArrayList<RoomEntity> getRoomsByName(String name){
        return sales.getRoomsByName(name);
    }

    @RequestMapping("/getContractForSales")
    public ArrayList<ContractEntity> getContractForSales(String salesmanaccount){
        return sales.getContractForSales(salesmanaccount);
    }

    @RequestMapping("/checkinRoom")
    public String checkinRoom(ContractEntity contract){
        return sales.checkinRoom(contract);
    }
    @RequestMapping("/checkoutRoom")
    public String checkoutRoom(int roomid,String stoptime){
        return sales.checkoutRoom(roomid, stoptime);
    }
}
