<template>
  <template v-for="item in menuList" :key="item.path">
    <!--没有子路由-->
    <template v-if="!item.children">
      <el-menu-item :index="item.path" v-if="!item.meta.hidden" @click="goRoute">
        <el-icon>
          <component :is="item.meta.icon"></component>
        </el-icon>
        <template #title>
          <span style="font-size: 16px">{{ item.meta.title }}</span>
        </template>
      </el-menu-item>
    </template>
    <!-- 有子路由且递归所有的子路由 -->
    <el-sub-menu :index="item.path" v-if="item.children">
      <template #title>
        <el-icon>
          <component :is="item.meta.icon"></component>
        </el-icon>
        <span style="font-size: 16px">{{ item.meta.title }}</span>
      </template>
      <Menu :menuList="item.children"></Menu>
    </el-sub-menu>
  </template>
</template>

<script setup lang="ts">
import {useRouter} from 'vue-router';
//获取父组件传递过来的全部路由数组
defineProps(['menuList']);

//获取路由器对象
const myRouter = useRouter();
//点击菜单的回调
const goRoute = (vc: any) => {
  //路由跳转
  myRouter.push({name: vc.index});
}
</script>
<script lang="ts">
export default {
  name: 'Menu'
}
</script>

<style scoped></style>