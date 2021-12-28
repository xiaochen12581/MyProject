<template>
	<div>
		<h3>全部热点图片显示</h3>
		<el-table :data="hots" border style="width: 100%">
			<el-table-column fixed prop="memo" label="图片描述" width="410">
			</el-table-column>
			<el-table-column fixed prop="hot" label="图片热度" width="200">
			</el-table-column>
			<el-table-column fixed prop="type" label="图片类型" width="200">
			</el-table-column>
			<el-table-column label="图片" width="160">
				<template slot-scope="scope">
					<img style="width: 80px;height: 60px;" :src="`${$imgurl}/public/showImg/${scope.row.picture}`" />
				</template>
			</el-table-column>
			<el-table-column label="操作" width="155">
				<template slot-scope="scope">
					<el-button type="danger" plain @click="del(scope.row.id)" icon="el-icon-delete">删除</el-button>
				</template>
			</el-table-column>
		</el-table>
		<br />
		<el-button type="primary" round @click="previous()">去添加热点图片</el-button>
	</div>
</template>

<script>
	export default{
		name:"HotAll",
		props:[],
		data(){
			return{
				hots : []
			}
		},
		methods:{
			del(id){
				let _this = this
				this.$get("/api/adm/hot/delete/"+id+"").then(function(resp){
					_this.hots = resp.data.data
				})
			},
			previous(){
				this.$router.push({path:'/adm/hot/hot'})
			}
		},
		mounted() {
			let _this = this
			this.$get("/api/adm/hot/findAll").then(function(resp){
				_this.hots = resp.data.data
			})
		}
	}
</script>

<style>
</style>
