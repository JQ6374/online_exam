<!--<template>-->
<!--  <el-button type="primary" @click="handleClick">点我</el-button>-->
<!--  <svg-icon name="404" width="100px" height="100px"></svg-icon>-->

<!--</template>-->

<!--<script setup lang="ts">-->
<!--import {useRouter} from "vue-router";-->

<!--const $router = useRouter()-->
<!--const handleClick = () => {-->
<!--  $router.push({name: 'student'})-->
<!--}-->


<!--</script>-->

<!--<style scoped>-->

<!--</style>-->
<!-- App.vue -->
<template>
  <div class="page-container">
    <div class="question-list">
      <h2 class="question-list-title">题目列表</h2>
      <el-collapse v-model="activeNames" accordion>
        <el-collapse-item
            v-for="(question, idx) in questions"
            :key="question.id"
            :title="question.title"
            :name="question.id"
        >
          <div class="question-item">
            <div class="question-detail">
              <div class="question-number">{{ idx + 1 }}.</div>
              <div class="question-content">{{ question.content }}</div>
            </div>
            <div class="question-options">
              <el-radio-group v-model="question.answer" disabled>
                <el-radio :label="option" v-for="option in question.options" :key="option">
                  {{ option }}
                </el-radio>
              </el-radio-group>
            </div>
            <div class="question-score">{{ question.score }}分</div>
          </div>
        </el-collapse-item>
      </el-collapse>
    </div>
  </div>
</template>

<script>
import { defineComponent } from "vue";
import { ElCollapse, ElCollapseItem, ElRadio, ElRadioGroup } from "element-plus";

export default defineComponent({
  components: {
    ElCollapse,
    ElCollapseItem,
    ElRadio,
    ElRadioGroup,
  },
  data() {
    return {
      activeNames: [],
      questions: [
        {
          id: 1,
          title: "单选题",
          content: "下列哪个不是 JavaScript 的数据类型？",
          options: ["String", "Int", "Undefined", "Boolean"],
          answer: "",
          score: 2,
        },
        {
          id: 2,
          title: "多选题",
          content: "下列哪些是 ECMAScript 6 中新增的语法特性？",
          options: ["let 和 const 关键字", "箭头函数", "双冒号运算符", "模块化导入导出"],
          answer: [],
          score: 3,
        },
        {
          id: 3,
          title: "填空题",
          content: "React 使用的是什么编程模型？",
          answer: "",
          score: 5,
        },
      ],
    };
  },
});
</script>

<style>
/* 全局样式 */
body {
  margin: 0;
  padding: 0;
}

.app {
  margin: 0 auto;
}

/* 页面容器样式 */
.page-container {
  margin: 32px auto;
  width: 960px;
  background-color: #fff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 24px;
}

/* 题目列表样式 */
.question-list {
  font-size: 14px;
  color: #666;
}

.question-list-title {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 16px;
}

/* 题目项样式 */
.question-item {
  margin-top: 16px;
  border: 1px solid #eee;
  border-radius: 8px;
  padding: 16px;
  display: flex;
  justify-content: space-between;
}

/* 题目详情样式 */
.question-detail {
  width: 60%;
  display: flex;
  align-items: center;
}

.question-number {
  font-size: 18px;
  font-weight: bold;
  margin-right: 8px;
}

.question-content {
  text-indent: 2em;
  line-height: 1.5;
}

/* 题目选项样式 */
.question-options {
  display: flex;
  align-items: center;
}

.question-score {
  font-size: 16px;
  font-weight: bold;
}
</style>