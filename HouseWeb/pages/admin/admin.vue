<template>
	<view>
	        <uni-segmented-control :current="current" :values="items" @clickItem="onClickItem" style-type="button" active-color="#4cd964"></uni-segmented-control>
	        <view class="content">
	            <view v-if="current === 0">
	                <div class="user">
	                	<span style="margin-bottom: 30px; margin-top: 10rpx; display: flex; flex-direction: row; ">
	                	 <input
	                	      @keyup.enter.native="searchClick"
	                	      placeholder="通过用户名搜索..."
	                	      size="small"
	                	      style="width: 400px; margin-left: 5%;margin-top: 20px;  border-bottom: 1rpx solid #dcdcdc;"
	                	      v-model="operaterName">
	                	    </input>
	                	    <button style="margin-left: 60rpx;margin-top: 15px;  height: 40px; width: 100px; text-align: center;" type="primary"@click="searchUser(operaterName)">搜索</button>
							<button style="margin-top: 15px;align-self: center;width:100px" type="primary" @click="info(1)">刷新</button>
							<button style=" margin-top: 15px;margin-left: 60px; width: 120px; " type="primary" @click="toaddsales">添加业务员</button>
	                	</span>
	                	<uni-table border stripe emptyText="暂无更多数据" style="width: 90%;margin-left: 5%;">
	                		<uni-tr>
	                			<uni-th align="center" style="width: 16%;">姓名</uni-th>
	                			<uni-th align="center" style="width: 16%;">账号</uni-th>
								<uni-th align="center" style="width: 16%;">手机</uni-th>
	                			<uni-th align="center" style="width: 16%;">操作</uni-th>
	                		</uni-tr>
	                		<uni-tr v-for="(item,index) in users" :key=index class='tr'>
	                		 	<uni-td>{{item.name}}</uni-td>
	                			<uni-td>{{item.account}}</uni-td>
								<uni-td>{{item.phone}}</uni-td>
	                			<uni-td style="display：flex; flex-direction: row;">
									<button style=" margin-left: 60px; width: 100px; " type="primary" @click="del(item.account,current)">删除</button>
								</uni-td>
								
	                		</uni-tr>
	                		</uni-table>		
	                		
	                </div>
	            </view>
	            <view v-if="current === 1">
	                <div class="user">
	                	<span style="margin-bottom: 30px;text-align: center; display: flex;justify-content: center;align-items: center">
	                	 <input
	                	      @keyup.enter.native="searchClick"
	                	      placeholder="通过用户名搜索..."
	                	      size="small"
	                	      style="width: 400px; margin-left: 5%;margin-top: 20px;  border-bottom: 1rpx solid #dcdcdc;"
	                	      v-model="userName">
	                	    </input>
	                	    <button style="margin-left: 60rpx;margin-top: 15px;  height: 40px; width: 100px; text-align: center;" type="primary"@click="searchUser(userName)">搜索</button>
							<button style="margin-top: 15px;align-self: center;width:100px" type="primary" @click="mana()">刷新</button>
						</span>
	                	<uni-table border stripe emptyText="暂无更多数据" style="width: 90%;margin-left: 5%;">
	                		<uni-tr>
								<uni-th align="center" style="width: 16%;">姓名</uni-th>
								<uni-th align="center" style="width: 16%;">账号</uni-th>
								<uni-th align="center" style="width: 16%;">手机</uni-th>
								<uni-th align="center" style="width: 16%;">操作</uni-th>
	                		</uni-tr>
	                		<uni-tr v-for="(item,index) in users" :key=index class='tr'>
	                		 	<uni-td>{{item.name}}</uni-td>
	                			<uni-td>{{item.phone}}</uni-td>
	                			<uni-td>{{item.account}}</uni-td>
	                			<uni-td>
									<button style=" margin-left: 100px; width: 100px; " class="del" size="small" type="primary" @click="del(item.account,current)">删除</button>
									</uni-td>
	                		</uni-tr>			
	                	</uni-table>		
	                	
	                </div>
	            </view>
	            <view v-if="current === 2">
	                <div class="room">
						<span style="margin-bottom: 30px;text-align: center; display: flex;justify-content: center;align-items: center">
						 <input
						      @keyup.enter.native="searchClickroom"
						      placeholder="通过房间名名搜索..."
						      size="small"
						      style="width: 400px; margin-left: 5%;margin-top: 20px;  border-bottom: 1rpx solid #dcdcdc;"
						      v-model="roomName">
						    </input>
						    <button style="margin-left: 60rpx;margin-top: 15px;  height: 40px; width: 100px; text-align: center;" type=primary @click="searchRoom(roomName)">搜索</button>
							<button style="margin-left: 30px;margin-top: 15px;"  size="small" type="primary" @click="toaddroom">添加房屋</button>
						</span>
						<uni-table border stripe emptyText="暂无更多数据" style="width: 95%;margin-left: 2%;">
						    <!-- 表头行 -->
						    <uni-tr>
								<uni-th align="center" style="width: 70px;">图片</uni-th>
								<uni-th align="center" style="width: 50px;">房间编号</uni-th>
						        <uni-th align="center" style="width: 100px;">房间名</uni-th>
						        <uni-th align="center" style="width: 50px;">状态</uni-th>
								<uni-th align="center" style="width: 150px;">用户</uni-th>
						        <uni-th align="center">地址</uni-th>
								<uni-th align="center">面积</uni-th>
								<uni-th align="center">价格 /月</uni-th>
								<uni-th align="center">结构</uni-th>
								<uni-th align="center">房东姓名</uni-th>
								<uni-th align="center">房东联系方式</uni-th>
								<uni-th align="center"style="width: 300px;">操作</uni-th>
						    </uni-tr>
						    <!-- 表格数据行 -->
						    <uni-tr v-for="(item,index) in rooms" >
								<uni-td ><image :src="getimg(item.id)" style="width: 60px; height: 60px;" v-image-preview></image></uni-td>
								<uni-td style="text-align: center;">{{item.id}}</uni-td>
								<uni-td style="text-align: center;">{{item.name}}</uni-td>
								<uni-td style="text-align: center;">{{item.state}}</uni-td>
								<uni-td style="text-align: center;">{{item.user}}</uni-td>
								<uni-td style="text-align: center;">{{item.address}}</uni-td>
								<uni-td style="text-align: center;">{{item.area}}</uni-td>
								<uni-td style="text-align: center;">{{item.price}}</uni-td>
								<uni-td style="text-align: center;">{{item.structure}}</uni-td>
								<uni-td style="text-align: center;">{{item.owner}}</uni-td>
								<uni-td style="text-align: center;">{{item.ownerphone}}</uni-td>
								<uni-td style="display: flex; flex-direction: row; height: 100px;">
									<button style="margin-left: 2px;height: 50px;" class="del"  type="primary" @click="delRoom(item.id)">删除</button>
									<button style="margin-left: 2px;height: 50px;" class="del"  type="primary" @click="openRoomModal(item.id)">修改</button>
									<button style="margin-left: 2px;height: 50px;" class="del"  type="primary" @click="setimg(item.id)">更改图片</button>
								</uni-td> 
								</uni-tr>				
						</uni-table>
					</div>
	            </view>
				
				<view v-if="current === 3">
					<view>
						<echarts :option="option" style="height: 300px;" @click="echartsClick"></echarts>
						<button @click="updateClick">切换数据</button>
					</view>
				</view>
			
			</view>
	
		<view>
			<l-modal ref="RoomModal" modalTitle="房间信息" >
			<template>
			  <view>
			         <view>
			             <form  @reset="formReset">
							 <view style="display: flex;flex-direction: row;">
								<view class="title">房间编号:</view>
								<text style="margin-left: 100px;">{{thisRoom.id}}</text>
							 </view>
							 <view style="display: flex;flex-direction: row; margin-top: 30px;border-bottom: 2rpx solid #f2f5ff;">
							 	<view class="title">房间名:</view>
								<input
								     size="small"
								     style="width: 200px;margin-left: 53px;border-bottom: 2rpx solid #7494a2;"
								     v-model="thisRoom.name">
								</input>
								<view class="title" style="margin-left: 50px;">住户:        </view>
								<input
								     size="small"
								     style="width: 200px;margin-left: 30px;border-bottom: 2rpx solid #7494a2;"
								     v-model="thisRoom.user">
								</input>
							 </view>
							 <view style="display: flex;flex-direction: row; margin-top: 30px;border-bottom: 2rpx solid #f2f5ff;">
							 	<view class="title">地址:         </view>
								<input
								     size="small"
								     style="width: 400px;margin-left: 40px;border-bottom: 2rpx solid #7494a2;"
								     v-model="thisRoom.address">
								   </input>
							 </view>
							 <view style="display: flex;flex-direction: row; margin-top: 30px;border-bottom: 2rpx solid #f2f5ff;">
							 	<view class="title">面积（平）:         </view>
								<input
								     size="small"
								     style="width: 200px;margin-left: 30px;border-bottom: 2rpx solid #7494a2;"
								     v-model="thisRoom.area">
								</input>
								<view class="title" style="margin-left: 50px;">价格（/日）:         </view>
								<input
								        size="small"
								        style="width: 200px;margin-left: 30px;border-bottom: 2rpx solid #7494a2;"
								        v-model="thisRoom.price">
								</input>
							 </view>
							 <view style="display: flex;flex-direction: row; margin-top: 30px;border-bottom: 2rpx solid #f2f5ff;">
							 	<view class="title">房东:       </view>
								<input
								     size="small"
								     style="width: 200px;margin-left: 30px;border-bottom: 2rpx solid #7494a2;"
								     v-model="thisRoom.owner">
								</input>
								<view class="title"style="margin-left: 50px;">房东电话:</view>
								<input
								     size="small"
								     style="width: 200px;margin-left: 30px;border-bottom: 2rpx solid #7494a2;"
								     v-model="thisRoom.ownerphone">
								</input>
							 </view>
							 <view style="display: flex;flex-direction: row; margin-top: 30px;border-bottom: 2rpx solid #f2f5ff;">
							 	<view class="title">房间结构:       </view>
							 	<input
							 	     size="small"
							 	     style="width: 200px;margin-left: 30px;border-bottom: 2rpx solid #7494a2;"
							 	     v-model="thisRoom.structure">
							 	</input>
							 	<view class="title"style="margin-left: 50px;">房间状态:</view>
							 	<text>{{thisRoom.state}}</text>
							 </view>
			                
			                 
			                 <view class="uni-btn-v" style="display: flex;flex-direction: row; margin-top: 30px;">
			                     <button form-type="submit" @click="updateRoom(thisRoom)">提交</button>
			                     <button type="default" form-type="reset">重置</button>
								 <button type="default" @click="closeRoomModal()">关闭</button>
			                 </view>
			             </form>
			         </view>
			     </view>
			</template>
			</l-modal>
		</view>	
		
	</view>
	
