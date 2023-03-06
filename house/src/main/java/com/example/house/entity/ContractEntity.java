package com.example.house.entity;

public class ContractEntity {
    private int id;
    private String num;
    private String starttime;
    private String stoptime;
    private float price;
    private int roomid;//数据库返回的是房间id
    private String room;//默认为空，根据id得到房间名字后放置在这个属性
    private String useraccount;//数据库返回的是用户account
    private String user;//默认为空，根据account得到用户名字后放置在这个属性
    private String salesmanaccount;//数据库返回的是业务员account
    private String salesman;//默认为空，根据account得到业务员名字后放置在这个属性

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getStoptime() {
        return stoptime;
    }

    public void setStoptime(String stoptime) {
        this.stoptime = stoptime;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSalesmanaccount() {
        return salesmanaccount;
    }

    public void setSalesmanaccount(String salesmanaccount) {
        this.salesmanaccount = salesmanaccount;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }
}
