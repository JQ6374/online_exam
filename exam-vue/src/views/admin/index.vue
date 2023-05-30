<template>
  <div class="layout-container">
    <!-- 左侧菜单 -->
    <div class="layout-slider shadow-lg" :class="{fold: LayOutSettingStore.fold}">
      <Logo></Logo>
      <!-- 展示菜单 -->
      <!-- 滚动组件 -->
      <el-scrollbar class="scrollbar">
        <!-- 菜单组件-->
        <el-menu
            :collapse="LayOutSettingStore.fold"
            :default-active="$route.name"
            active-text-color="#3b82f6"
            text-color="#6b7280">
          <!--          根据路由动态生成菜单-->
          <Menu :menuList="userStore.menuRoutes"></Menu>
        </el-menu>
      </el-scrollbar>
    </div>
    <!-- 右侧侧边区域 -->
    <div class="layout-right">
      <!-- 顶部导航 -->
      <div class="layout-tab-bar shadow-lg" :class="{ fold: LayOutSettingStore.fold }">
        <!-- layout组件的顶部导航tabbar -->
        <TabBar></TabBar>
      </div>
      <!-- 内容展示区域 -->
      <div class="layout-main shadow-lg" :class="{ fold: LayOutSettingStore.fold }">
        <el-scrollbar class="scrollbar">
          <Main></Main>
        </el-scrollbar>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
//获取路由对象
import {useRoute} from 'vue-router'
//引入左侧菜单logo子组件
import Logo from '@/components/Logo/index.vue'
//引入菜单组件
import Menu from './layout/menu/index.vue'
//右侧内容展示区域
import Main from './layout/main/index.vue';
//引入顶部tabbar组件
import TabBar from './layout/tabbar/index.vue';
//获取用户相关的小仓库
import useUserStore from '@/store/modules/user';
import useLayOutSettingStore from '@/store/modules/layoutTabBar.ts';
//获取路由对象
const $route = useRoute();
//获取仓库
const userStore = useUserStore();
userStore.constantRouterChoice($route.path.split('/')[1])
const LayOutSettingStore = useLayOutSettingStore();


</script>

<script lang="ts">
export default {
  name: "admin"
}
</script>
<style scoped lang="scss">
// 阴影
.shadow-general {
  box-sizing: border-box;
  box-shadow: 0 5px 20px rgba(213, 192, 192, 0.2);
}

.layout-container {
  width: 100%;
  height: 100vh;
  background-color: $base-background-color;
  padding: $base-page-padding;
  display: flex;

  .layout-slider {
    width: $base-menu-width;
    height: 100%;
    background: $item-background-color;
    transition: all 0.3s;
    border-radius: $base-item-radius;
    padding: 10px;
    margin-right: 15px;

    .scrollbar {
      width: 100%;
      height: calc(100% - $base-menu-logo-height);

      .el-menu {
        font-size: 24px;
        border-right: none;
      }
    }

    &.fold {
      width: $base-menu-min-width;
    }
  }

  .layout-right {
    flex: 1;
    height: 100%;
    transition: all 0.3s;

    &.fold {
      width: calc(100vw - $base-menu-min-width);
      left: $base-menu-min-width;
    }

    .layout-tab-bar {
      padding-right: 20px;
      margin-bottom: 15px;
      height: $base-tabbar-height;
      border-radius: $base-item-radius;
      background-color: $item-background-color;
      overflow: hidden;
    }

    .layout-main {
      height: calc(100% - $base-tabbar-height - 15px);
      border-radius: $base-item-radius;
      background-color: $item-background-color;
      padding: 20px;
    }
  }

}
</style>