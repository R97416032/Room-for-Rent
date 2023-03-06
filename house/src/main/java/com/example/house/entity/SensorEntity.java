package com.example.house.entity;

public class SensorEntity {
    private int id;
    private int room;
    private String temperature;
    private String humidity;
    private String pm;
    private String noise;
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom() {    return room; }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getNoise() {
        return noise;
    }

    public void setNoise(String noise) {
        this.noise = noise;
    }

    public String getDatetime() {
        return time;
    }

    public void setDatetime(String datetime) {
        this.time = datetime;
    }
}
