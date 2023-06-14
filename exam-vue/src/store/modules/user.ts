import {defineStore} from "pinia";
import {reqLogin} from "@/api/user";
import {Code} from "@/utils/Code.ts";
import {LoginData} from "@/utils/type.ts";
import {constantRouter} from "@/router/routers.ts";
import {ref} from "vue";
import jwtDecode from "jwt-decode";
import {REMOVE_TOKEN, SET_TOKEN} from "@/utils/token.ts";

const constantRouterList = ref()
const useUserStore = defineStore('User', {
  state: () => {
    return {
      uId: '',
      username: '',
      token: '',
      menuRoutes: constantRouterList
    }
  },
  actions: {
    constantRouterChoice(name: string) {
      constantRouterList.value = constantRouter
          .filter(item => item.name == name)
          .map(item => item.children)[0]
    },
    async userLogin(reqData: object) {
      const res = await reqLogin(reqData);
      if (res.code == Code.GET_OK) {
        //pinia仓库存储一下token
        //由于pinia|vuex存储数据其实利用js对象
        // this.token = String((res.data as LoginData).uId)
        //本地存储持久化存储一份
        // SET_TOKEN(String((res.data as LoginData).uId))
        //能保证当前async函数返回一个成功的promise
        const data: LoginData = res.data as LoginData;
        const token: string = data.token;
        const parseToken: any = jwtDecode(token);
        const userInfo = JSON.parse(parseToken.sub);
        SET_TOKEN(token, userInfo.uId, userInfo.username)
        this.uId = localStorage.getItem('uId') || ''
        this.username = localStorage.getItem('username') || ''
        this.token = localStorage.getItem('token') || ''
      } else {
        return Promise.reject(new Error(res.msg))
      }
    },
    userLogout() {
      this.uId = '';
      this.username = '';
      this.token = '';
      REMOVE_TOKEN();
    }
  },
  getters: {}
})

export default useUserStore;