import router from "@/router/index.ts";
// @ts-ignore
import nprogress from "nprogress"
import "nprogress/nprogress.css"
import myRequest from "@/utils/request.ts";
import {ApiResult} from "@/utils/type.ts";
// @ts-ignore
import {Action, ElMessage, ElMessageBox, ElNotification} from "element-plus";
import {useRouter} from "vue-router";
import {REMOVE_TOKEN} from "@/utils/token.ts";

const $router = useRouter();

// @ts-ignore
router.beforeEach(async (to, from, next) => {
  nprogress.start();
  const token: string = localStorage.getItem('token') || ''
  if (token) {
    // 用户登录
    next();
  } else {
    // 用户未登录
    if (to.path == '/login' || to.path == '/register') {
      next();
    } else {
      await next({path: '/login'});
    }
  }
})

// @ts-ignore
router.beforeResolve(async (to, from) => {
  nprogress.done();
  if (to.path == '/login' || to.path == '/register')
    return;
  const res = await myRequest.get<any, ApiResult>('/checkToken')
  if (String(res.code).includes('700')) {
    await ElMessageBox.alert('会话过期，请重新登录！', '提示', {
      confirmButtonText: '确定',
      callback: () => {
        REMOVE_TOKEN()
        $router.push({name: 'login'})
      },
    })
  }
})
