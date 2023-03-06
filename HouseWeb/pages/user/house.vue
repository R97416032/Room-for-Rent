<template>
	<view style="margin-left: 15%;" >
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
		<view style="width: 100%; height: 0.1rpx; display: flex; border-radius: 10px;">
			<uni-collapse :accordion="true" style="width: 100%; display: flex; flex-direction:row">
				<uni-collapse-item title="位置距离" showAnimation="true" style="margin-left: 400rpx; width: 300rpx;" >
					<view style=" background-color:#FFFFFF;">
						<view style="width: 100%; height: 60rpx; text-align:center; background-color:#F8F8F8"  @click="search()">
							<text style="font-size: 30rpx;">全洛阳</text>
						</view>
						<view style="width: 100%; height: 60rpx; text-align:center; background-color:#F8F8F8"  @click="roomaddress('老城区')">
							<text style="font-size: 30rpx;">老城区</text>
						</view>
						<view style="width: 100%; height: 60rpx; text-align:center; background-color:#F8F8F8"  @click="roomaddress('西工区')">
							<text style="font-size: 30rpx;">西工区</text>
						</view>
						<view style="width: 100%; height: 60rpx; text-align:center; background-color:#F8F8F8"  @click="roomaddress('涧西区')">
							<text style="font-size: 30rpx;">涧西区</text>
						</view>
						<view style="width: 100%; height: 60rpx; text-align:center; background-color:#F8F8F8"  @click="roomaddress('洛龙区')">
							<text style="font-size: 30rpx;">洛龙区</text>
						</view>
						<view style="width: 100%; height: 60rpx; text-align:center; background-color:#F8F8F8"  @click="roomaddress('吉利区')">
							<text style="font-size: 30rpx;">吉利区</text>
						</view>
					</view>
				</uni-collapse-item>
				<uni-collapse-item title="结构房型" showAnimation="true" style="margin-left: 300rpx; width: 300rpx; " >
					<view style=" background-color: #FFFFFF;">
						<view style="width: 100%; height: 60rpx; text-align:center; background-color:#F8F8F8"  @click="search()">
							<text style="font-size: 30rpx;">全部房型</text>
						</view>
						<view style="width: 100%; height: 60rpx; text-align:center; background-color:#F8F8F8"  @click="roomstructure('一室一厅')">
							<text style="font-size: 30rpx;">一室一厅</text>
						</view>
						<view style="width: 100%; height: 60rpx; text-align:center; background-color:#F8F8F8"  @click="roomstructure('二室一厅')">
							<text style="font-size: 30rpx;">二室一厅</text>
						</view>
						<view style="width: 100%; height: 60rpx; text-align:center; background-color:#F8F8F8"  @click="roomstructure('三室一厅')">
							<text style="font-size: 30rpx;">三室一厅</text>
						</view>
						<view style="width: 100%; height: 60rpx; text-align:center; background-color:#F8F8F8"  @click="roomstructure('三室两厅')">
							<text style="font-size: 30rpx;">三室两厅</text>
						</view>
						<view style="width: 100%; height: 60rpx; text-align:center; background-color:#F8F8F8"  @click="roomstructure('四室两厅')">
							<text style="font-size: 30rpx;">四室两厅</text>
						</view>
						<view style="width: 100%; height: 60rpx; text-align:center; background-color:#F8F8F8"  @click="roomstructure('五室三厅')">
							<text style="font-size: 30rpx;">五室三厅</text>
						</view>
					</view>
				</uni-collapse-item>
				<uni-collapse-item title="价格区间" showAnimation="true" style="margin-left: 300rpx; width: 300rpx;" >
					<view style=" background-color: #FFFFFF;">
						<view style="display: flex;">
							<view style="font-size: 35rpx;">从: </view>
							<input style="height: 40rpx; width: 120rpx; margin-left: 10rpx; background-color: #E4E4E4;" v-model="pricemin" />
						</view>
						<view style="display: flex;">
							<view style="font-size: 35rpx; margin-top: 40rpx">到: </view>
							<input style="height: 40rpx; width: 120rpx; margin-left: 10rpx; margin-top: 40rpx; background-color: #E4E4E4;" v-model="pricemax" />
						</view>
						<button style="height: 60rpx; width: 200rpx; margin-top: 40rpx; font-size: 32rpx; background-color: #F3C9C3;" @click="roomprice()">确认</button>
						<button style="height: 60rpx; width: 200rpx; margin-top: 25rpx; font-size: 32rpx; background-color: #C0C0C0;" @click="search()">重置</button>
					</view>
				</uni-collapse-item>
			</uni-collapse>
		</view>
		<view style="display: flex;flex-direction: row;">
			<!-- 商品列表信息情况页面 -->
			<view style="padding: 50rpx 20upx 20upx 20rpx; width: 100%;">
				<view @click="room(item.id)" style="border-bottom:1upx solid #e7e7e7;padding: 10upx 0 10upx 0; display: flex;" class="flex justify-around bg-white" v-for="(item,index) in user"  :key='index'>
					<view style="width: 250upx;">
						<image style="width: 250upx;height: 300upx; border-radius: 10px;" :src="onloadimg(item.id)"></image>
					</view>
					<view  style="margin-left: 10rpx; width: 1000upx;flex-direction: column;" class="flex justify-between">
						<!-- 上面 -->
						<view>
							<!-- 这里是根内容 -->
							<view class="flex align-center" style="font-weight: 600;font-size: 40rpx;">
								<text class="cu-tag bg-gradual-orange sm round" >{{item.name}}</text>
							</view>
							
						</view>
						<!-- 下面 -->
						<view style="margin-bottom: 10upx;padding-top: 10upx;">
							<view style=" display: flex; flex-direction: row;">
								<view class="cu-tag sm bg-red radius" style="font-size: 35rpx;color: #808080;margin-top: 10rpx;">地处{{item.address}}</view>
								<text style="color:#F0AD4E;font-size: 90%;margin-top: 10rpx; margin-left: 40rpx;">房间构造: {{item.structure}}</text>
							</view>
							<view style=" display: flex; flex-direction: row;">
								<view class="cu-tag sm bg-orange radius" style="font-size: 35rpx;margin-top: 10rpx;">房间面积: {{item.area}}平</view>
								<text style="color: grey;font-size: 35rpx; margin-top: 10rpx; margin-left: 90rpx;">房间状态: {{item.state}}</text>
							</view>
							<text style="color: #DD524D;font-size: 70rpx;float: right;margin-top: 50rpx;">￥{{item.price}}/月</text>
						</view>
					</view>
				</view>
			</view>
			<image src="../../static/tishi.jpg" style="height: 1000rpx;"></image>
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
				url:'http://127.0.0.1:8080/user/getAllRooms',
				success: (response) => {
					this.user = response.data;
					console.log(response.data)
				}
			})
		},
		methods: {
			onloadimg(id){
				var url="http://127.0.0.1:8080/"+id+".jpg"
				console.log("aaaa:"+url)
				return url
			},
			room(id){
				uni.navigateTo({
					url:'./houseallthing?id='+id,
					
				})
			},
			search(){
				
				wx.request({
				url:'http://127.0.0.1:8080/user/getRoomsByName',
				data:{
					name:this.search_input
				},
				success: (response) => {
					this.user = response.data
				}
				})
			},
			roomaddress(e){
				wx.request({
					url:'http://127.0.0.1:8080/user/getRoomsByAddress',
					data:{
						address:e
					},
					success: (response) => {
						this.user = response.data
					}
				})
			},
			roomstructure(e){
				wx.request({
					url:'http://127.0.0.1:8080/user/getRoomsByStructure',
					data:{
						structure:e
					},
					success: (response) => {
						this.user = response.data
					}
				})
			},
			roomprice(){
				wx.request({
					url:'http://127.0.0.1:8080/user/getRoomsByPrice',
					data:{
						min:this.pricemin,
						max:this.pricemax
					},
					success: (response) => {
						this.user = response.data
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
