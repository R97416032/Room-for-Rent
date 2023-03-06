package com.example.house.config;

import com.example.house.mapper.ScheduleTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
    @Autowired
    ScheduleTask scheduleTask ;
    //3.添加定时任务
    @Scheduled(fixedRate=28800000)
    private void configureTasks() {
        scheduleTask.ScheduleTask(); }}