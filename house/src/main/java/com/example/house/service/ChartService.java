package com.example.house.service;


import com.example.house.mapper.ChartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ChartService {
    @Autowired
    private ChartMapper chart;
    public ArrayList<Float> getOrderSumByMonth() {
        return chart.getOrderSumByMonth();
    }
    public ArrayList<String> getOrderDate() {
        return chart.getOrderDate();
    }
    public ArrayList<Float> getOrderSumByRoomId() {
        return chart.getOrderSumByRoomId();
    }
    public ArrayList<Integer> getOrderRoomId() {
        return chart.getOrderRoomId();
    }
}
