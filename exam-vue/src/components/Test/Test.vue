<template>
  <!--  <el-button type="primary" @click="handleClick">点我</el-button>-->
  <!--  <svg-icon name="404" width="100px" height="100px"></svg-icon>-->
  <!--<exam-countdown-->
  <!--    start-time="2023-5-30 14:19"-->
  <!--    end-time="2023-5-30 14:20"-->
  <!--&gt;-->
  <!--</exam-countdown>-->
  <!--  <div class="mt-4">-->
  <!--    <el-checkbox-group v-model="checkboxGroup1" size="small">-->
  <!--      <el-checkbox label="Option1" border/>-->
  <!--      <el-checkbox label="Option2" border/>-->
  <!--    </el-checkbox-group>-->
  <!--  </div>-->
  <Time></Time>
</template>

<script setup lang="ts">
import {reactive, ref} from "vue";
import Time from "@/components/Test/Time.vue";

const checkboxGroup1 = ref([])

</script>

<style scoped>

</style>