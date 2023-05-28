<template>
  <my-el-top-bottom
      title="题目创建"
  >
    <template #main>
      <div class="question-form">
        <form class="el-form" label-position="top">
          <div class="el-form-item">
            <label class="el-form-item__label">题目类型</label>
            <div class="el-form-item__content">
              <el-select v-model="form.typeId" placeholder="请选择" @change="cleanValue">
                <el-option v-for="item in typeList"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value"
                ></el-option>
              </el-select>
            </div>
          </div>
          <div class="el-form-item">
            <label class="el-form-item__label">题目内容</label>
            <div class="el-form-item__content">
              <el-input type="textarea" v-model="form.question"></el-input>
            </div>
          </div>
          <div class="el-form-item">
            <label class="el-form-item__label">答案</label>
            <div class="el-form-item__content">
              <el-select v-show="[1].includes(form.typeId)"
                         v-model="form.answer"
                         placeholder="请选择答案"
              >
                <el-option
                    v-for="item in typeSelect.trueFalse"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                />
              </el-select>
              <el-select v-show="[2, 3].includes(form.typeId)"
                         v-model="form.answer"
                         :multiple="form.typeId == 3"
                         placeholder="请选择答案"
              >
                <el-option
                    v-for="item in typeSelect.choice"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                />
              </el-select>
              <el-input
                  v-show="[4, 5].includes(form.typeId)"
                  v-model="form.answer"
                  :type="form.typeId ==5 ? 'textarea':'text'"
                  autocomplete="off"/>
            </div>
          </div>
          <div class="el-form-item">
            <label class="el-form-item__label">标签</label>
            <div class="el-form-item__content">
              <el-select v-model="form.tagId" placeholder="请选择标签">
                <el-option v-for="item in tagList" :key="item.value" :label="item.label"
                           :value="item.value"></el-option>
              </el-select>
            </div>
          </div>
          <div class="el-form-item">
            <label class="el-form-item__label">难度</label>
            <div class="el-form-item__content">
              <el-rate :max="3" v-model="form.difficultyId"/>
            </div>
          </div>
          <div class="el-form-item">
            <div class="el-form-item__content" style="justify-content: center">
              <el-button type="primary" native-type="submit">提交</el-button>
            </div>
          </div>
        </form>
      </div>
    </template>
  </my-el-top-bottom>
</template>

<script setup lang="ts">
import {reactive, ref} from "vue";
import MyElTopBottom from "@/views/admin/components/MyElTopBottom.vue";


interface Form {
  uId: string,
  typeId: number,
  tagId: string,
  difficultyId: number,
  question: string,
  answer: string | Array<string>
}

const form = reactive<Form>({
  uId: '',
  typeId: 1,
  tagId: '',
  difficultyId: 1,
  question: '',
  answer: ''
})
const typeList = [
  {
    label: '判断题',
    value: 1
  }, {
    label: '单选题',
    value: 2
  }, {
    label: '多选题',
    value: 3
  }, {
    label: '填空题',
    value: 4
  }, {
    label: '主观题',
    value: 5
  },
]
const tagList = [
  {value: 1, label: '标签1'},
  {value: 2, label: '标签2'},
  {value: 3, label: '标签3'},
]
const typeSelect = ref({
  trueFalse: [
    {
      label: '√',
      value: 1
    },
    {
      label: '×',
      value: 0
    }
  ],
  choice: [
    {
      label: 'A',
      value: 'A'
    },
    {
      label: 'B',
      value: 'B'
    },
    {
      label: 'C',
      value: 'C'
    },
    {
      label: 'D',
      value: 'D'
    },
  ]
})
const cleanValue = () => {
  form.answer = []
}

</script>

<style scoped>
.question-form {
  max-width: 600px;
  margin: 0 auto;
}

.el-form-item__label {
  width: 80px;
}

.el-form-item__content {
  flex: 1;
}
</style>