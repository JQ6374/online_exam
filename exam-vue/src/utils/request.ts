import axios from "axios";
// @ts-ignore
import {ElMessage, ElNotification} from "element-plus";
import {Code} from "@/utils/Code.ts";
//创建axios实例
let myRequest = axios.create({
  baseURL: import.meta.env.VITE_APP_BASE_API,
  timeout: 5000
})
//请求拦截器
myRequest.interceptors.request.use(config => {
  return config;
});
//响应拦截器
myRequest.interceptors.response.use((response) => {
  return response.data;
}, (error) => {
  ElNotification({
    title: '警告！',
    message: error.message || Code.ERROR_MSG,
    type: 'error'
  })
  return Promise.reject(error);
});

export default myRequest;