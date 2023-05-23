<script setup lang="ts">
import request from "@/utils/request.ts";
import {onMounted} from "vue";
import {ElMessage} from "element-plus";

onMounted(() => {
  request.post()
})


</script>

<template>
  <h1>这是test文件</h1>
  <svg-icon name="404" width="100px" height="100px"></svg-icon>
</template>

<style scoped lang="scss">
h1 {
  color: $color;
}

</style>