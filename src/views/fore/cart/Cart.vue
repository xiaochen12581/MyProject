<template>
	<div>
		<h3 style="color: red;">-- 我的购物车 --</h3>
		<el-table :data="bookcart" border style="width: 100%">
			<el-table-column fixed prop="number" label="订单编号" width="80">
			</el-table-column>
			<el-table-column fixed prop="name" label="书本名称" width="280">
			</el-table-column>
			<el-table-column fixed prop="price" label="书本价格" width="180">
			</el-table-column>
			<el-table-column label="图片" width="140">
				<template slot-scope="scope">
					<img style="width: 60px;height: 60px;"
						:src="`${$imgurl}/public/showImg/${scope.row.picture}`" />
				</template>
			</el-table-column>
			<el-table-column fixed prop="tcreate" label="订单创建的日期" width="280">
			</el-table-column>
			<el-table-column label="商品数量" width="280" align="center">
				<template slot-scope="scope">
					<el-input-number v-model="scope.row.num" :min="1" :max="10" label="描述文字"></el-input-number>
				</template>
			</el-table-column>
			<el-table-column label="商品总价">
				<template slot-scope="scope">
					<div>{{scope.row.price*scope.row.num}}</div>
				</template>
			</el-table-column>
			<el-table-column label="复选框" width="190">
				<template slot-scope="scope">
					<el-checkbox v-model="scope.row.checked" label="购买" border></el-checkbox>
				</template>
			</el-table-column>
			<el-table-column fixed prop="scope" label="操作" width="280">
				<template slot-scope="scope">
					<el-button type="danger" icon="el-icon-delete" plain @click="del(scope.row.id)">删除商品</el-button>
				</template>
			</el-table-column>
		</el-table>
		<p>计算总价：￥{{getTotal}}</p>
		<hr />
		<el-button type="primary" @click="sub()" plain>提交订单</el-button>
	</div>


</template>

<script>
	export default {
		name: "cart",
		props: [],
		data() {
			return {
				total: 0,
				bookcart: []
			}
		},
		methods: {
			sub() {
				let carts = [];
				for (var i = 0; i < this.bookcart.length; i++) {
					if (this.bookcart[i].checked == true) {
						var time = new Date().getFullYear() +
							"-" +
							(new Date().getMonth() + 1) +
							"-" +
							new Date().getDate();
							this.bookcart[i].tcreate = time;
							this.bookcart[i].amount = this.total;
						carts.push(this.bookcart[i])
					}
				}
				if (carts[0] == null) {
					this.$message.error("请选择好物品后再下单");
				} else {
					this.$store.state.creats = carts;
					console.log(this.$store.state.creats)
					this.$router.push("/fore/ord/make");
				}

			},
			del(id) {
				let _this = this;
				let fd = new FormData;
				fd.append("id", id);
				this.$postJson("/api/fore/ord/delById", fd).then(function(resp) {
					console.log(resp.data.data)
					_this.bookcart = resp.data.data
					_this.$message({
						message: resp.data.msg,
						type: 'success'
					});
				})
			}
		},
		mounted() {
			let _this = this;
			this.$get("/api/fore/ord/bookCartById").then(function(resp) {
				let aa = resp.data.data;
				for(let i=0; i<aa.length; i++) {
					let c = {}
					c.name = aa[i].book.name;
					c.picture = aa[i].book.picture;
					c.bookid  = aa[i].book.bookid;
					c.number = i +1;
					c.id = aa[i].id;
					c.num = aa[i].num;
					c.tcreate = aa[i].tcreate;
					c.price = aa[i].book.price;
					_this.bookcart.push(c)
				}
			})
		},
		computed: {
			getTotal: function() {
				var a = 0;
				for (let i = 0; i < this.bookcart.length; i++) {
					if (this.bookcart[i].checked == true) {
						a += this.bookcart[i].price * this.bookcart[i].num
					}
				}
				this.total = a;
				return this.total
			}
		}
	}
</script>

<style scoped="scoped">
</style>
