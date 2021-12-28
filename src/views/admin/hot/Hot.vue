<template>
	<div>
		<h2>热点管理:热点图片添加</h2>
		<div>
			<table>
					<tr><td>描述:</td>
						<td><input type="text" v-model="obj.memo"></td>
					</tr>
					<tr><td>热度:</td>
						<td><input type="text" v-model="obj.hot"></td>
					</tr>
					<tr><td>类型:</td>
						<td><input type="text" v-model="obj.type"></td>
					</tr>
					<tr><td>图片:</td>
					<td><input type="file" ref="pic"></td>
					</tr>
			</table>
			<hr />
			<el-button type="primary" plain @click="upload">提交</el-button>
			<el-button type="success" @click="previous()">返回</el-button>
		</div>
		
	</div>
</template>

<script>
	export default {
		name : "Hot",
		props : [],
		data (){
			return {
				obj : {}
			}
		},
		methods : {
			previous(){
				this.$router.push({path:'/adm/hot/hotAll'})
			},
			upload(){
				let fd = new FormData;
				fd.append("memo",this.obj.memo)
				fd.append("hot",this.obj.hot)
				fd.append("type",this.obj.type)
				let fs = this.$refs.pic.files;
				if(fs.length >0 && fs[0] != null){
					//有文件上传
					fd.append("file",fs[0])
				}
				this.$postFile("/api/adm/hot/addHot",
				fd).then(function(resp){
					alert(resp.data.msg);
				})
			}
		},
		mounted()  {
			
		}
	}
</script>

<style scoped="scoped">
	table tr {font-size: 20px;}
	input{
		outline-style: none;
		border: 1px solid #ccc;
		border-radius: 3px;
		padding: 6px 0px;
		width: 400px;
		font-size: 20px;
	}
	input:focus{
		border-color: #66afe9;
		outline: 0;
		-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6);
		box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6)
	}
</style>
