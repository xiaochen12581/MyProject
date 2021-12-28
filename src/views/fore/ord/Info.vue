<template>
	<div>
		<h3>我的订单详情</h3>
		<hr />
		<el-table :data="ords" border style="width: 100%">
			<el-table-column fixed prop="ordid" label="订单号" width="100">
			</el-table-column>
			<el-table-column fixed prop="addr" label="收货地址" width="550">
			</el-table-column>
			<el-table-column fixed prop="phone" label="电话" width="180">
			</el-table-column>
			<el-table-column fixed prop="shipno" label="发货单号" width="180">
			</el-table-column>
			<el-table-column fixed prop="amount" label="总价" width="160">
			</el-table-column>
			<el-table-column fixed prop="tcreate" label="下单时间" width="190">
			</el-table-column>
			<el-table-column fixed prop="ispay" label="是否付款" width="240">
			</el-table-column>
			<el-table-column fixed prop="scope" label="操作" width="302">
				<template slot-scope="scope">
					<el-popover placement="right"  trigger="click">
						<p>{{scope.row.ordid}}号订单发往：{{scope.row.addr}}</p>
						<el-table :data="itemBook" border style="width: 100%;">
							<el-table-column label="图片" width="180" align="center">
								<template slot-scope="scope">
									<img style="width: 60px;height: 60px;"
										:src="`${$imgurl}/public/showImg/${scope.row.picture}`" />
								</template>
							</el-table-column>
							<el-table-column fixed prop="name" label="商品名称" width="150">
							</el-table-column>
							<el-table-column fixed prop="price" label="商品价钱" width="100">
							</el-table-column>
							<el-table-column fixed prop="num" label="商品数量" width="100">
							</el-table-column>
							<el-table-column label="小计" width="100">
								<template slot-scope="scope">
									<div>{{scope.row.num*scope.row.price}}</div>
								</template>
							</el-table-column>
						</el-table>
						<el-button slot="reference" type="primary" icon="el-icon-star-off" plain @click="detailed(scope.row.ordid)">明细</el-button>
					</el-popover>
					
				</template>
			</el-table-column>
		</el-table>
			
	</div>
</template>

<script>
	export default{
		name:'info',
		props:[],
		data(){
			return{
				ords:[],
				itemBook:[]
			}
		},
		methods:{
			detailed(ordid){
				let _this = this
				this.$get("/api/fore/cart/findItemBook/"+ordid+"").then(function(resp){
					_this.itemBook = resp.data.data
					console.log(resp);
				})
			}
		},
		mounted() {
			let _this = this;
			this.$get("/api/fore/cart/findAllOrd").then(function(resp){
				console.log(resp.data.data);
				_this.ords = resp.data.data;
				for(var i=0;i<_this.ords.length;i++){
					if(_this.ords[i].ispay == 0){
						_this.ords[i].ispay = "未付款";
					}
					if(_this.ords[i].ispay == 1){
						_this.ords[i].ispay = "已付款";
					}
				}
			})
		}
	}
</script>

<style scoped="scoped">
</style>
