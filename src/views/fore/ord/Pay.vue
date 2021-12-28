<template>
	<div>
		<h3 v-text="h3te"></h3>
		<hr />
		<div style="display: flex;">
			<el-button icon="el-icon-edit" plain>订单号</el-button>
			<el-input
			  placeholder="请输入内容"
			  v-model="ordid"
			  :disabled="true">
			</el-input>
			<br />
			<el-button icon="el-icon-edit" type="success">收货地址</el-button>
			<el-input v-model="ord.addr"></el-input>
			<br />
			<el-button icon="el-icon-edit" type="success">收货电话</el-button>
			<el-input v-model="ord.phone"></el-input>
		</div>
		<div style="display: flex;" v-show="but1">
			  <el-button type="danger" @click="payment()" plain>去付款</el-button>
		</div>
		<hr />
		<div style="display: flex;" v-show="but2">
			   <el-button type="success">付款完成</el-button>
		</div>
		<hr />
		<el-table :data="$store.state.creats" border style="width: 100%">
			<el-table-column label="图片" width="280" align="center">
				<template slot-scope="scope">
					<img style="width: 100px;height: 60px;"
						:src="`${$imgurl}/public/showImg/${scope.row.picture}`" />
				</template>
			</el-table-column>
			<el-table-column fixed prop="name" label="商品名称" width="480" align="center">
			</el-table-column>
			<el-table-column fixed prop="price" label="商品价格" width="200" align="center">
			</el-table-column>
			<el-table-column fixed prop="num" label="数量" width="250" align="center">
			</el-table-column>
			<el-table-column label="商品总价">
				<template slot-scope="scope">
					<div>{{scope.row.price*scope.row.num}}</div>
				</template>
			</el-table-column>
		</el-table>
	</div>
</template>

<script>
	export default{
		name:'pay',
		props:[],
		data(){
			return{
				but1:true,
				but2:false,
				ord:[],
				h3te:"付款中.................",
				ordid:""
			}
		},
		methods:{
			payment(){
				var Order = this.ord;
				let _this = this;
				Order.ispay = 1;
				this.$postJson("/api/fore/cart/UpdateIspay",Order).then(function(resp){
					_this.$message({
						message: resp.data.data,
						type: 'success'
					});
					_this.$router.push("/fore/cart/cart");
				})
			}
		},mounted() {
			this.ordid = this.$route.query.ordid
			let _this = this
			this.$get("/api/fore/cart/findOrdByOid/"+this.ordid+"").then(function(resp){
				_this.ord = resp.data.data
				console.log(resp.data.data)
			})
		}
	}
</script>

<style scoped="scoped">
</style>
