import Vue from "vue"
import VueRouter from "vue-router"

Vue.use(VueRouter)

import App from "@/App.vue"
	
	import Fore from '@/views/fore/Fore.vue'
		import Home from "@/views/fore/home/Home.vue"
		import Goods from "@/views/fore/goods/Goods.vue"
		import Go from "@/views/fore/cart/Cart.vue"
		import Info from "@/views/fore/ord/Info.vue"
			import Make from "@/views/fore/ord/Make.vue"
			import Pay from "@/views/fore/ord/Pay.vue"
		import UserAdd from "@/views/fore/user/userAdd.vue"

	import Adm  from '@/views/admin/Admin.vue'
		import Type from '@/views/admin/type/Type.vue'
		import Book from "@/views/admin/book/Book.vue"
			import BookUp from "@/views/admin/book/BookUp.vue"
		import Hot  from "@/views/admin/hot/Hot.vue"
			import HotAll from "@/views/admin/hot/HotAll.vue"
		import User from "@/views/admin/user/User.vue"
		import Ord from "@/views/admin/ord/Ord.vue"

const routes = [
		{
			path : "/",
			redirect : "/home",
			component : App,
		},
	{
	path : "/adm"  ,
	component : Adm,
	children:[
		{path : "type/type" , component : Type},
		{path : "book/book" , component : Book},
		{path : "book/bookUp" , component : BookUp},
		{path : "hot/hot"  , component : Hot},
		{path : "hot/hotAll"  , component : HotAll},
		{path : "user/userAll"  , component : User},
		{path : "ord/ord"  , component : Ord}
		]},
	{
	path : "/fore" , component : Fore,
	children : [
		{path : "/home", component : Home},
		{path : "home/home", component : Home},
		{path : "goods/goods", component : Goods},
		{path : "ord/info", component : Info},
		{path : "cart/cart", component : Go},
		{path : "ord/make", component : Make},
		{path : "ord/pay", component : Pay},
		{path : "user/userAdd", component : UserAdd}
	]},
]

const router = new VueRouter({
	routes
})

export default router