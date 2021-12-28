<template>
	<div>
		<h2>用户管理</h2>
		<el-table :data="users" border style="width: 100%">
			<el-table-column fixed prop="id" label="用户id" width="80">
			</el-table-column>
			<el-table-column fixed prop="account" label="用户名" width="180">
			</el-table-column>
			<el-table-column fixed prop="role" label="用户身份" width="180">
			</el-table-column>
			<el-table-column fixed prop="phone" label="手机号" width="180">
			</el-table-column>
			<el-table-column label="图片" width="120">
				<template slot-scope="scope">
					<img style="width: 80px;height: 80px;" :src="`${$imgurl}/public/showImg/${scope.row.img}`" />
				</template>
			</el-table-column>
			<el-table-column label="操作" width="180">
				<template slot-scope="scope">
					<el-button type="danger"icon="el-icon-delete-solid" plain @click="del(scope.row.id)">删除用户</el-button>
				</template>
			</el-table-column>
		</el-table>
			
	</div>
</template>

<script>
	export default{
		props:[],
		data(){
			return{
				users:[]
			}
		},
		methods:{
			del(id){
				let _this = this
				this.$get("/api/adm/user/delById/"+id+"").then(function(resp){
					if(resp.data.data!=null){
						_this.users = resp.data.data
						_this.$message({
						    message: resp.data.msg,
						    type: 'success'
						});
					}else{
						_this.$message.error(resp.data.msg);
					}
					
				})
			}
		},
		mounted() {
			let _this = this
			this.$get("/api/adm/user/AllUser").then(function(resp){
				console.log(resp.data.data)
				_this.users = resp.data.data
			})
		}
	}
</script>

<style scoped="scoped">
</style>
