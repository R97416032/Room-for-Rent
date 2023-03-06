package com.example.house.controller;


import com.example.house.entity.SensorEntity;
import com.example.house.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @RequestMapping("/getSensor")
    public ArrayList<SensorEntity> getSensors(int RoomId){
        return sensorService.getSensorById(RoomId);
    }

    @RequestMapping("/getCurrent")
    public ArrayList<SensorEntity> getCurrentSensor(int RoomId){
        return sensorService.getCurrentSensorById(RoomId);
    }

    @RequestMapping("/setSensor")
    public String setSensor(@RequestBody SensorEntity s){
        System.out.println(s.getRoom());
        return sensorService.setSensor(s);
    }
}
