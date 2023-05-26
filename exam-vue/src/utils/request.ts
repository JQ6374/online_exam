import axios from "axios";
// @ts-ignore
import {ElMessage} from "element-plus";
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
    ElMessage({
        type: 'error',
        message: error.message || "请求失败！"
    })
    return Promise.reject(error);
});

export default myRequest;