<template>
	<div>
		<div
			style="background-color:#545c64;display: flex; height: 60px;display: flex;">
			<el-menu  class="el-menu-demo" mode="horizontal" background-color="#545c64"
				text-color="#fff" active-text-color="#ffd04b" style="font-weight: 950">
				<el-menu-item index="1">
					<i class="el-icon-s-home"></i>
					<router-link to="/fore/home/home">主页</router-link>
				</el-menu-item>
				<el-menu-item index="2">
					<i class="el-icon-s-shop"></i>
					<router-link to="/fore/goods/goods">商品详情</router-link>
				</el-menu-item>
				<el-menu-item index="3">
					<i class="el-icon-shopping-cart-full"></i>
					<router-link to="/fore/cart/cart">购物车</router-link>
				</el-menu-item>
				<el-menu-item index="9">
					<i class="el-icon-notebook-2"></i>
					<router-link to="/fore/ord/info">我的订单</router-link>
				</el-menu-item>
				<el-menu-item index="5">
					<el-popover placement="right"  trigger="hover">
						<span>
							<h4>用户名:</h4>
							<el-input v-model="user.username" placeholder="请输入用户名"></el-input>
							<h4>密码:</h4>
							<el-input type="password" v-model="user.password" placeholder="请输入密码"></el-input>
							<h4></h4>
							<el-button type="success" plain @click="submit()">登录</el-button>
						</span>
						<el-button slot="reference" style="height: 58px;"><el-avatar style="width: 40px;height: 40px;" :src="circleUrl"></el-avatar></el-button>
					</el-popover>
				</el-menu-item>
				<el-menu-item>
					<h3 style="color: white;">{{$store.state.username}}</h3>
				</el-menu-item>
				<el-menu-item>
					<el-button @ type="danger" icon="el-icon-switch-button" plain @click="exit()">退出登录</el-button>
				</el-menu-item>
				<el-menu-item index="8">
					<i class="el-icon-s-custom"></i>
					<router-link to="/fore/user/userAdd">用户注册</router-link>
				</el-menu-item>
			</el-menu>

		</div>
		<div>
			<router-view></router-view>
		</div>
	</div>
</template>

<script>
	export default {
		name: "Adm",
		props: [],
		data() {
			return {
				user:{
					grant_type:"password",
					client_id:"sobook",
					client_secret:"223344",
					username:"",
					password:""
				},
				circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
			}
		},
		methods: {
			submit() {
				let _this = this
				let s = this.$qs.stringify(this.user)
				this.$postJson("/oauth/token",s).then(function(resp){
					localStorage.setItem("token","");
					localStorage.setItem("token",resp.data.access_token)
				_this.$get("/api/info/getUser").then(function(resp){
					let url = "http://127.0.0.1:8000/api/public/showImg/"
					_this.$store.state.pic = resp.data.img
					_this.circleUrl = url + _this.$store.state.pic
					if(resp.data.role == "G"){
						_this.$message({
						    message: '登录成功，欢迎卖家-'+resp.data.account,
						    type: 'success'
						});
						_this.$store.state.username = "欢迎，卖家-"+resp.data.account
						_this.$router.push("/adm/book/book");
					}
					if(resp.data.role == "P"){
						_this.$message({
						    message: '登录成功，欢迎买家-'+resp.data.account,
						    type: 'success'
						});
						_this.$store.state.username = "欢迎，买家-"+resp.data.account
					}
					if(resp.data.role == null){
						_this.$message.error("账号或密码错误，请重新登录0.0");
						_this.user.password = "";
					}
				})
				})
				
			},
			exit(){
				localStorage.setItem("token","");
				this.user.username = "";
				this.user.password = "";
				this.$store.state.username = "未登录";
				this.circleUrl = "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png";
				this.$message({
				    message: '已退出登录',
				    type: 'success',
				});
				this.$router.push("/fore/home/home");
			}
		},
		mounted() {

		}
	}
</script>

<style scoped="scoped">
	a {
		text-decoration: none;
	}
</style>
