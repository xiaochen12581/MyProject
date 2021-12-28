import qs from 'qs'
import axios from 'axios'
import {Message} from 'element-ui'
import router from '../router/index'

let token = ''
axios.defaults.withCredentials = false;
axios.defaults.headers.common['Authorization'] = token;

let baseUrl = 'http://127.0.0.1:8000'
var instance = axios.create({
	baseURL : baseUrl//以后要改主机或端口, 只要改这里
})

//添加一个请求拦截器 AOP 事前增强
// 每次发出 axios 请求时, 都会先运行这段程序
instance.interceptors.request.use(	
	config => {
		let token = window.localStorage.getItem('token');
		console.log("axios 拦截器读取 token===>" + token)
		if (token) {
			config.headers.Authorization = "bearer " + token;
		}
		return config
	},
	error => {
		return Promise.reject(error)
	}
);
// http response 响应拦截器
instance.interceptors.response.use(
    response => {
        return response;
    },
    error => {
        if (error.response) {
            switch (error.response.status) {
                case 401:
                   Message.error({message:'你没有权限，请登录后重试',offset:150});
                   // window.localStorage.setItem('token', "");
					router.push('/home'); 
                   break;
                case 403:   //禁止访问
                   Message.error({message:'禁止访问',offset:250});
				   router.push('/home');
                   window.localStorage.setItem('token', "");
            }
        }
        return Promise.reject(error.response.data)   // 返回接口返回的错误信息
    }
);

// url = '/api/....' params  JS对象 
export let $get = (url, params) => {
	return instance.get(url, {
		params : params
	})
}

// data 参数是js对象 , SpringBoot 参数接收 @RequestBody 类
// data 参数是js数组 , SpringBoot 参数接收 @RequestBody List<类>
export let $postJson = (url,data) => {
	return instance.post(url, data)
}

//data: JS 对象   转成 x-www-form-urlencoded 发送到 SpringBoot, 用普通参数接收
export let $postX = (url, data) => {
	console.log(data)
	//JS 对象 ==> id=123&username=tom&
	let strKV = qs.stringify(data)
	console.log(strKV)
	return instance.post(
	    url, strKV, 
		{
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		})
}

// 文件上传, formData 格式, multipart/form-data
// formData 是 new FormData
export let $postFile = (url, formdata) => {
	return instance.post(url, formdata, {'Content-Type':'multipart/form-data'})
}
