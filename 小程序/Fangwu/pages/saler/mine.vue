<template>
	<view>
		
		<view style="width: 700rpx; height: 700rpx; margin-top: 20px;">
			<view style="display: flex; ">
				<image style=" width: 150rpx; height: 150rpx; margin-left: 40rpx;" src="../../static/touxiang.jpg"></image>
			
				<view>
					<view style="font-size: 60rpx; color: #333333; margin-left: 20rpx;">姓名: {{name}}</view>
					<view style="font-size: 40rpx; color: #808080; margin-top: 20rpx; margin-left: 20rpx;">账号: {{account}}</view>
				</view>
			</view>
			<view style="display: flex; margin-top: 30rpx;">
				<image style="width: 50rpx; height: 50rpx; margin-top: 20rpx; margin-left: 90rpx;" src="../../static/tel.png"></image>
				<view style="font-size: 40rpx; color: #555555; margin-top: 20rpx; margin-left: 20rpx;">手机号: {{phone}}</view>
			</view>
			<navigator url="./information"><button type="default" style="width: 500rpx; height: 100rpx; margin-top: 400rpx; background-color:#999999" >修改个人信息</button></navigator>
			<button @click="goto()" style="width: 500rpx; height: 100rpx; margin-top: 100rpx; background-color:#FF5050" >退出登录</button>
		</view>
		
		
		
	</view>
</template>

<script>
	import{
		mapMutations
	}from 'vuex'
export default {
	
	data() {
		return {
			name:'',
			account:'',
			phone:''
		};
	},
	created() {
		// console.log("acacaca"+getApp().globalData.account), // 'test'
		wx.request({
			url:'http://192.168.137.1:8081/user/getUserByAccount',
			data:{
				account:uni.getStorageSync('userInfo').account
			},
			success:(response) => {
				this.name = response.data.name;
				this.account = response.data.account;
				this.phone = response.data.phone;
				console.log(response.data);
			}
		})
	},
	methods: {
		...mapMutations(['logout']),
		goto(){
			uni.reLaunch({
				url:'../index/index'
			})
		}
		
	}
};
</script>

<style>


</style>