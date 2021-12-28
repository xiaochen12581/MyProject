<template>
	<div align="center">
		<input class="in2" size="60" style="height: 20px;" v-model="key" />
		<el-button type="success" plain @click="findIf(key)" icon="el-icon-check">查询</el-button>
		<hr style="border: solid red;" />
		<div style="display: flex;">
			<div style="width: 25%; border: 1px solid blue;display: flex;background-color: skyblue;">
				<el-col :span="4">
					<el-menu default-active="2" class="el-menu-vertical-demo" background-color="skyblue"
						text-color="#fff" active-text-color="#46ff7a">
						<el-menu-item index="1">
							<i class="el-icon-menu"></i>
							<a @click="all()" style="font-size: 30px;height: 50px;">全部商品类型</a>
						</el-menu-item>
						<div v-for="(type,idx) in Types">
						<el-menu-item index=idx>
							<i class="el-icon-menu"></i>
							<a @click="findType(type.id)">{{type.type}}</a>
			 		</el-menu-item>
						</div>
					</el-menu>
				</el-col>
			</div>
			<div style="width: 70%; display: flex; border: 1px solid red;flex-wrap: wrap; background-color: skyblue;">
				<div v-for="(book,idx) in books" style="width: 50%; text-align: center;" id="abc">
					<img :src="`${$imgurl}/public/showImg/${book.picture}`" style="width: 150px;height: 80px;" />
					<br />
					<span v-html="book.name"></span>
					<br />
					<span v-html="book.author"></span>
					<br />
					<span v-html="book.publisher"></span>
					<br />
					￥{{book.price}}
					<button class="bt" @click="buy(book.bookType.id)">购买</button>
					<br />
				</div>
			</div>
		</div>
		<br />
		<el-pagination background layout="prev, pager, next" :page-size="num" @current-change="jump" :total="total">
		</el-pagination>


	</div>

</template>

<script>
	export default {
		name: "",
		props: [],
		data() {
			return {
				key: "",
				Types: [],
				books: [],
				total: 0,
				start: 1,
				num: 8
			}
		},
		methods: {
			buy(bookid){
				console.log(bookid)
				let fd = new FormData;
				fd.append("bookid",bookid);
				let _this = this;
				this.$postJson("/api/fore/ord/addCart",fd).then(function(resp){
					_this.$message({
					    message: resp.data.msg,
					    type: 'success'
					});
				})
			},
			findIf(key) {
				let _this = this
				let a = "key=" + key
				this.$postJson("/api/public/book/findIf/0/8",
					a).then(function(resp) {
					_this.books = resp.data.data.books
					_this.total = resp.data.data.total
				})
			},
			findType(id) {
				let _this = this
				this.$get("/api/public/findByPage/0/8/" + id + "").then(function(resp) {
					_this.books = resp.data.data.books
					_this.total = resp.data.data.total
				})
			},
			all() {
				let _this = this
				this.$get("/api/public/findAll/1/8").then(function(resp) {
					_this.books = resp.data.data.books
				})
			},
			jump(pag) {
				this.start = pag;
				let _this = this
				this.$get("/api/public/findAll/" + _this.start + "/" + _this.num + "").then(function(resp) {
					_this.books = resp.data.data.books
					_this.total = resp.data.data.total
				})
			}
		},
		mounted() {
			let _this = this
			this.$get("/api/public/findType").then(function(resp) {
				_this.Types = resp.data.data
			})
			this.$get("/api/public/findAll/" + _this.start + "/" + _this.num + "").then(function(resp) {
				_this.books = resp.data.data.books
				_this.total = resp.data.data.total
			})
			let a = "key=" + this.$route.query.key
			this.key = this.$route.query.key
			this.$postJson("/api/public/book/findIf/0/8",
				a).then(function(resp) {
				_this.books = resp.data.data.books
				_this.total = resp.data.data.total
			})
		}

	}
</script>

<style scoped="scoped">
	a {
		color: black;
		font-size: 30px;
		text-decoration: none;
		display: inline-block;
		cursor: pointer;
	}

	li:hover {
		background-color: #ccc;
	}

	li {
		width: 390px;
	}

	#abc:hover {
		background-color: #CCCCCC;
	}

	.c1 {
		background-color: aqua;
	}

	.in2 {
		outline-style: none;
		border: 1px solid #ccc;
		border-radius: 3px;
		padding: 6px 0px;
		width: 400px;
		font-size: 20px;
	}

	.in2:focus {
		border-color: #66afe9;
		outline: 0;
		-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6);
		box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6)
	}
	.bt{
		border-radius: 8px;
		font-size: 16px;
		-webkit-transition-duration: 0.4s;
		transition-duration: 0.4s;
	}
	.bt:hover{
		background-color: #4CAF50;
		color: white;
		box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
	}
</style>
