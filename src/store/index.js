import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

const store = new Vuex.Store({
	state:{
		username:"未登录",
		pic:"",
		carts:[]
	},
	mutations:{
		setUsername(state,username){
			state.username = username
			state.pic = pic
		}
	}
})


export default store