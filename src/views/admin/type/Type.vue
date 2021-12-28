<template>
	<div>
		<h2>类型管理</h2>
		<div style="display:flex;justify-content:space-between;">
			<el-table :data="types" border style="width: 20%">
				<el-table-column fixed prop="id" label="类型id" width="200">
				</el-table-column>
				<el-table-column fixed prop="type" label="类型" width="220">
				</el-table-column>
				<el-table-column label="操作" width="220">
					<template slot-scope="scope">
						<el-button type="primary" icon="el-icon-edit" plain @click="update(scope.row.id,scope.row.type)">修改</el-button>
					</template>
				</el-table-column>
			</el-table>
				
		<div style="width:60vm border-left: 1px;">
			<el-button  type="success" plain @click="add()">
				添加
			</el-button>
			<h3 v-text="h3text"></h3>
			<el-input type="text" v-model:value="booktype.id"  />
			<el-input type="text" v-model:value="booktype.type" />
			<br />
			<br />
			<el-button type="success" plain @click="submit()">提交</el-button>
		</div>
		</div>
		</div>
		
		
	</div>
</template>

<script>
	export default{
		props : [],
		data (){
			return {
				h3text:"更新",
				vlu:"添加",
				booktype:{id:0,type:""},
				resp : "",
				types : []
				}
				
			},
			methods : {
				update(id,type){
					this.h3text = "正在更新中........"
					this.booktype.id = id
					this.booktype.type = type
				},
				add(){
					this.h3text = "正在添加中。。。。。。"
					this.id = 0
				},
				submit(){
					var bookType = this.booktype
					let _this = this
					_this.$postJson("/api/adm/type/add",bookType).then(function(resp){
						_this.$message({
							message:resp.data.msg,
							type:"success"
						})
						_this.types = resp.data.data
					})
				}


			},
			mounted(){
				let _this = this
				this.$get("/api/adm/type/findAll")
				.then(function(resp){
					_this.types = resp.data.data
				})
			}
		}
	
</script>

<style scoped="scoped">
</style>
