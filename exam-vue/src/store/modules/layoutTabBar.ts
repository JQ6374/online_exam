//小仓库:layout组件相关配置仓库
import {defineStore} from 'pinia'

const useLayOutSettingStore = defineStore('SettingStore', {
  state: () => {
    return {
      fold: false, //用户控制菜单折叠还是收起控制
      refresh: false, //仓库这个属性用于控制刷新效果
    }
  },
  actions: {
    // 刷新页面
    refreshFn(){
      this.refresh = !this.refresh;
    }
  }
})

export default useLayOutSettingStore
