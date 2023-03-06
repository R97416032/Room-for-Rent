<template>
    <view  >
    	<image class="bg-set" src='../../static/bg.jpg'></image>
        <uni-segmented-control class="segmented"
    		:current="current" 
    		:values="items" 
    		@clickItem="onClickItem" 
    		style-type="button" 
    		active-color="#007AFF" >
    	</uni-segmented-control>
        <view>
            <view v-if="current === 0">
                
    			<view  style="display: flex; flex-direction: column;">
    				<view style="display: flex; flex-direction: row; margin-top: 50%; margin-left: 10%;">
    					<image style="width: 20px; height: 20px;" src="../../static/account.png"></image>
    					<text style="font-size: 13px; align-self: center; margin-left: 5px;">账号</text>
    				</view>
    				<input type="text" placeholder="请输入你的账号" class="input" v-model="account" />
    				
    				<view style="display: flex; flex-direction: row; margin-top: 10%; margin-left: 10%;">
    					<image style="width: 20px; height: 20px;" src="../../static/password.png"></image>
    					<text style="font-size: 13px; align-self: center; margin-left: 5px;">密码</text>
    				</view>
    				<input type="password" placeholder="请输入你的密码" class="input" v-model="password"/>
    				
    				<button class="bt_login" @click="loginGoto">登录</button>
    			</view>
    			
            </view>
            <view v-if="current === 1">
                <view  style="display: flex; flex-direction: column;">
                	<view style="display: flex; flex-direction: row; margin-top: 37%; margin-left: 10%;">
                		<image style="width: 20px; height: 20px;" src="../../static/account.png"></image>
                		<text style="font-size: 13px; align-self: center; margin-left: 5px;">账号</text>
                	</view>
                	<input type="text" placeholder="请输入你的账号" class="input" v-model="register_account"/>
                	
                	<view style="display: flex; flex-direction: row; margin-top: 5%; margin-left: 10%;">
                		<image style="width: 20px; height: 20px;" src="../../static/password.png"></image>
                		<text style="font-size: 13px; align-self: center; margin-left: 5px;">密码</text>
                	</view>
                	<input type="password" placeholder="请输入你的密码并确保3~20位" class="input" v-model="register_password"/>
                	
    				<view style="display: flex; flex-direction: row; margin-top: 5%; margin-left: 10%;">
    					<image style="width: 20px; height: 20px;" src="../../static/password.png"></image>
    					<text style="font-size: 13px; align-self: center; margin-left: 5px;">确认密码</text>
    				</view>
    				<input type="password" placeholder="确认你的密码" class="input" v-model="register_confirm_password"/>
    				
    				<view style="display: flex; flex-direction: row; margin-top: 5%; margin-left: 10%;">
    					<image style="width: 20px; height: 20px;" src="../../static/account.png"></image>
    					<text style="font-size: 13px; align-self: center; margin-left: 5px;">姓名</text>
    				</view>
    				<input type="text" placeholder="请输入你的姓名" class="input" v-model="register_name"/>
    				
    				<view style="display: flex; flex-direction: row; margin-top: 5%; margin-left: 10%;">
    					<image style="width: 20px; height: 20px;" src="../../static/account.png"></image>
    					<text style="font-size: 13px; align-self: center; margin-left: 5px;">手机号码</text>
    				</view>
    				<input type="text" placeholder="请输入你的手机号码" class="input" v-model="register_phone"/>
    				
                	<button class="bt_register" @click="registerGoto">注册</button>
                </view>
            </view>
        </view>
    </view>
</template>

