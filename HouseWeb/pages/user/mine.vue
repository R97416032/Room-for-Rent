<template>
	<view style="margin-left: 15%;display: flex; flex-direction: row;">
		
		<view style="width: 700rpx; height: 700rpx; margin-left: 200rpx; margin-top: 100px;">
			<view style="display: flex; ">
				<image style=" width: 150rpx; height: 150rpx; margin-left: 40rpx;" src="../../static/touxiang.png"></image>
			
				<view>
					<view style="font-size: 60rpx; color: #333333; margin-left: 20rpx;">姓名: {{name}}</view>
					<view style="font-size: 60rpx; color: #808080; margin-top: 20rpx; margin-left: 20rpx;">账号: {{account}}</view>
				</view>
			</view>
			<view style="display: flex; margin-top: 30rpx;">
				<image style="width: 85rpx; height: 85rpx; margin-top: 20rpx; margin-left: 101rpx;" src="../../static/tel.png"></image>
				<view style="font-size: 60rpx; color: #555555; margin-top: 20rpx; margin-left: 20rpx;">手机号: {{phone}}</view>
			</view>
			<navigator url="./information"><button type="default" style="width: 500rpx; height: 100rpx; margin-top: 400rpx; background-color:#999999" >修改个人信息</button></navigator>
		</view>
		<button @click="goto()" style="width: 500rpx; height: 100rpx; margin-top: 980rpx; background-color:#FF5050" >退出登录</button>
		
		
		
	</view>
</template>

<script>
	import {
		mapMutations
		} from 'vuex'
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
			url:'http://127.0.0.1:8080/user/getUserByAccount',
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
			this.logout(null)
			uni.reLaunch({
				url:'../index/index'
			})
		}
		
	}
};
</script>

<style>


</style>