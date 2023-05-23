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
// @ts-ignore
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
// @ts-ignore
import globalComponent from '@/components'

const app = createApp(App);
app.use(globalComponent)
app.use(ElementPlus, {
    locale: zhCn
});
app.mount('#app')
