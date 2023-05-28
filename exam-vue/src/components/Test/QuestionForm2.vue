
<template>
  <div class="create-question">
    <form class="question-form" @submit.prevent="submit">
      <div class="el-form-item">
        <label class="el-form-item__label">题目类型</label>
<!--        <div class="el-form-item__content">-->
<!--          <el-radio-group v-model="form.questionType">-->
<!--            <el-radio-button label="single_choice">单选题</el-radio-button>-->
<!--            <el-radio-button label="multiple_choice">多选题</el-radio-button>-->
<!--            <el-radio-button label="true_false">判断题</el-radio-button>-->
<!--            <el-radio-button label="fill_blank">填空题</el-radio-button>-->
<!--            <el-radio-button label="short_answer">简答题</el-radio-button>-->
<!--          </el-radio-group>-->
<!--        </div>-->
        <el-select v-model="form.questionType" placeholder="请选择">
          <el-option label="判断题" value="判断题"></el-option>
          <el-option label="单选题" value="单选题"></el-option>
          <el-option label="多选题" value="多选题"></el-option>
          <el-option label="填空题" value="填空题"></el-option>
          <el-option label="主观题" value="主观题"></el-option>
        </el-select>
      </div>
      <div class="el-form-item">
        <label class="el-form-item__label">题目内容</label>
        <div class="el-form-item__content">
          <el-input v-model="form.content" placeholder="请输入题目内容"></el-input>
        </div>
      </div>
      <div class="el-form-item" v-if="form.questionType !== 'fill_blank' && form.questionType !== 'short_answer'">
        <label class="el-form-item__label">选项</label>
        <div class="el-form-item__content">
          <el-button type="primary" size="small" @click="addOption">添加选项</el-button>
          <el-button type="danger" size="small" @click="removeOption">删除选项</el-button>
          <div v-for="(option, index) in form.options" :key="index">
            <el-input v-model="option.content" placeholder="请输入选项内容"></el-input>
          </div>
        </div>
      </div>
      <div class="el-form-item" v-if="form.questionType !== 'true_false'">
        <label class="el-form-item__label">答案</label>
        <div class="el-form-item__content">
          <el-checkbox-group v-model="form.answer">
            <el-checkbox v-for="(option, index) in form.options" :key="index" :label="option.content">{{ option.content }}</el-checkbox>
          </el-checkbox-group>
        </div>
      </div>
      <div class="el-form-item">
        <label class="el-form-item__label">分值</label>
        <div class="el-form-item__content">
          <el-input-number v-model="form.score" :min="1" :max="100" :step="1" precision="0" controls-position="right" placeholder="请输入分值"></el-input-number>
        </div>
      </div>
      <div class="el-form-item">
        <label class="el-form-item__label">难度</label>
        <div class="el-form-item__content">
          <el-select v-model="form.difficultyId" placeholder="请选择难度">
            <el-option v-for="item in difficulties" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </div>
      </div>
      <div class="el-form-item">
        <label class="el-form-item__label">题目和答案</label>
        <div class="el-form-item__content">
          <el-input v-model="form.questionText" placeholder="请输入题目和答案" @input="parseQuestionText(form.questionText)"></el-input>
        </div>
      </div>
      <div class="el-form-item">
        <el-button type="primary" @click="submit">提交</el-button>
      </div>
    </form>
  </div>
</template>

<script>
import { defineComponent, reactive } from 'vue';
import { useQuestionForm } from '@/hook/useQuestionForm';

export default defineComponent({
  setup() {
    const { questionType, content, options, answer, score, addOption, removeOption, submitQuestion } = useQuestionForm();

    const form = reactive({
      questionType,
      content,
      options,
      answer,
      score,
      tId: 0,
      uId: 0,
      typeId: 0,
      typeName: '',
      tagId: 0,
      tagName: '',
      difficultyId: 0,
      difficultyName: '',
      question: '',
      status: 0,
      isExist: 0,
      createTime: '',
    });

    const difficulties = [
      { id: 1, name: '简单' },
      { id: 2, name: '普通' },
      { id: 3, name: '困难' },
    ];

    const parseQuestionText = (text) => {
      const regex = /(.+)\n(.+)/s;
      const match = text.match(regex);
      if (match) {
        form.question = match[1].trim();
        form.answer = [match[2].trim()];
      } else {
        form.question = text.trim();
        form.answer = [];
      }
    };

    const submit = () => {
      submitQuestion(form);
    };

    return {
      form,
      difficulties,
      addOption,
      removeOption,
      parseQuestionText,
      submit,
    };
  },
});
</script>

<style scoped>
.create-question {
  max-width: 600px;
  margin: 0 auto;
}

.question-form {
  margin-top: 20px;
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
