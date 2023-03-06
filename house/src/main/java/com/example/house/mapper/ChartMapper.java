package com.example.house.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface ChartMapper {
    @Select("SELECT sum from (select sum(price) as sum, DATE_FORMAT(stoptime,\"%Y-%m\") as date FROM contract WHERE DATE_FORMAT(stoptime,\"%Y\")=DATE_FORMAT(CURRENT_DATE,\"%Y\") GROUP BY date  ORDER BY date ASC) as MonthSum")
    ArrayList<Float> getOrderSumByMonth();
    @Select("SELECT date from (select sum(price) as sum, DATE_FORMAT(stoptime,\"%Y-%m\") as date FROM contract WHERE DATE_FORMAT(stoptime,\"%Y\")=DATE_FORMAT(CURRENT_DATE,\"%Y\") GROUP BY date  ORDER BY date ASC ) as MonthSum")
    ArrayList<String> getOrderDate();
    @Select("select DISTINCT roomid  from contract ORDER BY roomid ASC")
    ArrayList<Integer>getOrderRoomId();
    @Select("SELECT sum(price) as sum  from contract  GROUP BY roomid ORDER BY roomid")
    ArrayList<Float>getOrderSumByRoomId();
}
