package com.example.house.service;

import com.example.house.entity.ContractEntity;
import com.example.house.entity.RoomEntity;
import com.example.house.entity.UserEntity;
import com.example.house.mapper.SalesOperationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Service
public class SalesOperationService {

    @Autowired
    private SalesOperationMapper sales;

    public ArrayList<RoomEntity> getAllRooms(){
        return sales.getAllRooms();
    }

    public RoomEntity getRoomById(int id){
        return sales.getRoomById(id);
    }

    public ArrayList<RoomEntity> getRoomsByName(String name){
        return sales.getRoomsByName(name);
    }

    public ArrayList<ContractEntity> getContractForSales(String salesmanaccount){
        if(sales.getUserByAccount(salesmanaccount) == null)
            return null;
        ArrayList<ContractEntity> result = sales.getContractForSales(salesmanaccount);//通过业务员账户得到对应租房记录
        for(ContractEntity item : result) {//将结果集里的房间名、用户名、业务员名通过其account设置好
            item.setSalesman(sales.getUserByAccount(item.getSalesmanaccount()).getName());
            if(sales.getRoomById(item.getRoomid())!=null)
                item.setRoom(sales.getRoomById(item.getRoomid()).getName());
            else
                item.setRoom(null);
            if(sales.getUserByAccount(item.getUseraccount())!=null)
                item.setUser(sales.getUserByAccount(item.getSalesmanaccount()).getName());
            else
                item.setSalesman(null);
        }
        return result;
    }
    public long time(String starttime,String stoptime){//计算月份差
        String[] start=starttime.split(" ");
        String[] startData=start[0].split("-");
        int startYear=Integer.parseInt(startData[0]);
        int startMonth=Integer.parseInt(startData[1]);
        int startDay=Integer.parseInt(startData[2]);

        String[] stop=stoptime.split(" ");
        String[] stopData=stop[0].split("-");
        int stopYear=Integer.parseInt(stopData[0]);
        int stopMonth=Integer.parseInt(stopData[1]);
        int stopDay=Integer.parseInt(stopData[2]);

        int result = (stopYear-startYear)*12+(stopMonth-startMonth);
        if(stopDay>startDay) result=result+1;

        return result;
    }
    public String checkinRoom(ContractEntity contract){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");
        Date date = new Date();
        UserEntity user = sales.getUserByAccount(contract.getUseraccount());
        UserEntity salesman = sales.getUserByAccount(contract.getSalesmanaccount());
        //System.out.println(contract.getUseraccount()+"---"+contract.getSalesmanaccount());
        if (user==null||salesman==null) return "ERROR1";
        if(sales.getRoomById(contract.getRoomid()).getState().equals("已租")) return "ERROR2";

        String num = sdf.format(date)+"-"+contract.getRoomid()+user.getId()+salesman.getId();//生成单号
        contract.setNum(num);

        contract.setPrice(time(contract.getStarttime(),contract.getStoptime())*sales.getRoomById(contract.getRoomid()).getPrice());

        int ii = sales.checkinRoom(contract);//租房
        int iii = sales.insertOrder(contract);//产生合同
        int i = sales.rentRoom(contract.getRoomid(),"已租",user.getName());
        if(i!=0&&ii!=0&&iii!=0)
            return "OK";
        else
            return "ERROR3";
    }

    public String checkoutRoom(int roomid,String stoptime){
        float price = 0;

        if(sales.getRoomById(roomid).getState().equals("空闲")) return "ERROR1";
        int id = sales.getNewestContractIdByRoomId(roomid);


        ContractEntity c = sales.getContractById(id);
        if(c.getStarttime().compareTo(stoptime)>0) {//判断是否提前退
            stoptime = c.getStarttime();
        }

        price = time(c.getStarttime(),stoptime)*sales.getRoomById(roomid).getPrice();
        int ii = sales.checkoutRoom(id,stoptime,price);
        int i = sales.rentRoom(roomid,"空闲"," ");
        if(sales.getOrderbyNum(c.getNum())!=null){
            int iii = sales.updateOrder(c.getNum(),stoptime,price);
            if(i!=0&&ii!=0&&iii!=0)
                return "OK";
            else
                return "ERROR2";
        }
        else{
            if(i!=0&&ii!=0)
                return "OK";
            else
                return "ERROR2";
        }

    }



}
