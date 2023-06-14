import router from "@/router/index.ts";
// @ts-ignore
import nprogress from "nprogress"
import "nprogress/nprogress.css"
import pinia from "@/store/index.ts"
import useUserStore from "@/store/modules/user.ts";
const userStore = useUserStore(pinia);

router.beforeEach((to, from, next) => {
  console.log(userStore.uId)
  nprogress.start();
  next();
})

router.beforeResolve((to, from) => {
  nprogress.done();
})
