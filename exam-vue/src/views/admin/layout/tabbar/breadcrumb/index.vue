<template>
  <!-- 顶部左侧静态 -->
  <el-icon
      style="margin-right:20px;cursor: pointer"
      @click="changeIcon">
    <component :is="LayOutSettingStore.fold ? 'Fold' : 'Expand'"></component>
  </el-icon>
  <!-- 左侧面包屑 -->
  <el-breadcrumb separator-icon="ArrowRight">
    <!-- 面包动态展示路由名字与标题 -->
    <el-breadcrumb-item
        v-for="(item, index) in $route.matched"
        :key="index"
        v-show="(item.meta as object).title"
        :to="item.path">
      <!-- 面包屑展示匹配路由的标题 -->
      <span>{{ (item.meta as object).title }}</span>
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script setup lang="ts">
import {useRoute} from 'vue-router';
import useLayOutSettingStore from '@/store/modules/layoutTabBar.ts';
//获取layout配置相关的仓库
let LayOutSettingStore = useLayOutSettingStore();
//获取路由对象
let $route = useRoute();
//点击图标的方法
const changeIcon = () => {
  //图标进行切换
  LayOutSettingStore.fold = !LayOutSettingStore.fold
}

</script>
<script lang="ts">
export default {
  name: "Breadcrumb"
}
</script>

<style scoped lang="scss">
.el-breadcrumb {
  font-size: 16px;
}
.el-icon{
  font-size: 16px;
}

</style>