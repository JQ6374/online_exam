// 注册全局组件
// @ts-ignore
import SvgIcon from '@/components/SvgIcon/index.vue'

const allGlobalComponent: any = {SvgIcon};
export default {
    // 务必叫做install方法，在main.ts，use的时候，会执行一次这个方法
    // @ts-ignore
    install(app) {
        // 注册项目所有的全局组件
        Object.keys(allGlobalComponent).forEach(key => {
            app.component(key, allGlobalComponent[key]);
        });
    }
}