</template> 

<script>
	import Echarts from '@/components/echarts/echarts.vue'
	import uniSegmentedControl from '@/components/uni-segmented-control/uni-segmented-control.vue'
	import uniTable from '../../components/uni-table/uni-table.vue'
	import uniTr from  '../../components/uni-tr/uni-tr.vue'
	import uniTh from '../../components/uni-th/uni-th.vue'
	import uniTd from '../../components/uni-td/uni-td.vue'
	import module from '../../components/l-modal/l-modal.vue'
	import uniCollapse from '../../components/uni-collapse/uni-collapse.vue'
	import uniCollapseItem from '../../components/uni-collapse-item/uni-collapse-item.vue'
	export default{
		components: {Echarts,uniCollapse,uniCollapseItem,uniSegmentedControl,uniTable,uniTh,uniTr,uniTd,module},
		    
	data(){	
		return{
			imgPaths:[],
			
			items: ['业务员管理','用户管理','房屋管理','分析管理'],
			current: 0,
			users:[],
			operaterName:'',
			userName:'',
			roomName:'',
			rooms:null,
			thisRoom:{
				id:null,
				name:null,
				state:null,
				user:null,
				address:null,
				area:null,
				price:null,
				structure:null,
				owner:null,
				ownerphone:null
			},
			
			//画图
			option: {},
			option2: {
				notMerge: true, // 自定义变量：true代表不合并数据，比如从折线图变为柱形图则需设置为true；false或不写代表合并
				tooltip: {
					trigger: 'axis',
					positionStatus: true,
					formatterStatus: true, // 自定义变量：是否格式化tooltip，设置为false时下面三项均不起作用
					formatterUnit: '元', // 自定义变量：数值后面的单位
					formatFloat2: true, // 自定义变量：是否格式化为两位小数
					formatThousands: true // 自定义变量：是否添加千分位
				},
				legend: {
					data: ['月度营业额']
				},
				grid: {
					left: '5%',
					right: '8%',
					bottom: '5%',
					containLabel: true,
					
				},
				xAxis:{
						type: 'category',
						name:'年-月',
						axisLabel: {
						      interval: 0,
							  rotate:60
						 },
						data: []		
					},
				yAxis: [
					{
						type: 'value'
					}
				],
				
				series: [
					{
						name: '月度营业额',
						type: 'bar',
						data: null,
						// 自定义变量，以数组形式传递渐变参数
						linearGradient: [0, 0, 0, 1,
			            [
			                {offset: 0, color: '#0bac00'},
			                {offset: 0.7, color: '#0dcb00'},
			                {offset: 1, color: '#0fef00'}
			            ]]
					}
				]
			},
			option3: {
				notMerge: true, // 自定义变量：true代表不合并数据，比如从折线图变为柱形图则需设置为true；false或不写代表合并
					tooltip: {
						trigger: 'axis',
						positionStatus: true,
						formatterStatus: true, // 自定义变量：是否格式化tooltip，设置为false时下面三项均不起作用
						formatterUnit: '元', // 自定义变量：数值后面的单位
						formatFloat2: true, // 自定义变量：是否格式化为两位小数
						formatThousands: true // 自定义变量：是否添加千分位
					},
					legend: {
						data: ['房间出租额']
					},
					grid: {
						left: '5%',
						right: '8%',
						bottom: '5%',
						containLabel: true,
						
					},
					xAxis:{
						type:'category',
						name:'房间号',
						axisLabel: {				
							interval: 0
						},
						data: []
									
						},
					yAxis: [
						{
							type: 'value'
						}
					],
					
					series: [
						{
							name: '房间出租额',
							type: 'bar',
							data: null,
							// 自定义变量，以数组形式传递渐变参数
							linearGradient: [0, 0, 0, 1,
				            [
				                {offset: 0, color: '#0bac00'},
				                {offset: 0.7, color: '#0dcb00'},
				                {offset: 1, color: '#0fef00'}
				            ]]
						}
					]
				},
		}						
	},
	onLoad() {
		this.info(1);
		
		this.getMonthSum();
		this.option = this.option2
	},
	onShow() {
		this.info(1);
	},
methods:{
	getimg(id){
		var url="http://127.0.0.1:8080/"+id+".jpg?time="+(new Date()).getTime()/100;
		console.log(url)
		return url
	},
	setimg(id){
		var me = this;
		var i=0
		var account=null
		uni.chooseImage({
			count: 1, //最多可以选择的图片张数，默认9
			sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
			sourceType: ['album'], //从相册选择;
									//album 从相册选图，camera 使用相机，默认二者都有。
									//如需直接开相机或直接选相册，请只使用一个选项
			success: function (res) {//成功则返回图片的本地文件路径列表 tempFilePaths
				me.imgPaths = res.tempFilePaths;
				uni.uploadFile({
					url: 'http://127.0.0.1:8080/test/uploadImg', 
					filePath: me.imgPaths[0],//要上传文件资源的路径
					name: 'uploadFile',//文件对应的 key , 
										//开发者在服务器端通过这个 key 可以获取到文件二进制内容
					formData: {//HTTP 请求中其他额外的 form data
						roomid: id,
					},
					success: (uploadFileRes) => {
							console.log(uploadFileRes);
						me.RoomInfo()
					}
				});
			}
		});
		this.RoomInfo();
	},
	
	onClickItem(e) {
	            if (this.current !== e.currentIndex) {
	                this.current = e.currentIndex;
					if(this.current==0){
						this.operaterName=null;
						this.info(1);
					}
					if(this.current==1){
						this.userName=null;
						this.info(2);
					}
					if(this.current==2){
						this.RoomInfo();
					}
	            }
	        },
	openRoomModal(id) {	
			uni.request({
				url:'http://127.0.0.1:8080/manage/searchRoomById?id='+id,
				success:(responce)=>{
					console.log(responce)
				this.thisRoom=responce.data
									}
					  })
			// show modal
			this.$refs['RoomModal'].showModal()
	}, 
	closeRoomModal() {
		this.$refs['RoomModal'].isShowModal=false
	},
 
		
	toaddsales(){
		uni.navigateTo({
			url:'./addsales'
		})
	},
	toaddroom(){
		uni.navigateTo({
			url:'./addroom'
		})
	},	
			
	info(type){
	wx.request({
		url:'http://127.0.0.1:8080/manage/getAllUsersByType?type='+type,
		success:(responce)=>{
			console.log(responce)
			this.users=responce.data
			}		
		}) 
	},
	del(account){
		wx.request({
		url:'http://127.0.0.1:8080/manage/delUserByAccount?account='+account,
		success:(responce)=>{
			console.log(responce)
			this.users=null;
			if(this.current==0)
				this.info(1);
			else if(this.current==1)
				this.info(2);
			}
		})
	},		
	searchUser(name){
		if(this.current==0){
			wx.request({
				url:'http://127.0.0.1:8080/manage/searchSalesByName?name='+name,
				success:(responce)=>{
					this.users=null;
					console.log(responce)
					this.users=responce.data
				}
			})
		}
		else if(this.current==1){
			wx.request({
				url:'http://127.0.0.1:8080/manage/searchUsersByName?name='+name,
				success:(responce)=>{
					this.users=null;
					console.log(responce)
					this.users=responce.data
				}
			})
		}
		
	},
	
	searchRoom(roomName){
		wx.request({
			url:'http://127.0.0.1:8080/manage/searchRoomsByName?name='+roomName,
			success:(responce)=>{
				console.log(responce)
				this.rooms=responce.data
			}
		})
	},
	
	delRoom(id)
	{
		wx.request({
			url:'http://127.0.0.1:8080/manage/delRoomById?id='+id,
			success:(responce)=>{
				console.log(responce)
				this.rooms=null
				this.RoomInfo()
				}
			})
		},
	RoomInfo(){
		wx.request({
			url:'http://127.0.0.1:8080/manage/getAllRooms',
			success:(responce)=>{
				console.log(responce)
				this.rooms=responce.data
			}
		})
	},
	updateRoom(){
		wx.request({
			data:{
				id:this.thisRoom.id,
				name:this.thisRoom.name,
				state:this.thisRoom.state,
				user:this.thisRoom.user,
				address:this.thisRoom.address,
				area:this.thisRoom.area,
				price:this.thisRoom.price,
				structure:this.thisRoom.structure,
				owner:this.thisRoom.owner,
				ownerphone:this.thisRoom.ownerphone
			},
			method:'POST',
			url:'http://127.0.0.1:8080/manage/updateRoom',
			success:(responce)=>{
				console.log(responce)
				this.closeRoomModal();
				this.RoomInfo();
			}
		})
	},
	getMonthSum(){
		uni.request({
			url:'http://127.0.0.1:8080/chart/getOrderSumByMonth',
			success:(response)=>{
				this.option2.series[0].data= response.data;
				console.log(response.data);
			}
		})
		uni.request({
			url:'http://127.0.0.1:8080/chart/getOrderDate',
			
			success:(response)=>{
				this.option2.xAxis.data= response.data;
			}
		})
	},
	getRoomSum(){
		uni.request({
			url:'http://127.0.0.1:8080/chart/getOrderSumByRoomId',
			
			success:(response)=>{
				this.option3.series[0].data= response.data;
				console.log(response.data);
			}
		})
		uni.request({
			url:'http://127.0.0.1:8080/chart/getOrderRoomId',
			
			success:(response)=>{
				this.option3.xAxis.data= response.data;
			}
		})
	},
	echartsClick(params) {
		console.log('点击数据', params)
	},
	/**
	 * 切换数据
	 */
	updateClick() {
		if (this.option === this.option2) {
			this.getRoomSum();
			this.option = this.option3
		} else {
			this.getMonthSum();
			this.option = this.option2
		}
	}
	
}
	}
</script>

<style>
	.table{
		margin: 10px auto 40px auto;
	    text-align: center;
	    color: #505458;
		
	}
	.th{
		width:auto;
		margin-left: 30px;
	}
</style>
