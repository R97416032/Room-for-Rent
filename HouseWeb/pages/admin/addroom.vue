<template>
	<view>
		<uni-forms :value="formData" ref="form" validate-trigger="bind" err-show-type="toast">
			<uni-group title="房间信息" top="0" >
				<view style="display: flex;flex-direction: row;">
				<view style="margin-left: 20%;">
				<uni-forms-item name="name" required label="房名">
					<uni-easyinput type="text" :inputBorder="true" v-model="formData.name" style="width: 250px;" placeholder="请输入房间名"></uni-easyinput>
				</uni-forms-item>
				<!-- 使用原生input，需要绑定binddata -->
				<uni-forms-item name="address" required label="地址">
					<input type="text" v-model="formData.address" class="uni-input-border" style="width: 250px;" placeholder="请输入房间地址">
				</uni-forms-item>
				<uni-forms-item name="area" required label="面积" >
					<input type="text" v-model="formData.area" class="uni-input-border"style="width: 250px;" placeholder="请输入房间面积">
				</uni-forms-item>
				<uni-forms-item name="price" required label="日租">
					<uni-easyinput type="text" v-model="formData.price"style="width: 250px;" placeholder="请输入密码"></uni-easyinput>
				</uni-forms-item>
				<uni-forms-item name="structure" required label="结构">
					<uni-easyinput type="text" :inputBorder="true" v-model="formData.structure"style="width: 250px;" placeholder="请输入房间日租金"></uni-easyinput>
				</uni-forms-item>
				<uni-forms-item name="owner" required label="房东姓名">
					<uni-easyinput type="text" :inputBorder="true" v-model="formData.owner"style="width: 250px;" placeholder="请输入房东姓名"></uni-easyinput>
				</uni-forms-item>
				<uni-forms-item name="ownerphone" required label="房东联系方式">
					<uni-easyinput type="text" :inputBorder="true" v-model="formData.ownerphone"style="width: 250px;" placeholder="请输入房东联系方式"></uni-easyinput>
				</uni-forms-item>
				</view>
				<view>
					<image :src="imgPaths[0]" style="width: 300px;height: 300px; margin-left: 40%;margin-top: 5%;"></image>
					<button class="button" v-if="ifsubmite==1" style="margin-left: 40%;margin-top: 5%;" @click="choose" >上传图片</button>
				</view>
				</view>
			</uni-group>
 
			<!-- 直接使用组件自带submit、reset 方法，小程序不生效 -->
			<!-- 			<button class="button" form-type="submit">Submit</button>
				<button class="button" form-type="reset">Reset</button> -->

			<view class="example" style="display: flex; flex-direction: row;">
				<button class="button" @click="close">返回</button>
				<button class="button" @click="resetForm">重置</button>
				<button class="button" @click="submitForm('form')">提交</button>
			</view>
		</uni-forms>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				imgPaths:[],
				ifsubmite:0,
				
				formData: {
					name: '',
					state:'空闲',
					user:'',
					address:'',
					area:'',
					price:'',
					structure:'',
					owner:'',
					ownerphone:''
				},
				rules: {
					name: {
						rules: [{
							required: true,
							errorMessage: '请输入房名',
						}]
					},
					address: {
						rules: [{
							required: true,
							errorMessage: '请输入地址',
						}]
					},
					area: {
						rules: [{
							required: true,
							errorMessage: '请输入面积',
						}]
					},
					price: {
						rules: [{
							required: true,
							errorMessage: '请输入价格',
						}]
					},
					structure: {
						rules: [{
							required: true,
							errorMessage: '请输入房间结构',
						}]
					},
					owner: {
						rules: [{
							required: true,
							errorMessage: '请输入房东姓名',
						}]
					},
					ownerphone: {
						rules: [{
							required: true,
							errorMessage: '请输入房东联系方式',
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
					name: '',
					state:'空闲',
					user:'',
					address:'',
					area:'',
					price:'',
					structure:'',
					owner:'',
					ownerphone:''
				}
				uni.hideLoading()
			}, 500)
		},
		onReady() {
			this.$refs.form.setRules(this.rules)
		},
		methods: {
			choose(e){
				var me = this;
				var idroom = null
				uni.request({
					url:'http://127.0.0.1:8080/test/getMaxRoomId',
					success: (e) => {
						idroom = e.data
					}
				})
				uni.chooseImage({
					count: 6, //最多可以选择的图片张数，默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择;
											//album 从相册选图，camera 使用相机，默认二者都有。
											//如需直接开相机或直接选相册，请只使用一个选项
					success: function (res) {//成功则返回图片的本地文件路径列表 tempFilePaths
						me.imgPaths = res.tempFilePaths;
						console.log(me.imgPaths);
						uni.uploadFile({
							url: 'http://127.0.0.1:8080/test/uploadImg', 
							filePath: me.imgPaths[0],//要上传文件资源的路径
							name: 'uploadFile',//文件对应的 key , 
												//开发者在服务器端通过这个 key 可以获取到文件二进制内容
							formData: {//HTTP 请求中其他额外的 form data
								roomid: idroom,
							},
							success: (uploadFileRes) => {
									console.log(uploadFileRes);
									uni.showModal({
										title: '提示',
										content: '图片上传成功',
										success: function(res) {
										if (res.confirm) {
											
										} else if (res.cancel) {
											}
										},
									})
							}
						});
					}
				});
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
						url:'http://127.0.0.1:8080/manage/insertRoom'
					})
				)
					.then(
						uni.showModal({
							title: '提示',
							content: '信息上传成功，可以上传房间图片了',
							success: function(res) {
							if (res.confirm) {
							}
							},
						}),
						this.ifsubmite=1
					).catch((errors) => {
						
					})
			},
			close(){
				uni.navigateTo({
					url:'./admin'
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