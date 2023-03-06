<template>	  
		 <div class="login-container">
			 <h3 class="login_title">用户注册</h3>
		      用户名:<input type="text" v-model="uname" placeholder="请输入用户名"/>
		      <br><br>
		      密码： <input type="password" v-model="passw" placeholder="请输入密码并保证为3~20位"/>
		      <br><br>
			  确认密码： <input type="password" v-model="passt" placeholder="请确认输入密码一致"/>
			  <br><br>
			  姓名： <input type="text" v-model="namew" placeholder="请输入您的真实姓名"/>
			  <br><br>
			  手机号： <input type="text" v-model="numw" placeholder="请输入您的手机号"/>
		      <br><br>
		      <button @click="goto()">登录</button>
		  </div> 
		  
</template>

<script>
export default{
	data(){
	return {
	      uname:'',
	      passw:'',
	      passt:'',
	      namew:'',
	      numw:''
	    };
},

methods:{
	goto(){
			
				if (this.uname.length <= 0 || this.passw.length <= 0) {
					uni.showToast({
					title: '账号或密码不能为空',
				    icon: 'none'
				    });
				    return;
				}
				if (this.passw.length < 3 || this.passw.length > 20) {
					uni.showToast({
					title: '请确保密码长度为6~20位',
				    icon: 'none'
				    });
				    return;
				}
				if(this.namew.length <=0 || this.numw.length <= 0){
					uni.showToast({
						title:'请输入您的姓名和电话号码',
						icon:'none'
					});
					return;
				}
				if (this.passw!=this.passt) {
					uni.showToast({
					title: '请再次确认密码保证一致',
				    icon: 'none'
				    });
				    return;
				}
				if(this.numw.length!=11){
					uni.showToast({
						title:'请确认输入的电话号码格式是否正确',
						icon:'none'
					});
					return;
				}
	
					wx.request({
					url:'http://127.0.0.1:8080/loginPage/register',
					data:{
						account:this.uname,
						password:this.passw,
						name:this.namew,
						phone:this.numw
					},
					success:(response)=>{
						if(response.data=='OK')
						{
							uni.redirectTo({
								url:'./index'
							})	
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
	}
}
</script>

<style>
	.login-container {
	    border-radius: 15px;
	    background-clip: padding-box;
	    margin: 50px auto;
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
