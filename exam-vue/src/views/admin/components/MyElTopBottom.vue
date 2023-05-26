<template>
  <el-container>
    <el-header>
      <span>{{ title }}</span>
      <slot name="header"></slot>
    </el-header>
    <el-main>
      <slot name="main"></slot>
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
defineProps(['title'])
</script>

<style scoped lang="scss">
$el-header-height: 40px;

.el-header {
  display: flex;
  height: $el-header-height;
  align-items: center;
  border-left: 5px solid #3b82f6;

  span {
    padding-right: 15px;
    font-size: 24px;
  }
}
</style>