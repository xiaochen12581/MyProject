<template>
	<div>
		<h3>待确认订单详情</h3>
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
		<br />
		<p>总价格为：￥{{$store.state.creats[0].amount}}</p>
		<hr />
		<div style="display: flex;">
					<el-button type="primary"  >
								请输入收货电话
					</el-button>   
					<el-input  placeholder="请输入收货电话" v-model="ord.phone">
					</el-input>
		</div>
		<div style="display: flex;">
					<el-button type="primary"  >
								请输入收货地址
					</el-button>   
					<el-input  placeholder="请输入收货地址" v-model="ord.addr">
					</el-input>
		</div>
		<hr />
		<div v-show="sua">
		<el-button type="info" @click="reselect()" plain>重新选择</el-button>
		<el-button type="success" @click="sub()"  plain>提交订单</el-button>
		</div>
		<hr />
		<div v-show="subb">
			<el-button type="success" plain>订单下单成功,单号:{{ordid}}</el-button>
		<el-button type="success" @click="payment(ordid)"  plain>去付款</el-button>
		</div>
		
		
		
	</div>
</template>

<script>
	export default{
		name:"make",
		props:[],
		data(){
			return{
				ord:[],
				sua:true,
				subb:false,
				ordid:1
			}
		},
		methods:{
			reselect(){
				this.$store.state.creats = "";
				this.$router.push("/fore/cart/cart");
			},
			sub(){
				let carts = this.$store.state.creats;
				for(var i = 0;i<carts.length;i++){
					this.$get("/api/fore/ord/del/"+carts[i].id).then(function(resp){
						console.log(resp.data.msg);
					})
				}
				for(var i=0;i<carts.length;i++){
					carts[i].addr = this.ord.addr;
					carts[i].phone = this.ord.phone;
					console.log(carts[i])
				}
				let _this = this;
				this.$postJson("/api/fore/cart/addCart2Order",carts).then(function(resp){
					_this.$message({
						message: resp.data.msg+",订单号为:"+resp.data.data,
						type: 'success'
					});
					_this.ordid = resp.data.data;
					_this.sua = false;
					_this.subb = true;
				})
			},
			payment(ordid){
				this.$router.push({
					path:'/fore/ord/pay',
					query:{
						ordid
					}
				})
			}
			
		},mounted() {
			
			
		}
	}
</script>

<style scoped="scoped">
</style>
