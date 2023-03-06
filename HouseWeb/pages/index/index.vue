<template>	  
		 <div class="login-container">
			 <h3 class="login_title">系统登录</h3>
		      用户名:<input type="text" v-model="uname" placeholder="请输入用户名"/>
		      <br><br>
		      密码： <input type="password" v-model="passw" placeholder="请输入密码"/>
		      <br><br>
		      <button v-on:click="goto()">登录</button>
			  <br><br>
			  <text @click="register()" style="font-size: 30rpx; color: #265a52; margin-left: 70%;">新用户注册</text>
		  </div> 
		  
</template>

<script>
	import {
		mapMutations
		} from 'vuex'
export default{
	data(){
	return {
	      uname:'',
		  passw:''
	    };
},

methods:{
	...mapMutations(['login']),
	goto(){
		console.log("aaaaa"+this.uname+this.passw)
			if (this.uname.length <= 0 || this.passw.length <= 0) {
				uni.showToast({
				title: '账号或密码不能为空',
			    icon: 'none'
			    });
			    return;
			}
			// axios
			// .get("http://km8vys.natappfree.cc/loginPage/login",{params:user})
			wx.request({
				url:'http://127.0.0.1:8080/loginPage/login',
				data:{
					account:this.uname,
					password:this.passw
				},
				success:(response)=>{
						if(response.data.type == 2)
						{
							let userInfo={
								account:this.uname
							}
							this.login(userInfo)
							uni.redirectTo({
								url:'../user/user'
								// url:'../user/order?account='+ this.uname
							})
						}
						else if(response.data.type == 1)
						{
							let userInfo={
								account:this.uname
							}
							this.login(userInfo)
							uni.redirectTo({
								url:'../saler/saler'
							})
						}
						else if(response.data.type == 0)
						{
							let userInfo={
								account:this.uname
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
	register(){
		uni.navigateTo({
			url:'./register'
		})
		
	}
}
}
</script>

<style>
	.login-container {
	    border-radius: 15px;
	    background-clip: padding-box;
	    margin: 90px auto;
	    width: 350px;
	    padding: 35px 35px 15px 35px;
	    background: #fff;
	    border: 1px solid #eaeaea;
	    box-shadow: 0 0 25px #cac6c6;
	  }	  
	  .login_title {
	    margin: 0px auto 40px auto;
	    text-align: center;
	    color: #505458;
	  } 

</style>
