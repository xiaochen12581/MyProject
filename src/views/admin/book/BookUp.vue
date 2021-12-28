<template>
	<div>
		<h3 v-text="h3Text"></h3>
		<div>
			<table>
				<tr>
					<td>商品名称:</td>
					<td><input type="text" v-model="book.name"></td>
				</tr>
				<tr>
					<td>商品序号:</td>
					<td><input type="text" v-model="book.sn"></td>
				</tr>
				<tr>
					<td>商品价格:</td>
					<td><input type="text" v-model="book.price"></td>
				</tr>
				<tr>
					<td>商品作者:</td>
					<td><input type="text" v-model="book.author"></td>
				</tr>
				<tr>
					<td>商品出处:</td>
					<td><input type="text" v-model="book.publisher"></td>
				</tr>
				<tr>
					<td>出厂时间:</td>
					<td><input type="date" v-model="book.tpublish"></td>
				</tr>
				<tr>
					<td>商品类型:</td>
					<td>
						<select v-model="typeid">
							<option v-for="option in options" v-bind:value="option.id">
								{{option.type}}
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><img style="width: 60px;height: 60px;" :src="`${$imgurl}/public/showImg/${book.picture}`" /></td>
					<td>
						<el-upload class="avatar-uploader" :multipe="false" :show-file-list="true" :auto-upload="false"
							:before-upload="beforeUpload" :file-list="fileList" :on-change="handleChange"
							action="uploadAction" list-type="picture" accept="image/jpg,image/jpeg/png,PNG">
							<h2>点击这里更新图片</h2>
						</el-upload>
					</td>
				</tr>
			</table>
			<hr />
			<el-button type="primary" plain @click="upload">提交</el-button>
			<el-button type="info" @click="add()">添加</el-button>
			<el-button type="success" @click="previous()">返回</el-button>
		</div>
	</div>
</template>

<script>
	export default {
		name: "BookUp",
		props: [],
		data() {
			return {
				h3Text: "更新中.............",
				book: {},
				typeid: 0,
				options: [],
				fileList:[]
			}
		},
		methods: {
			beforeUpload(file){
				console.log("beforeUpload = "+file)
			},
			handleChange(file,flieList){
				this.fileList = flieList;
			},
			previous() {
				this.$router.push({
					path: '/adm/book/book'
				})
			},
			add() {
				this.h3Text = "添加中..................."
				this.book.bookid = 0
			},
			upload() {
				let fd = new FormData;
				fd.append("bookid", this.book.bookid)
				fd.append("name", this.book.name)
				fd.append("sn", this.book.sn)
				fd.append("price", this.book.price)
				fd.append("author", this.book.author)
				fd.append("publisher", this.book.publisher)
				fd.append("tpublish", this.book.tpublish)
				fd.append("typeid", this.typeid)
				let _this = this
				let fs = this.fileList;
				if (fs.length > 0 && fs[0].raw != null) {
					//有文件上传
					fd.append("file", fs[0].raw)
				}
				this.$postFile("/api/adm/book/addBook",
					fd).then(function(resp) {
					_this.$message({
							message:resp.data.msg,
							type:"success"
					});	
					_this.book = resp.data.data
				})
			}
		},
		mounted() {
			var bookid = this.$route.query.bookid
			let _this = this
			this.$get("/api/adm/book/findById/" + bookid + "")
				.then(function(resp) {
					_this.book = resp.data.data
				})
			this.$get("/api/adm/type/findAll")
				.then(function(resp) {
					_this.options = resp.data.data
				})
		}
	}
</script>

<style scoped="scoped">
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
