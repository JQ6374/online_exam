<template>
  <router-view v-slot="{ Component }">
    <transition name="fade">
      <component :is="Component"/>
    </transition>
  </router-view>
</template>

<script setup lang="ts">
import useUserStore from "@/store/modules/user.ts";
import {onMounted} from "vue";

onMounted(() => {
  getUser()
})

const userStore = useUserStore()
const getUser = () => {
  userStore.uId = localStorage.getItem("uId");
  userStore.username = localStorage.getItem("username");
}
</script>

<style scoped>
.fade-enter-from {
  opacity: 0;
  transform: scale(0);
}

.fade-enter-active {
  transition: all .5s;
}

.fade-enter-to {
  opacity: 1;
  transform: scale(1);
}
</style>
