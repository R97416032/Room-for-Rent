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
			<view style="display: flex; margin-top: 60rpx;">
				<image style="width: 50rpx; height: 50rpx; margin-top: 30rpx; margin-left: 80rpx;" src="../../static/name1.png"></image>
				<view style="font-size: 40rpx; color: #555555; margin-top: 30rpx; margin-left: 20rpx;">姓名:</view>
				<input v-model="namexg" style="width: 400rpx; height: 80rpx; margin-top: 20rpx; margin-left: 10rpx; background-color:#F1F1F1" placeholder="请输入要修改的姓名"/>
			</view>
			<view style="display: flex; margin-top: 30rpx;">
				<image style="width: 50rpx; height: 50rpx; margin-top: 30rpx; margin-left: 40rpx;" src="../../static/tel.png"></image>
				<view style="font-size: 40rpx; color: #555555; margin-top: 30rpx; margin-left: 20rpx;">手机号:</view>
				<input v-model="phone" style="width: 400rpx; height: 80rpx; margin-top: 20rpx; margin-left: 10rpx; background-color:#F1F1F1" placeholder="请输入要修改的手机号"/>
			</view>
			<view style="display: flex; margin-top: 30rpx;">
				<view style="font-size: 40rpx; color: #555555; margin-top: 30rpx; margin-left: 70rpx;">修改密码:</view>
				<input v-model="password" style="width: 400rpx; height: 80rpx; margin-top: 20rpx; margin-left: 10rpx; background-color:#F1F1F1" placeholder="请输入修改密码并确保为3~20位"/>
			</view>
			<view style="display: flex; margin-top: 30rpx;">
				<view style="font-size: 40rpx; color: #555555; margin-top: 30rpx; margin-left: 70rpx;">确认密码:</view>
				<input v-model="passquery" style="width: 400rpx; height: 80rpx; margin-top: 20rpx; margin-left: 10rpx; background-color:#F1F1F1" placeholder="请再次确认修改密码"/>
			</view>
			<button @click="goto()" style="width: 500rpx; height: 100rpx; margin-top: 200rpx; background-color:#F0AD4E" >提交修改</button>
		</view>
		
		
		
	</view>
</template>

<script>
export default {
	
	data() {
		return {
			name:'',
			account:'',
			namexg:'',
			phone:'',
			password:'',
			passquery:''
		};
	},
	created() {
		wx.request({
			url:'http://192.168.137.1:8081/user/getUserByAccount',
			data:{
				account:uni.getStorageSync('userInfo').account
			},
			success:(response)=>{
				this.name = response.data.name;
				this.account = response.data.account;
			}
		})
	},
	methods: {
		goto(){
			if (this.phone.length!=11) {
				uni.showToast({
				title: '请确保手机号格式正确',
			    icon: 'none'
			    });
			    return;
			}
			if (this.password!=this.passquery) {
				uni.showToast({
				title: '请再次确认密码保证一致',
			    icon: 'none'
			    });
			    return;
			}
			if (this.password.length<3||this.password.length>20)
			{
				uni.showToast({
				title: '请确保输入密码为3~20位',
				icon: 'none'
				});
				return;
			}
			wx.request({
				url:'http://192.168.137.1:8081/user/updataUser',
				data:{
					account:uni.getStorageSync('userInfo').account,
					name:this.namexg,
					phone:this.phone,
					password:this.password
				},
				success:(response)=>{
					console.log("information:"+response.data)
					if(response.data=='OK'){
						uni.showModal({
							title: '提示',
							content: '您需要重新登陆登录',
							success: function(res) {
								if (res.confirm) {
									uni.reLaunch({
										url:'../index/index'
									})
								} else if (res.cancel) {
									uni.reLaunch({
										url:'../index/index'
									})
								}
							},
						})
					}
					if(response.data=='ERROR'){
						uni.showToast({
						title: '与原信息相符，不需要修改',
						icon: 'none'
						});
						return;
					}
				}
			})
		}
	}
};
</script>

<style>


</style>