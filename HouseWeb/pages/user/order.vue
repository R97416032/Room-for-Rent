<template>
	<view style="margin-left: 15%;">
		
		<!-- 订单列表信息情况页面 -->
		<view style="padding: 20upx 20upx 20upx 40upx;">
			<view style="border-bottom:1upx solid #e7e7e7;padding: 10upx 0 10upx 0; " class="flex justify-around bg-white" v-for="(item,index) in order"  :key='index'>
					<view style="width: 100%; height: 10%; display: flex; ">
						<view style="display: flex; width: 60%;">
						<image style="width: 50rpx;height: 50rpx; margin-left: 10rpx;"src="../../static/homeicon.png"></image>
						<text class="name" style="font-size: 30rpx; color: #333333; margin-left: 5rpx; display: flex; align-items: center;">{{item.room}}</text>
						<text style="font-size: 25rpx; color: #000000; margin-top: 10rpx; margin-left: 860rpx; align-items: center;">订单号: {{item.num}}</text>
						</view>
						<text v-if="comparetime(item.starttime,item.stoptime)==2" class="name" style="font-size: 25rpx; color: #808080; margin-left: 160rpx; display: flex; align-items: center;">已完成</text>
						<text v-if="comparetime(item.starttime,item.stoptime)==1" class="name" style="font-size: 25rpx; color: #808080; margin-left: 160rpx; display: flex; align-items: center;">进行中</text>
						<text v-if="comparetime(item.starttime,item.stoptime)==3" class="name" style="font-size: 25rpx; color: #808080; margin-left: 160rpx; display: flex; align-items: center;">已取消</text>
						<text v-if="comparetime(item.starttime,item.stoptime)==0" class="name" style="font-size: 25rpx; color: #808080; margin-left: 160rpx; display: flex; align-items: center;">未入住</text>
						<text class="name" @click="delet(item.id)" style="font-size: 35rpx; color:#808080; margin-left: 250rpx; display: flex; align-items: center;color:#FF5242;">删除订单</text>
					</view>
					<!-- 上面 -->
					<!-- 下面 -->
				<view  style="margin-left: 10rpx; width: 1400upx; display: flex;" class="flex justify-between">
					<image style="width: 106rpx;height: 100rpx; margin-top: 20rpx;" :src="onloadimg(item.roomid)"></image>
					<view style="margin-bottom: 10upx;padding-top: 10upx;">
						<view style="font-size: 120%; margin-left: 20rpx; margin-top: 10rpx;">
							<view class="cu-tag sm bg-orange radius" style="color: grey; font-size: 25rpx;margin-top: 10rpx;">业务员: {{item.salesman}} </view>
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
			i:null
		}
	},
	created() {
		this.nowtime = currentDate.getDate();
		wx.request({
			url:'http://127.0.0.1:8080/user/getContractForUser',
			data:{
				account:uni.getStorageSync('userInfo').account
			},
			success:(response) => {
				this.order = response.data;	
				console.log(response.data);
			}
		})
	},
	methods: {
		onloadimg(id){
			var url="http://127.0.0.1:8080/"+id+".jpg"
			console.log("aaaa:"+url)
			return url
		},
		delet(id){
			wx.request({
				url:'http://127.0.0.1:8080/user/deleteOrderById',
				data:{
					id:id
				},
				success:(response) => {
					uni.redirectTo({
						url:'./user'
					})
				}
			})
		},
		comparetime(start,stop){
			const data = new Date();
			let year = data.getFullYear();
			let month = data.getMonth()+1;
			let day = data.getDate();
			month = month > 9 ? month :'0';
			day = day > 9 ? day : '0' +day;
			var timer = year + '-' +month + '-' +day +' 00:00:00';
			
			if(timer>start&&timer<stop)
				return 1
			else if(timer<=start&&start<stop)
				return 0
			else if(timer>=stop&&start<stop)
				return 2
			else if(timer<=start&&start==stop||start==stop)
				return 3
		},
	}
};
</script>

<style>


</style>