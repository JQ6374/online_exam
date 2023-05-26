<template>
  <div class="tab-bar">
    <div class="tab-bar-left">
      <Breadcrumb/>
    </div>
    <div class="tab-bar-right">
      <Setting/>
    </div>
  </div>
</template>

<script setup lang="ts">
import Breadcrumb from './breadcrumb/index.vue'
import Setting from './setting/index.vue';
</script>
<script lang="ts">
export default {
  name: "TabBar"
}
</script>
<style scoped lang="scss">
.tab-bar {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: space-between;
  // background-image: linear-gradient(to right, rgb(232, 223, 223), rgb(201, 178, 178), rgb(197, 165, 165));

  .tab-bar-left {
    display: flex;
    align-items: center;
    margin-left: 20px;

  }

  .tab-bar-right {
    display: flex;
    align-items: center;
  }
}
</style>