import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui' 
import router from './router'
import 'element-ui/lib/theme-chalk/index.css'
import qs from "qs"
import {$get,$postJson,$postX,$postFile} from "@/ajax"
import store from '@/store'
// import axios from "axios"

// axios.defaults.baseURL="http://127.0.0.1:8000";

Vue.prototype.$imgurl="http://127.0.0.1:8000/api";
// Vue.prototype.$axios = axios;
Vue.prototype.$qs = qs;
Vue.prototype.$get = $get;
Vue.prototype.$postJson = $postJson;
Vue.prototype.$postX = $postX;
Vue.prototype.$postFile = $postFile;


Vue.use(ElementUI)

Vue.config.productionTip = false

new Vue({
	router, //挂载
	store : store,
  render: h => h(App),
  router : router,
}).$mount('#app')
