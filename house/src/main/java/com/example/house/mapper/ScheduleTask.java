package com.example.house.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ScheduleTask {
    @Update("update room set room.state='空闲' , room.user='' where room.id in (select roomid as id FROM contract WHERE DATE_FORMAT(stoptime,\"%Y-%m-%d\")<DATE_FORMAT(CURRENT_DATE,\"%Y-%m-%d\")) ")
    void ScheduleTask();
}
