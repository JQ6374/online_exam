import {createApp} from 'vue'
import './style.css'

// @ts-ignore
import App from '@/App.vue'
// element-plus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

// svg插件
import 'virtual:svg-icons-register'

// 全局变量配置
import '@/styles/index.scss'

// @ts-ignore ElementPlus使用中文
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'

// @ts-ignore 注册全局组件
import globalComponent from '@/components'

// 配置路由
import router from "@/router";

// 配置pinia仓库
import pinia from "@/store";

const app = createApp(App);
app.use(globalComponent)
app.use(ElementPlus, {
    locale: zhCn
});
app.use(router);
app.use(pinia);
app.mount('#app')
