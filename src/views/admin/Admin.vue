<template>
	<div>
		<div style="background-color: #545c64; height: 50px; display: inline-block;position: fixed;top: 0;width: 100%;">
			<el-menu  class="el-menu-demo" mode="horizontal" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
				<el-menu-item index="1">
					<router-link to="/fore/home/home">返回首页</router-link>
				</el-menu-item>
			</el-menu>
		</div>
		<div style="display: flex;width: 100%;margin-top: 60px;">
			<el-menu default-active="2" class="el-menu-vertical-demo"  background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" style="width: 25%;">
				   <el-menu-item index="1">
				          <i class="el-icon-document"></i>
				          <router-link to="/adm/type/type" style="color: white;">类型管理</router-link>
				    </el-menu-item>
					<el-submenu index="2">
					        <template slot="title">
					          <i class="el-icon-location"></i>
					          <span>热点管理</span>
					        </template>
					        <el-menu-item-group>
					          <el-menu-item index="2-1"><router-link to="/adm/hot/hot" style="color: white;">热点管理</router-link></el-menu-item>
					        </el-menu-item-group>
							<el-menu-item-group>
							  <el-menu-item index="2-2"><router-link to="/adm/hot/hot"><router-link to="/adm/hot/hotAll" style="color: white;">热点图片显示</router-link></router-link></el-menu-item>
							</el-menu-item-group>
					</el-submenu>
					<el-menu-item index="3">
					       <i class="el-icon-collection"></i>
					       <router-link to="/adm/book/book" style="color: white;">书本管理</router-link>
					 </el-menu-item>
					 <el-submenu index="4">
					         <template slot="title">
					           <i class="el-icon-user-solid"></i>
					           <span>用户管理</span>
					         </template>
					         <el-menu-item-group>
					           <el-menu-item index="4-1"><router-link to="/adm/user/userAll" style="color: white;">用户管理</router-link></el-menu-item>
					         </el-menu-item-group>
					 </el-submenu>
					 <el-menu-item index="5">
					        <i class="el-icon-s-order"></i>
					        <router-link to="/adm/ord/ord" style="color: white;">查看所有订单明细</router-link>
					  </el-menu-item>
			</el-menu>
			<div style="width: 3%;"></div>
			<div  style="width: 73%; height: 70vw;background-color: skyblue;">
				<router-view></router-view>
			</div>
		</div>
	</div>
</template>

<script>
	export default{
		name  : "Adm",
		props : [] ,
		data()  {
			return {
				
			}
		},
		methods : {
			
		},
		mounted(){
			
		}
	}
</script>

<style scoped="scoped">
	a{text-decoration:none;}
	li{color: white;}
</style>
