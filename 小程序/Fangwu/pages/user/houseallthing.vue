<template>
	<view class="container">
		<image class="bg-set" :src="onloadimg(id)"></image>
		<view class="content" style="width: 94%;height: 30%; margin-top: 37%;margin-left:3%; background-color: #FFFFFF; border-radius: 10px;">
			<view style="width: 100%;height: 10rpx;"></view>
			<view style="width: 100%;height: 30%; display: flex; margin-bottom: 20rpx;">
				<view style="width: 50%;">
					<view style="font-size: 45rpx; margin-top: 10rpx; margin-left: 5%; color: #F0AD4E;">{{name}}</view>
					<view style="font-size: 33rpx; margin-top: 10rpx; margin-left: 5%; color:#808080">房型: {{structure}}</view>
				</view>
				<view style="display: flex;">
					<image style="width: 60rpx; height: 60rpx; margin-top: 15%; margin-left: 70rpx" src="../../static/state.png"></image>
					<view style="font-size: 45rpx; margin-top: 15%; margin-left: 5rpx; color:#DD524D;">{{state}}中</view>
				</view>
			</view>
			<view style="width: 100%; height: 1rpx; background-color: #555555;"></view>
			<view style="width: 100%;height: 70%; margin-top: 20rpx;">
				<view style="font-size: 31rpx; margin-top: 15rpx; margin-left: 5%;">面积: {{area}}平</view>
				<view style="font-size: 30rpx; margin-top: 15rpx; margin-left: 5%;">地址: {{address}}</view>
				<view style="font-size: 31rpx; margin-top: 15rpx; margin-left: 5%;">房东: {{owner}}</view>
				<view style="font-size: 31rpx; margin-top: 15rpx; margin-left: 5%;">房东联系方式: {{owner_phone}}</view>
				<view style="font-size: 37rpx; margin-top: 15rpx; margin-left: 60%; color: #DD524D;">房价: {{price}}元/月</view>
			</view>
		</view>
		
		<view style="font-size: 50rpx; margin-top: 20rpx; background-color: #FFFFFF;">
			<button style="width: 60%; margin-left: 20%; margin-top: 10rpx; color:#3F536E " v-on:click="info()">{{bt}}</button>
			<!-- <table class='table'>
			      <tr class='tr'>
			        <td class='th'>房号</td>
			        <td class='th'>温度</td>
			        <td class='th'>湿度</td>
			        <td class='th'>Pm2.5</td>
			        <td class='th'>噪音</td>
			        <td class='th'>时间</td>
			      </tr>
			      <tr v-for="(item, index) in infos"
			          :key="index" class='tr'>
			        <td class='td'>{{item.roomId}}</td>
			        <td class='td'>{{item.temperature}}</td>
			        <td class='td'>{{item.humidity}} </td>
			        <td class='td'>{{item.pm}}</td>
			        <td class='td'>{{item.noise}}</td>
			        <td class='td'>{{item.datetime}}</td>
			      </tr>
			    </table> -->
				<view v-if="infos.length != 0" style="margin-top: 30rpx; margin-left: 7%; width: 100%;">
				        <mytable :tableHead="tableHead" :tableData="infos" ></mytable>
				</view>
		</view>
		
	</view>
</template>

<script>
var sensorid;
var ck = true;
import mytable from "components/mytable/mytable.vue";
export default {
	components: {
	            mytable
	        },
	data() {
		return {
			id:'',
			name:'',
			structure:'',
			state:'',
			area:'',
			address:'',
			owner:'',
			owner_phone:'',
			price:'',
			
			infos:[],
			centerList:null,
			tableHeadT:null,
			tableHead:[
                    {
                        name:'温度'
                    },
                    {
                        name: '湿度'
                    },
                    {
                        name: 'pm2.5'
                    },
                    {
                        name: '噪音'
                    },
                    {
                        name: '时间'
                    },
            
                ],
			
			bt:"查询历史记录",
			btstate:1
		};
	},
	onLoad:function(option){
		sensorid=option.id;
		console.log("1111"+option.id);
		wx.request({
			url:"http://192.168.137.1:8081/user/getRoomById",
			data:{
				id:option.id
			},
			success:(response)=>{
				this.id = response.data.id;
				this.name = response.data.name;
				this.structure = response.data.structure;
				this.state = response.data.state;
				this.area = response.data.area;
				this.address = response.data.address;
				this.owner = response.data.owner;
				this.owner_phone = response.data.ownerphone;
				this.price = response.data.price;
			}
		});
	},
	mounted() {
	  		this.getData();
	  },
	methods: {
		onloadimg(id){
			var url="http://192.168.137.1:8081/"+id+".jpg"
			console.log("aaaa:"+url)
			return url
		},
		info(){
			this.infos=null;
			if(ck){
				ck=false;
				wx.request({
					  		url:'http://192.168.137.1:8081/sensor/getSensor',
					  		data:{
									RoomId:sensorid
					  			},
					  		success:(response)=>{
					  			this.infos = response.data;
					  			console.log(response.data)
					  			}
					  	});
				
			}
			else{
				this.infos=null;
				ck=true;
				wx.request({
					  		url:'http://192.168.137.1:8081/sensor/getCurrent',
					  		data:{
									RoomId:sensorid
					  			},
					  		success:(response)=>{
					  			this.infos = response.data;
					  			console.log(response.data)
					  			}
					  	});
			}	  
			if(this.btstate==1){
				this.bt = "关闭历史记录"
				this.btstate = 0
			}else{
				this.bt = "查询历史记录"
				this.btstate = 1
			}
		},
		getData(){
			this.infos=[];
			wx.request({
				  		url:'http://192.168.137.1:8081/sensor/getCurrent',
				  		data:{
								RoomId:sensorid
				  			},
				  		success:(response)=>{
				  			this.infos = response.data;
				  			console.log(response.data)
				  			}
				  	});
					
			  }
	}
};
</script>

<style>
	.bg-set{
	    position: fixed;
	    width: 100%;
	    height: 40%;
	    top: 0;
	    left: 0;
	    z-index: -1;
	}

</style>