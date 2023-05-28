<template>
  <el-button type="primary" @click="handleClick">点我</el-button>
  <svg-icon name="404" width="100px" height="100px"></svg-icon>

</template>

<script setup lang="ts">
import {useRouter} from "vue-router";

const $router = useRouter()
const handleClick = () => {
  $router.push({name: 'student'})
}


</script>

<style scoped>

</style>