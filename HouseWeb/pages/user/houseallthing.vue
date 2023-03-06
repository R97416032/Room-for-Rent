<template>
	<view class="container">
		<image class="bg-set" :src="onloadimg(id)"></image>
		<view class="content" style="width: 94%;height: 30%; margin-top: 400rpx;margin-left:3%; background-color: #FFFFFF; border-radius: 10px;">
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
		<view style="font-size: 50rpx; margin-top: 20rpx; margin-left: 30%; background-color: #FFFFFF;">
			<table class='table'>
			      <tr class='tr'>
			        <th class='th'>温度</th>
			        <th class='th'>湿度</th>
			        <th class='th'>Pm2.5</th>
			        <th class='th'>噪音</th>
			        <th class='th'>时间</th>
			      </tr>
			      <tr v-for="(item, index) in infos"
			          :key="index" class='tr'>
			        <th class='td'>{{item.temperature}}</th>
			        <th class='td'>{{item.humidity}} </th>
			        <th class='td'>{{item.pm}}</th>
			        <th class='td'>{{item.noise}}</th>
			        <th class='td'>{{item.datetime}}</th>
				  </tr>
				  
			    </table>
				</view>
		<button style="width: 60%; margin-left: 20%; margin-top: 10rpx; color:#3F536E " v-on:click="info()">查询历史记录</button>
	</view>
</template>

<script>
var sensorid;
var ck;
export default {
	
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
			infos:null
		};
	},
	onLoad:function(option){
		sensorid=option.id;
		console.log("1111"+option.id);
		wx.request({
			url:"http://127.0.0.1:8080/user/getRoomById",
			data:{
				id:option.id
			},
			success:(response)=>{
				this.id = response.data.id;
				console.log("aoe"+this.id)
				this.name = response.data.name;
				this.structure = response.data.structure;
				this.state = response.data.state;
				this.area = response.data.area;
				this.address = response.data.address;
				this.owner = response.data.owner;
				this.owner_phone = response.data.owner_phone;
				this.price = response.data.price;
			}
		})
	},
	mounted() {
	  		this.getData();
	  },
	methods: {
		onloadimg(id){
			var url="http://127.0.0.1:8080/"+id+".jpg"
			console.log("aaaa:"+url)
			return url
		},
		info(){
			this.infos=null;
			if(ck){
				ck=false;
				wx.request({
					  		url:'http://127.0.0.1:8080/sensor/getSensor',
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
					  		url:'http://127.0.0.1:8080/sensor/getCurrent',
					  		data:{
									RoomId:sensorid
					  			},
					  		success:(response)=>{
					  			this.infos = response.data;
					  			console.log(response.data)
					  			}
					  	});
			}	  
				
			  },
		getData(){
			this.infos=null;
			ck=true;
			wx.request({
				  		url:'http://127.0.0.1:8080/sensor/getCurrent',
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