<template>
  <div class="create-question">
    <el-radio-group v-model="questionType" size="medium">
      <el-radio-button v-for="(type, index) in questionTypes" :key="index" :label="type.value">{{
          type.label
        }}
      </el-radio-button>
    </el-radio-group>
    <question-form v-bind="questionFormProps"/>
  </div>
</template>

<script>
import {defineComponent, computed} from 'vue';
import QuestionForm from '@/components/QuestionForm3.vue';

export default defineComponent({
  name: 'CreateQuestion',
  components: {
    QuestionForm,
  },
  setup() {
    const questionTypes = [
      {label: '判断题', value: 'judgement'},
      {label: '单选题', value: 'singleChoice'},
      {label: '多选题', value: 'multiChoice'},
      {label: '填空题', value: 'fillInBlank'},
      {label: '主观题', value: 'subjective'},
    ];
    const questionType = computed(() => questionTypes[0].value);

    const questionFormProps = computed(() => {
      return {
        type: questionType.value,
        difficulties: [
          {label: '简单', value: 'easy'},
          {label: '普通', value: 'medium'},
          {label: '困难', value: 'hard'},
        ],
        tags: [
          {label: '美术', value: 'art'},
          {label: '历史', value: 'history'},
          {label: '化学', value: 'chemistry'}],
      };
    });

    return {
      questionTypes,
      questionType,
      questionFormProps,
    };
  },
}); </script>
<style scoped>
.create-question {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}
</style>