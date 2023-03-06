<template>
	<view class="container">
		<image class="bg-set" :src="onloadimg(id)"></image>
		<view class="content" style="width: 94%;height: 30%; margin-top: 37%;margin-left:3%; background-color: #FFFFFF;">
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
				<view style="font-size: 31rpx; margin-top: 15rpx; margin-left: 5%;">房东联系方式: {{ownerphone}}</view>
				<view style="font-size: 37rpx; margin-top: 15rpx; margin-left: 60%; color: #DD524D;">房价: {{price}}元/月</view>
			</view>
		</view>
		<view style="width: 94%; height: 40%; margin-top: 10rpx; margin-left: 3%;">
			<button style="width: 60%; margin-left: 20%; margin-top: 250rpx; color:#3F536E" @click="houseback()">办理退房</button>	
		</view>
		
	</view>
</template>

<script>
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
			ownerphone:'',
			return:'',
			price:''
		};
	},
	onLoad:function(option){
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
				this.ownerphone = response.data.ownerphone;
				this.price = response.data.price;
			}
		})
	},
	methods: {
		onloadimg(id){
			var url="http://192.168.137.1:8081/"+id+".jpg"
			console.log("aaaa:"+url)
			return url
		},
		houseback(){
			const date = new Date();
			let year = date.getFullYear();
			let month = date.getMonth() + 1;
			let day = date.getDate();
			month = month > 9 ? month : '0' ;
			day = day > 9 ? day : '0' + day;
			var timer = year + '-' + month + '-' + day;
			wx.request({
				
				
				url:"http://192.168.137.1:8081/sales/checkoutRoom",
				data:{
					stoptime:timer+' 00:00:00',
					roomid:this.id
				},
				success:(response)=>{
					this.return = response.data;
					console.log("123"+this.return)
					if(this.return == "OK"){
						uni.redirectTo({
							url:'./saler'
						})
					}
				}
			})
			
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