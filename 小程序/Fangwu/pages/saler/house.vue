<template>
	<view >
		<view class="example-body">
			<uni-nav-bar :fixed="false" color="#333333" background-color="#FFFFFF" >
				<block slot="left">
					<view class="city">
						<view><text class="uni-nav-bar-text">{{ city }}</text></view>
						<uni-icons type="arrowdown" color="#333333" size="22" />
					</view>
				</block>
				<view class="input-view">
					<uni-icons class="input-uni-icon" type="search" size="22" color="#666666" />
					<input confirm-type="search" class="nav-bar-input" type="text" placeholder="输入搜索关键词" v-model="search_input">
				</view>
				<button style="width: 120rpx; display: flex; height: 60rpx; margin-top: 2rpx; margin-left: 10rpx; font-size: 27rpx; background-color: #F1F1F1;" @click="search()">搜索</button>
			</uni-nav-bar>
		</view>
		
		<!-- 商品列表信息情况页面 -->
		<view style="padding: 20rpx 20upx 20upx 20rpx;">
			<view @click="room(item.id,item.state)" style="border-bottom:1upx solid #e7e7e7;padding: 10upx 0 10upx 0; display: flex;" class="flex justify-around bg-white" v-for="(item,index) in user"  :key='index'>
				<view style="width: 250upx;">
					<image style="width: 250upx;height: 300upx; border-radius: 10px;" :src="onloadimg(item.id)"></image>
				</view>
				<view  style="margin-left: 10rpx; width: 400upx;flex-direction: column;" class="flex justify-between">
					<!-- 上面 -->
					<view>
						<!-- 这里是根内容 -->
						<view class="flex align-center" style="font-weight: 600;font-size: 40rpx;">
							<text class="cu-tag bg-gradual-orange sm round" >{{item.name}}</text>
						</view>
						
					</view>
					<!-- 下面 -->
					<view style="margin-bottom: 10upx;padding-top: 10upx;">
						<view>
							<view class="cu-tag sm bg-red radius" style="font-size: 25rpx;color: #808080;margin-top: 10rpx;">地处{{item.address}}</view>
							<text style="color:#F0AD4E;font-size: 90%;">房间构造: {{item.structure}}</text>
						</view>
						<view style="font-size: 120%;">
							<view class="cu-tag sm bg-orange radius" style="font-size: 30rpx;margin-top: 10rpx;">房间面积: {{item.area}}平</view>
							<text style="color: grey;font-size: 30rpx;">房间状态: {{item.state}}</text>
							<text style="color: #DD524D;font-size: 40rpx;float: right;">￥{{item.price}}/月</text>
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
	import uniIcons from'../../components/uni-icons/uni-icons.vue'
	import uniNavBar from '../../components/uni-nav-bar/uni-nav-bar.vue'
	import uniSection from '../../components/uni-section/uni-section.vue'
	export default {
		components: {
			uniIcons,
			uniNavBar,
			uniSection
		},
		data() {
			return {
				city:'洛阳',
				user: {},
				pricemin:'',
				pricemax:'',
				search_input:''
			}
		},
		// mounted() {
		// 	wx.request({
		// 		url:'http://pgqz97.natappfree.cc/user/freeRooms',
		// 		data:{
		// 			name:this.name,
		// 		},
		// 		success:(response)=>{
		// 			this.name = response.data.name;
		// 			}
		// 		})
		// },
		created() {
			wx.request({
				url:'http://192.168.137.1:8081/user/getAllRooms',
				success: (response) => {
					this.user = response.data;
					console.log(response.data)
				}
			})
		},
		methods: {
			onloadimg(id){
				var url="http://192.168.137.1:8081/"+id+".jpg"
				console.log("aaaa:"+url)
				return url
			},
			room(id,state){
				if(state=="空闲"){
					uni.navigateTo({
						url:'./houseallthing?id='+id
						
					})
				}
				if(state=="已租"){
					uni.navigateTo({
						url:'./houseallthingyz?id='+id
						
					})
				}
					
				
			},
			search(){
				
				wx.request({
				url:'http://192.168.137.1:8081/user/getRoomsByName',
				data:{
					name:this.search_input
				},
				success: (response) => {
					this.user = response.data
					console.log("bababa"+this.user)
				}
				})
			}
		}
	}
</script>

<style>
	.example-body {
		flex-direction: row;
		flex-wrap: wrap;
		justify-content: center;
		padding: 0;
		font-size: 14px;
		background-color: #ffffff;
	}
	.uni-nav-bar-text {
		font-size: 28rpx;
	}
	.city {
			/* #ifndef APP-PLUS-NVUE */
			display: flex;
			/* #endif */
			flex-direction: row;
			align-items: center;
			justify-content: flex-start;
			/* width: 160rpx;
	*/
			margin-left: 4px;
		}
	.input-view {
			/* #ifndef APP-PLUS-NVUE */
			display: flex;
			/* #endif */
			flex-direction: row;
			/* width: 500rpx;
	*/
			flex: 1;
			background-color: #f8f8f8;
			height: 30px;
			width: ;
			border-radius: 15px;
			padding: 0 15px;
			flex-wrap: nowrap;
			margin: 7px 0;
			line-height: 30px;
		}
	.input-uni-icon {
		line-height: 30px;
	}
	.nav-bar-input {
		height: 30px;
		line-height: 30px;
		/* #ifdef APP-PLUS-NVUE */
		width: 100%;
		/* #endif */
		padding: 0 5px;
		font-size: 28rpx;
		background-color: #f8f8f8;
	}
</style>
