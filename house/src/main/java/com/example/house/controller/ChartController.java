package com.example.house.controller;

import com.example.house.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@ResponseBody
@RequestMapping("/chart")
public class ChartController {
    @Autowired
    private ChartService chartService;
    @RequestMapping("getOrderSumByMonth")
    ArrayList<Float> getOrderSumByMonth(){
        return chartService.getOrderSumByMonth();
    }
    @RequestMapping("getOrderDate")
    ArrayList<String> getOrderMonthByMonth(){
        return chartService.getOrderDate();
    }

    @RequestMapping("getOrderSumByRoomId")
    ArrayList<Float> getOrderSumByRoomId(){
        return chartService.getOrderSumByRoomId();
    }
    @RequestMapping("getOrderRoomId")
    ArrayList<Integer> getOrderRoomId(){
        return chartService.getOrderRoomId();
    }
}
