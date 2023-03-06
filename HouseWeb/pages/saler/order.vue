<template>
	<view style="margin-left: 15%;">
		
		<!-- 订单列表信息情况页面 -->
		<view style="padding: 20upx 20upx 20upx 40upx;">
			<view style="border-bottom:1upx solid #e7e7e7;padding: 10upx 0 10upx 0; " class="flex justify-around bg-white" v-for="(item,index) in order"  :key='index'>
					<view style="width: 100%; height: 10%; display: flex; ">
						<view style="display: flex; width: 60%;">
							<image style="width: 50rpx;height: 50rpx; margin-left: 10rpx;"src="../../static/homeicon.png"></image>
							<text class="name" style="font-size: 30rpx; color: #333333; margin-left: 5rpx; display: flex; align-items: center;">{{item.room}}</text>
						</view>
						<text style="font-size: 25rpx; color: #000000; margin-left: 60rpx; align-items: center;">订单号: {{item.num}}</text>
						<text class="name" style="font-size: 25rpx; color:#808080; margin-left: 160rpx; display: flex; align-items: center;">{{item.state}}</text>
					</view>
					<!-- 上面 -->
					<!-- 下面 -->
				<view  style="margin-left: 10rpx; width: 1400upx; display: flex;" class="flex justify-between">
					<image style="width: 106rpx;height: 100rpx; margin-top: 20rpx;" :src="onloadimg(item.roomid)"></image>
					<view style="margin-bottom: 10upx;padding-top: 10upx;">
						<view style="font-size: 120%; margin-left: 20rpx; margin-top: 10rpx;">
							<view class="cu-tag sm bg-orange radius" style="color: grey; font-size: 25rpx;margin-top: 10rpx;">租房用户: {{item.useraccount}} </view>
							<view style="color: grey;font-size: 25rpx; margin-top: 10rpx;">{{item.starttime}}-{{item.stoptime}}</view>
							<view style="color: grey;font-size: 25rpx; margin-top: 10rpx;">总价:￥{{item.price}}</view>
						</view>
						<view style="width: 100%;height: 20upx;"></view>
					</view>
				</view>
			</view>
		</view>
		<view style="font-size: 25rpx; margin-left: 30%; color: #C0C0C0;">真的划不动了，已经到底了</view>
		<view style="height: 140rpx; width: 100%;"></view>
		
		
	</view>
</template>

<script>
	import currentDate from'../../store/currentDate.js'
export default {
	
	data() {
		return {
			nowtime:currentDate,
			account:null,
			order: {},
			state:'',
			i:'0'
		}
	},
	created() {
		this.nowtime = currentDate.getDate();
		console.log("5555"+this.nowtime);
		wx.request({
			url:'http://127.0.0.1:8080/sales/getContractForSales',
			data:{
				salesmanaccount:uni.getStorageSync('userInfo').account
			},
			success:(response) => {
				this.order = response.data;
				
				console.log(response.data);
				console.log("aaa"+this.order[this.i].stoptime);
				for(this.i;this.i<response.data.length;(this.i)++){
					console.log("4444444444"+response.data[this.i].starttime);
					if(this.order[this.i].stoptime>this.nowtime&&this.nowtime>this.order[this.i].starttime){
						this.order[this.i].state="租房中";
					}
					if(this.order[this.i].stoptime>this.order[this.i].starttime&&this.order[this.i].starttime>=this.nowtime){
						this.order[this.i].state="未入住"
					}
					if(this.order[this.i].stoptime<this.nowtime){
						this.order[this.i].state="已完成";
					}
					if(this.order[this.i].stoptime==this.order[this.i].starttime)
					{
						this.order[this.i].state="已取消";
					}
					
				}
				if(this.nowtime<"2021-12-26 22:41:48"){
					console.log("aaa"+response.data.stoptime);
				}
			}
		})
	},
	methods: {
		onloadimg(id){
			var url="http://127.0.0.1:8080/"+id+".jpg"
			console.log("aaaa:"+url)
			return url
		},
		
	}
};
</script>

<style>


</style>