<script>
	import{
		mapMutations
	}from 'vuex'
	
	import uniSegmentedControl from "../../components/uni-segmented-control/uni-segmented-control.vue"
	export default{
		components: {uniSegmentedControl},
		data() {
			return {
				items: ['登录','注册'],
				current: 0,
				
				account:null,
				password:null,
				
				register_account:null,
				register_password:null,
				register_confirm_password:null,
				register_name:null,
				register_phone:null,
			}
		},
		onLaunch:function(){
			const userInfo = uni.getStorageSync('userInfo');
			if (userInfo !=="") {
				this.login(userInfo)
				}
		},
		methods: {
			...mapMutations(['login']),
			onClickItem(e) {
			            if (this.current !== e.currentIndex) {
			                this.current = e.currentIndex;
			            }
			},
			loginGoto(){//登录
				if (this.account.length <= 0 || this.password.length <= 0) {
						uni.showToast({
						title: '账号或密码不能为空',
						icon: 'none'
				    });
				    return;
				}
				wx.request({
					url:'http://192.168.137.1:8081/loginPage/login',
					data:{
						account:this.account,
						password:this.password
					},
					success:(response)=>{
							if(response.data.type == 2)
							{
								let userInfo = {
									account :response.data.account
								}
								this.login(userInfo)
								uni.redirectTo({
									url:'../user/user'
								})
							}
							else if(response.data.type == 1)
							{
								let userInfo = {
									account :response.data.account
								}
								this.login(userInfo)
								uni.redirectTo({
									url:'../saler/saler'
								})
							}
							else if(response.data.type == 0)
							{
								let userInfo = {
									account :response.data.account
								}
								this.login(userInfo)
								uni.redirectTo({
									url:'../admin/admin'
								})
							}
							else{
								uni.showToast({
									title: '账号或密码错误，请重新登录',
									icon: 'none'
								});
								return;
							}		
					}	
				})
			},
			registerGoto(){//注册
				if (this.register_account.length <= 0 || this.register_account.length <= 0) {
							uni.showToast({
							title: '账号或密码不能为空',
						    icon: 'none'
						    });
						    return;
						}
						if(this.register_name.length <=0 || this.register_phone.length <= 0){
							uni.showToast({
								title:'请输入您的姓名和电话号码',
								icon:'none'
							});
							return;
						}
						if (this.register_password>20||this.register_password<3)
						{
							uni.showToast({
							title: '请确保密码符合6~20位',
							icon: 'none'
							});
							return;
						}
						if (this.register_password!=this.register_confirm_password) {
							uni.showToast({
							title: '请再次确认密码保证一致',
						    icon: 'none'
						    });
						    return;
						}
						if (this.register_phone.length!=11){
							uni.showToast({
							title: '请确保您的手机号格式正确',
							icon: 'none'
							});
							return;
						}
				
							wx.request({
							url:'http://192.168.137.1:8081/loginPage/register',
							data:{
								account:this.register_account,
								password:this.register_password,
								name:this.register_name,
								phone:this.register_phone
							},
							success:(response)=>{
								if(response.data=='OK')
								{
									this.current=0  //注册操作完成就跳到登录	
								}
								if(response.data=='ERROR')
								{
									uni.showToast({
									title: '该用户名已被注册，请重新输入',
									icon: 'none'
									});
									return;
								}
							}
				})
			}
		},
	}
</script>

<style>
.bt_register{
		font-size: 15px;
		
		width: 60%;
		height: 80rpx;
		margin-top: 10%;
		background: linear-gradient(to right, #006eff,#00BFFF );
		box-shadow: -1px 4px 6px 3px rgba(0, 81, 255, 0.4);
	}
	.bt_login{
		font-size: 15px;
		
		width: 60%;
		height: 80rpx;
		margin-top: 20%;
		background: linear-gradient(to right, #006eff,#00BFFF );
		box-shadow: -1px 4px 6px 3px rgba(0, 81, 255, 0.4);
	}
	.input{
		margin-top: 20rpx;
		margin-left: 11%;
		margin-right: 10%;
		font-size: 15px;
		border-bottom-style: solid;
		border-bottom-width: thin;
	}
	.bg-set{
	    position: fixed;
	    width: 100%;
	    height: 100%;
	    top: 0;
	    left: 0;
	    z-index: -1;
	}
	.a{
		background-color: rgba(255,255,255,0.1);
	}
	.segmented{
		position: fixed;
		top: 10%;
		left: 20%;
		width: 60%;
	}
</style>