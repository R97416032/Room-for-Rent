<template>
	<view>
		
		<!-- 订单列表信息情况页面 -->
		<view style="padding: 20upx 20upx 20upx 40upx;">
			<view style="border-bottom:1upx solid #e7e7e7;padding: 10upx 0 10upx 0; " class="flex justify-around bg-white" v-for="(item,index) in order"  :key='index'>
					<view style="width: 100%; height: 10%; display: flex; ">
						<view style="display: flex; width: 60%;">
						<image style="width: 50rpx;height: 50rpx; margin-left: 10rpx;"src="../../static/homeicon.png"></image>
						<text class="name" style="font-size: 30rpx; color: #333333; margin-left: 5rpx; display: flex; align-items: center;">{{item.room}}</text>
						</view>
						<text v-if="comparetime(item.starttime,item.stoptime)==2" class="name" style="font-size: 25rpx; color:#808080; margin-left: 160rpx; display: flex; align-items: center;">已完成</text>
						<text v-if="comparetime(item.starttime,item.stoptime)==1" class="name" style="font-size: 25rpx; color:#808080; margin-left: 160rpx; display: flex; align-items: center;">进行中</text>
						<text v-if="comparetime(item.starttime,item.stoptime)==3" class="name" style="font-size: 25rpx; color:#808080; margin-left: 160rpx; display: flex; align-items: center;">已取消</text>
						<text v-if="comparetime(item.starttime,item.stoptime)==0" class="name" style="font-size: 25rpx; color:#808080; margin-left: 160rpx; display: flex; align-items: center;">未入住</text>
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
export default {
	
	data() {
		return {
			account:null,
			order: {},
		}
	},
	created() {
		// console.log("acacaca"+getApp().globalData.account), // 'test'
		wx.request({
			url:'http://192.168.137.1:8081/sales/getContractForSales',
			data:{
				// salesmanaccount:getApp().globalData.account
				salesmanaccount:uni.getStorageSync('userInfo').account
			},
			success:(response) => {
				this.order = response.data;
				console.log(response.data);
			}
		})
	},
	methods: {
		comparetime(start,stop){
			const date = new Date();
			let year = date.getFullYear();
			let month = date.getMonth() + 1;
			let day = date.getDate();
			month = month > 9 ? month : '0' ;
			day = day > 9 ? day : '0' + day;
			var timer = year + '-' + month + '-' + day+' 00:00:00';
			
			if(timer>start&&timer<stop)
				return 1//进行中
			else if(timer<=start&&start<stop)
				return 0//未入住
			else if(timer>=stop&&start<stop)
				return 2//已完成	
			else if(timer<=start&&start==stop||start==stop)
				return 3//已取消	
		},
		onloadimg(id){
			var url="http://192.168.137.1:8081/"+id+".jpg"
			console.log("aaaa:"+url)
			return url
		},
		
	}
};
</script>

<style>


</style>