<template>
  <div class="question-form">
    <template v-if="type === 'judgement'">
      <el-form ref="form" :model="formData">
        <el-form-item label="题目">
          <el-input v-model="formData.content" placeholder="请输入题目"></el-input>
        </el-form-item>
        <el-form-item label="答案">
          <el-radio-group v-model="formData.answer">
            <el-radio :label="true">正确</el-radio>
            <el-radio :label="false">错误</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="分值">
          <el-input-number v-model="formData.score" :min="1" :max="100" :step="1" precision="0" controls-position="right" placeholder="请输入分值"></el-input-number>
        </el-form-item>
        <el-form-item label="难度">
          <el-select v-model="formData.difficulty" placeholder="请选择难度">
            <el-option v-for="difficulty in difficulties" :key="difficulty.value" :label="difficulty.label" :value="difficulty.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="标签">
          <el-select v-model="formData.tag" placeholder="请选择标签">
            <el-option v-for="tag in tags" :key="tag.value" :label="tag.label" :value="tag.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm">提交</el-button>
        </el-form-item>
      </el-form>
    </template>
    <template v-else-if="type === 'singleChoice'">
      <!-- 类型为单选题的表单 -->
    </template>
    <!-- 类型为多选题的表单 -->
    <template v-else-if="type === 'multiChoice'">
      <!-- 类型为多选题的表单 -->
    </template>
    <template v-else-if="type === 'fillInBlank'">
      <!-- 类型为填空题的表单 -->
    </template>
    <template v-else-if="type === 'subjective'">
      <!-- 类型为主观题的表单 -->
    </template>
  </div>
</template>

<script>
import { defineComponent, reactive } from 'vue';
import { ElForm, ElFormItem, ElInput, ElRadioGroup, ElRadio, ElInputNumber, ElSelect, ElOption, ElButton } from 'element-plus';

export default defineComponent({
  name: 'QuestionForm',
  props: {
    type: {
      type: String,
      required: true,
    },
    difficulties: {
      type: Array,
      required: true,
    },
    tags: {
      type: Array,
      required: true,
    },
  },
  setup(props) {
    const formData = reactive({
      content: '',
      answer: '',
      score: '',
      difficulty: '',
      tag: '',
    });

    const submitForm = () => {
      console.log(formData);
    };

    return {
      formData,
      submitForm,
    };
  },
  components: {
    ElForm,
    ElFormItem,
    ElInput,
    ElRadio,
    ElRadioGroup,
    ElInputNumber,
    ElSelect,
    ElOption,
    ElButton,
  },
});
</script>

<style scoped>
.question-form {
  max-width: 600px;
  margin: 0 auto;
}

.el-form-item {
  margin-bottom: 20px;
}

.el-form-item__label {
  width: 100px;
  text-align: right;
  margin-right: 20px;
}

.el-form-item__content {
  flex: 1;
}
</style>