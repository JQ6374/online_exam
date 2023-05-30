<template>
  <div class="logo" v-if="setting.logoHidden">
    <img :src="setting.logo" alt="">
    <p v-if="showTitle && !layOutSettingStore.fold">{{ setting.title }}</p>
  </div>
</template>

<script setup lang="ts">
//引入设置标题与logo这配置文件
import setting from "@/setting.ts";
import useLayOutSettingStore from "@/store/modules/layoutTabBar.ts";

const layOutSettingStore = useLayOutSettingStore()
defineProps({
  showTitle: {
    type: Boolean,
    default: true
  }
})


</script>

<style scoped lang="scss">
.logo {
  width: 100%;
  height: $base-menu-logo-height;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px;

  img {
    width: $base-logo-img-height;
    height: $base-logo-img-height;
  }

  p {
    font-size: $base-logo-title-fontSize;
    margin-left: 20px;
  }
}
</style>