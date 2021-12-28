<template>
	<div>
		<h3>用户注册</h3>
		<el-form ref="user" :model="user" label-width="80px">
			<el-form-item label="用户名">
				<el-input v-model="user.account" style="width: 200px;"></el-input>
			</el-form-item>
			<el-form-item label="密码">
				<el-input v-model="user.password" style="width: 200px;" type="password"></el-input>
			</el-form-item>
			<el-form-item label="手机号">
				<el-input v-model="user.phone" style="width: 200px;"></el-input>
			</el-form-item>
			<el-form-item label="名字">
				<el-input v-model="user.nickname" style="width: 200px;"></el-input>
			</el-form-item>
			<el-form-item>
				<el-upload class="avatar-uploader" :multipe="false" :show-file-list="true" :auto-upload="false"
					:before-upload="beforeUpload" :file-list="fileList" :on-change="handleChange"
					action="uploadAction" list-type="picture" accept="image/jpg,image/jpeg/png,PNG">
					<h2>点击这里上传头像</h2>
				</el-upload>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="onSubmit">立即创建</el-button>
				<el-button>取消</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		props: [],
		data() {
			return {
				fileList:[],
				user: {
					account: '',
					password: '',
					phone: '',
					nickname: ''
				}
			}
		},
		methods: {
			beforeUpload(file){
				console.log("beforeUpload = "+file)
			},
			handleChange(file,flieList){
				this.fileList = flieList;
			},
			onSubmit() {
				let fd = new FormData;
				fd.append("account",this.user.account);
				fd.append("password",this.user.password);
				fd.append("phone",this.user.phone);
				fd.append("nickname",this.user.nickname);
				let _this = this
				let fs = this.fileList;
				if(fs.length>0 && fs[0].raw != null){
					fd.append("file",fs[0].raw)
				}
				this.$postFile("/api/public/addUser",fd).then(function(resp){
					_this.$message({
					    message: resp.data.msg,
					    type: 'success'
					});
				})
			}
		},
		mounted() {

		}
	}
</script>

<style scoped="scoped">
</style>
