<template>
  <div class="countdown">
    <div class="timer" v-if="isStarted">{{ isStarted ? `${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}` : `${startHours.toString().padStart(2, '0')}:${startMinutes.toString().padStart(2, '0')}:${startSeconds.toString().padStart(2, '0')}` }}</div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, computed, watchEffect } from 'vue';

export default defineComponent({
  name: 'ExamCountdown',
  props: {
    startTime: {
      type: String,
      required: true,
    },
    endTime: {
      type: String,
      required: true,
    },
  },
  setup(props) {
    const startTime = new Date(props.startTime).getTime();
    const endTime = new Date(props.endTime).getTime();

    const isStarted = ref<boolean>(false);

    const hours = ref<number>(0);
    const minutes = ref<number>(0);
    const seconds = ref<number>(0);

    const startHours = computed(() => Math.floor(((startTime - new Date().getTime()) / (1000 * 60 * 60)) % 24));
    const startMinutes = computed(() => Math.floor(((startTime - new Date().getTime()) / (1000 * 60)) % 60));
    const startSeconds = computed(() => Math.floor(((startTime - new Date().getTime()) / 1000) % 60));

    const updateTime = () => {
      const nowTime = new Date().getTime();
      if (nowTime >= startTime && nowTime <= endTime) {
        isStarted.value = true;
        const timeDiff = endTime - nowTime;
        hours.value = Math.floor((timeDiff / (1000 * 60 * 60)) % 24);
        minutes.value = Math.floor((timeDiff / (1000 * 60)) % 60);
        seconds.value = Math.floor((timeDiff / 1000) % 60);
      } else {
        isStarted.value = false;
      }
    };

    updateTime();
    watchEffect(() => {
      const timer = setInterval(updateTime, 1000);
      return () => clearInterval(timer);
    });

    return {
      isStarted,
      hours,
      minutes,
      seconds,
      startHours,
      startMinutes,
      startSeconds,
    };
  },
});
</script>

<style scoped>
.countdown {
  display: flex;
  justify-content: center;
  align-items: center;
}

.timer {
  font-weight: bold;
  background-color: #f1f1f1;
  padding: 0.5rem 1rem;
  border-radius: 0.5rem;
}
</style>