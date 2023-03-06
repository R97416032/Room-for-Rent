<template>
	<view>
		<uni-forms :value="formData" ref="form" validate-trigger="bind" err-show-type="toast">
			<uni-group title="业务员信息" top="0">
				<uni-forms-item name="name" required label="姓名">
					<uni-easyinput type="text" :inputBorder="true" v-model="formData.name" placeholder="请输入用户名"></uni-easyinput>
				</uni-forms-item>
				<!-- 使用原生input，需要绑定binddata -->
				<uni-forms-item name="phone" required label="电话">
					<input type="text" v-model="formData.phone" class="uni-input-border" placeholder="请输入电话">
				</uni-forms-item>
				<uni-forms-item name="account" required label="账户">
					<input type="text" v-model="formData.account" class="uni-input-border" placeholder="请输入账户">
				</uni-forms-item>
				<uni-forms-item name="password" required label="密码">
					<uni-easyinput type="password" v-model="formData.password" placeholder="请输入密码"></uni-easyinput>
				</uni-forms-item>
			</uni-group>
 
			<!-- 直接使用组件自带submit、reset 方法，小程序不生效 -->
			<!-- 			<button class="button" form-type="submit">Submit</button>
				<button class="button" form-type="reset">Reset</button> -->

			<view class="example">
				<button class="button" @click="close">返回</button>
				<button class="button" @click="submitForm('form')">提交</button>
				<button class="button" @click="resetForm">重置</button>
			</view>
		</uni-forms>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				formData: {
					name: '',
					phone: '',
					account: '',
					password: "",
					type:1
				},
				show: false,
				rules: {
					name: {
						rules: [{
							required: true,
							errorMessage: '请输入姓名',
						}, {
							minLength: 3,
							maxLength: 15,
							errorMessage: '姓名长度在 {minLength} 到 {maxLength} 个字符',
						}]
					},
					phone: {
						rules: [{
							required: true,
							errorMessage: '请输入电话',
						}, {
							minLength: 11,
							maxLength: 11,
							errorMessage: '电话长度11个字符',
						}]
					},
					account: {
						rules: [{
							required: true,
							errorMessage: '请输入账户',
						}, {
							minLength: 3,
							maxLength: 20,
							errorMessage: '账户长度大于 {minLength} ，小于 {maxLength} 个字符',
						}]
					},
					password: {
						rules: [{
							required: true,
							errorMessage: '请输入密码',
						}, {
							minLength: 3,
							maxLength: 20,
							errorMessage: '密码长度大于 {minLength} ，小于 {maxLength} 个字符',
						}]
					}
				}
			}
		},
		onLoad() {
			uni.showLoading()
			// this.formData 应该包含所有需要校验的表单
			// 模拟异步请求数据
			setTimeout(() => {
				this.formData = {
					name:'',
					phone:'',
					account:'',
					password:'',
					type:1
				}
				uni.hideLoading()
			}, 500)
		},
		onReady() {
			this.$refs.form.setRules(this.rules)
		},
		methods: {
			close(){
				uni.navigateTo({
					url:'./admin'
				})
			},
			change(name, value) {
				this.formData.checked = value
				this.$refs.form.setValue(name, value)
			},

			/**
			 * 手动提交
			 * @param {Object} form
			 */
			submitForm(form) {
				console.log(this.formData);
				
				this.$refs[form].submit(
					uni.request({
						data:this.formData,
						url:'http://127.0.0.1:8080/manage/insertOperater'
					})
				)
					.then(
						uni.navigateTo({
							url:'./admin'
						})
					).catch((errors) => {
						
					})
			},

			/**
			 * 手动重置表单
			 */
			resetForm() {
				this.$refs.form.resetFields()
			},
			/**
			 * 部分表单校验
			 * @param {Object} form
			 */
			validateField(form) {
				this.$refs[form].validateField(['name', 'email']).then((res) => {
					uni.showToast({
						title: '验证成功'
					})
					console.log('表单的值：', res);
				}).catch((errors) => {
					console.error('验证失败：', errors);
				})
			},

			/**
			 * 清除表单校验
			 * @param {Object} form
			 * @param {Object} name
			 */
			clearValidate(form, name) {
				if (!name) name = []
				this.$refs[form].clearValidate(name)
			},
			datetimeChange(e) {
				this.formData.time = e
			}
		}
	}
</script>

<style>
	/* 头条小程序组件内不能引入字体 */
	/* #ifdef MP-TOUTIAO */
	@font-face {
		font-family: uniicons;
		font-weight: normal;
		font-style: normal;
		src: url("~@/static/uni.ttf") format("truetype");
	}

	/* #endif */
	/* #ifndef APP-NVUE */
	page {
		display: flex;
		flex-direction: column;
		box-sizing: border-box;
		background-color: #efeff4;
		min-height: 100%;
		height: auto;
	}

	view {
		font-size: 14px;
		line-height: inherit;
	}

	.example {
		padding: 0 15px 15px;
	}

	.example-info {
		padding: 15px;
		color: #3b4144;
		background: #ffffff;
	}

	.example-body {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		flex-wrap: wrap;
		justify-content: center;
		padding: 0;
		font-size: 14px;
		background-color: #ffffff;
	}

	/* #endif */
	.example {
		padding: 0 15px;
	}

	.example-info {
		/* #ifndef APP-NVUE */
		display: block;
		/* #endif */
		padding: 15px;
		color: #3b4144;
		background-color: #ffffff;
		font-size: 14px;
		line-height: 20px;
	}

	.example-info-text {
		font-size: 14px;
		line-height: 20px;
		color: #3b4144;
	}

	.example-body {
		flex-direction: column;
		padding: 15px;
		background-color: #ffffff;
	}

	.word-btn-white {
		font-size: 18px;
		color: #FFFFFF;
	}

	.word-btn {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		align-items: center;
		justify-content: center;
		border-radius: 6px;
		height: 48px;
		margin: 15px;
		background-color: #007AFF;
	}

	.word-btn--hover {
		background-color: #4ca2ff;
	}

	.example {
		padding: 0 10px 10px;
	}

	.uni-input-border,
	.uni-textarea-border {
		width: 100%;
		font-size: 14px;
		color: #666;
		border: 1px #e5e5e5 solid;
		border-radius: 5px;
		box-sizing: border-box;
	}

	.uni-input-border {
		padding: 0 10px;
		height: 35px;
	}

	.uni-textarea-border {
		padding: 10px;
		height: 80px;
	}

	.label-box {
		margin-right: 10px;
	}

	.transform-scale {
		transform: scale(0.7);
	}

	.button {
		margin: 10px auto;
	}
</style>