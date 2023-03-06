package com.example.house.service;

import com.example.house.entity.SensorEntity;
import com.example.house.mapper.SensorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class SensorService {

    @Autowired
    private SensorMapper sensorMapper;

    public ArrayList<SensorEntity> getSensorById(int RoomId){
        return sensorMapper.getSensorById(RoomId);
    }

    public ArrayList<SensorEntity> getCurrentSensorById(int RoomId){
        return sensorMapper.getCurrentSensorById(RoomId);
    }

    public String setSensor(SensorEntity s){
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        int room=s.getRoom();
        String temperature=s.getTemperature();
        String humidity=s.getHumidity();
        String pm=s.getPm();
        String noise=s.getNoise();
        s.setDatetime(dateFormat.format(date));
        System.out.println(room+"   "+temperature+" "+humidity+" "+pm+" "+noise+" "+s.getDatetime());
        sensorMapper.setSensor(s);
        return "success";
    }
}
