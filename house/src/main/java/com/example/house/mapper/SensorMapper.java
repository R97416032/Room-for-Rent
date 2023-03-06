package com.example.house.mapper;

import com.example.house.entity.SensorEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface SensorMapper {

    @Select("select * from sensor where room=#{RoomId} order by time desc")
    ArrayList<SensorEntity> getSensorById(int RoomId);
    @Select("SELECT * from sensor where id=(SELECT max(id) from sensor where room=#{RoomId})")
    ArrayList<SensorEntity> getCurrentSensorById(int RoomId);
    @Insert("insert into sensor(id,room,temperature,humidity,pm,noise,time) values(#{id},#{room},#{temperature},#{humidity},#{pm},#{noise},#{time})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void setSensor(SensorEntity s);
}
