<template>
	<view class="container">
		<image class="bg-set" :src="onloadimg(room.id)"></image>
		<view class="content" style="width: 94%;height: 30%; margin-top: 37%;margin-left:3%; border-radius: 10px; background-color: #FFFFFF;">
			<view style="width: 100%;height: 10rpx;"></view>
			<view style="width: 100%;height: 30%; display: flex; margin-bottom: 20rpx;">
				<view style="width: 50%;">
					<view style="font-size: 45rpx; margin-top: 10rpx; margin-left: 5%; color: #F0AD4E;">{{room.name}}</view>
					<view style="font-size: 33rpx; margin-top: 10rpx; margin-left: 5%; color:#808080">房型: {{room.structure}}</view>
				</view>
				<view style="display: flex;">
					<image style="width: 60rpx; height: 60rpx; margin-top: 15%; margin-left: 70rpx" src="../../static/state.png"></image>
					<view style="font-size: 45rpx; margin-top: 15%; margin-left: 5rpx; color:#DD524D;">{{room.state}}中</view>
				</view>
			</view>
			<view style="width: 100%; height: 1rpx; background-color: #555555;"></view>
			<view style="width: 100%;height: 70%; margin-top: 20rpx;">
				<view style="font-size: 31rpx; margin-top: 15rpx; margin-left: 5%;">面积: {{room.area}}平</view>
				<view style="font-size: 30rpx; margin-top: 15rpx; margin-left: 5%;">地址: {{room.address}}</view>
				<view style="font-size: 31rpx; margin-top: 15rpx; margin-left: 5%;">房东: {{room.owner}}</view>
				<view style="font-size: 31rpx; margin-top: 15rpx; margin-left: 5%;">房东联系方式: {{room.ownerphone}}</view>
				<view style="font-size: 37rpx; margin-top: 15rpx; margin-left: 60%; color: #DD524D;">房价: {{room.price}}元/月</view>
			</view>
		</view>
		<view style="width: 94%; height: 40%; margin-top: 10rpx; margin-left: 3%;">
			<view style="width: 80%; height: 35rpx; display: flex;">
				<text style="font-size: 35rpx;">租房用户: </text>
				<input v-model="useraccount" placeholder="在此处输入用户账号" style="margin-left: 10rpx; font-size: 35rpx; background-color: #E4E4E4;"></input>
			</view>
			<view style="margin-top: 50rpx; width: 80%; height: 35rpx; display: flex;">
				<text style="font-size: 35rpx;">起始时间: </text>
				<view class="uni-list">
				    <view class="uni-list-cell">
				        <view class="uni-list-cell-db">
				            <picker mode="date" :value="startdate" :start="startDate" :end="endDate" @change="bindDatestart">
				                <view class="uni-input">{{startdate}}</view>
				            </picker>
				        </view>
				    </view>
				</view>
			</view>
			<view style="margin-top: 50rpx; width: 80%; height: 35rpx; display: flex;">
				<text style="font-size: 35rpx;">截至时间: </text>
				<view class="uni-list">
				    <view class="uni-list-cell">
				        <view class="uni-list-cell-db">
				            <picker mode="date" :value="enddate" :start="startDate" :end="endDate" @change="bindDateend">
				                <view class="uni-input">{{enddate}}</view>
				            </picker>
				        </view>
				    </view>
				</view>
			</view>
			<button style="width: 60%; margin-left: 20%; margin-top: 100rpx; color:#3F536E" @click="renthouse()">办理租房</button>	
		</view>
		
	</view>
</template>

<script>
export default {
	
	data() {
		const currentDate = this.getDate({
		    format: true
		})
		return {
			useraccount:'',
			startdate: currentDate,
			enddate: currentDate,
			allprice:'',
			room:{},
			
		};
	},
	computed: {
	    startDate() {
	        return this.getDate('start');
	    },
	    endDate() {
	        return this.getDate('end');
	    }
	},
	onLoad:function(option){
		console.log("1111"+option.id);
		wx.request({
			url:"http://192.168.137.1:8081/user/getRoomById",
			data:{
				id:option.id
			},
			success:(response)=>{
				console.log("房间"+response.data)
				this.room = response.data
			}
		})
	},
	methods: {
		
		bindDatestart: function(e) {
		    this.startdate = e.target.value
		},
		bindDateend: function(e) {
		    this.enddate = e.target.value
		},
		getDate(type) {
		    const date = new Date();
		    let year = date.getFullYear();
		    let month = date.getMonth() + 1;
		    let day = date.getDate();
		    if (type === 'start') {
		        year = year;
		    } else if (type === 'end') {
		        year = year + 10;
		    }
		    month = month > 9 ? month : '0' ;
		    day = day > 9 ? day : '0' + day;
		    return `${year}-${month}-${day}`;
		},
		renthouse(){
			if(this.startdate<this.enddate)
			{
				wx.request({
				url:"http://192.168.137.1:8081/sales/checkinRoom",
				data:{
					starttime:this.startdate+' 00:00:00',
					stoptime:this.enddate+' 00:00:00',
					price:this.room.price,
					roomid:this.room.id,
					useraccount:this.useraccount,
					salesmanaccount:uni.getStorageSync('userInfo').account
				},
				success:(response)=>{
					console.log(response.data)
					if(response.data == "OK")
					{
						uni.redirectTo({
							url:'./saler'
						})
					}
					if(response.data == "ERROR1")
					{
						uni.showToast({
						title: '请确认用户名是否存在',
						icon: 'none'
						});
						return;
					}
				}
			})
			}
			else
			{
				uni.showToast({
				title: '请确保租房时间真实有效',
				icon: 'none'
				});
				return;
			}
			
			
			
		},
		onloadimg(id){
			var url="http://192.168.137.1:8081/"+id+".jpg"
			console.log("aaaa:"+url)
			return url
		}
	}
};
</script>

<style>
	.bg-set{
	    position: fixed;
	    width: 100%;
	    height: 40%;
	    top: 0;
	    left: 0;
	    z-index: -1;
	}

</style>