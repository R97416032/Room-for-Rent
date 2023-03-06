<template>
	<view >
		<!-- 自定义导航位置 -->
		<!-- 页面需要的自定义导航 -->
		<!-- 页面 -->
		<house v-if="index === 0"/>
		<order v-if="index === 1"/>
		<mine v-if="index === 2"/>
		<!-- 底部导航 -->
		<foo-bar @toIndex="toIndex"></foo-bar>
	</view>
</template>

<script>
	import house from './house.vue'
	import order from './order.vue'
	import mine from './mine.vue'
	import fooBar from '../../components/fooBar/fooBar.vue'
	export default {
		components:{
			house,
			order,
			mine,
			fooBar
		},
		data() {
			return {
				index: 0,
				title:'首页',
				skeletonOff:false,
			}
		},
		methods: {
			toIndex(index){
				this.index = index
			},
		}
	}
</script>

<style>

</style>
