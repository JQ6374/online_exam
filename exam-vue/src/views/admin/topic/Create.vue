<template>
  <my-el-top-bottom
      title="题目创建"
  >
    <template #main>
      <el-form
          ref="ruleFormRef"
          :model="form"
          :rules="rules"
      >
        <el-form-item
            prop="typeId"
            label="题目类型"
        >
          <el-select v-model="form.typeId" placeholder="请选择" @change="cleanValue">
            <el-option v-for="item in typeList"
                       :key="item.value"
                       :label="item.label"
                       :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item
            prop="question"
            label="题目内容"
        >
          <el-input
              rows="5"
              type="textarea"
              v-model="form.question"></el-input>
        </el-form-item>
        <el-form-item
            prop="answer"
            label="答案"
        >
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
        </el-form-item>
        <el-form-item
            prop="tagId"
            label="标签"
        >
          <el-select v-model="form.tagId" placeholder="请选择标签">
            <el-option v-for="item in tagList"
                       :key="item.tagId"
                       :label="item.name"
                       :value="item.tagId"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item
            prop="difficultyId"
            label="难度"
        >
          <el-rate :max="3" v-model="form.difficultyId"/>
        </el-form-item>
        <el-form-item
            prop="status"
            label="是否与其他老师共享题目"
        >

          <el-switch
              v-model="switchValue"
              @change="switchChange"
          />
        </el-form-item>
        <el-form-item class="my-item-deep">
          <el-button
              type="primary"
              @click="addTopic">提交
          </el-button>
        </el-form-item>
      </el-form>
    </template>
  </my-el-top-bottom>
</template>

<script setup lang="ts">
import {onMounted, reactive, ref} from "vue";
import MyElTopBottom from "@/views/admin/components/MyElTopBottom.vue";
import useUserStore from "@/store/modules/user.ts";
import request from "@/utils/request.ts";
import {ApiResult} from "@/utils/type.ts";
import {FormInstance, FormRules} from "element-plus";
import {MyElNotification} from "@/hook/requestTooltip.ts";
import {Code} from "@/utils/Code.ts";
import useLayOutSettingStore from "@/store/modules/layoutTabBar.ts";

onMounted(() => {
  getTagList()
})
const uId = useUserStore().uId;

const switchValue = ref(false)
const switchChange = (val) => {
  form.status = val ? 1 : 0
}

interface Form {
  uId: string,
  typeId: number,
  tagId: string,
  difficultyId: number,
  question: string,
  answer: string | Array<string>,
  status: number
}

const ruleFormRef = ref<FormInstance>()
const form = reactive<Form>({
  uId: uId,
  typeId: 1,
  tagId: '',
  difficultyId: 1,
  question: '',
  answer: '',
  status: 0
})
const rules = reactive<FormRules>({
  typeId: {required: true},
  tagId: {required: true, message: '请选择标签', trigger: 'blur'},
  difficultyId: {required: true},
  question: {required: true, message: '请输入题目', trigger: 'blur'},
  answer: {required: true, message: '请输入答案', trigger: 'blur'},
  status: {required: true},
})
const getTagList = async () => {
  const res = await request.get<any, ApiResult>(`/tag/${uId}`)
  tagList.value = res.data as [];
}

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
let tagList = ref([])
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
const settingStore = useLayOutSettingStore();
const addTopic = () => {
  ruleFormRef.value?.validate(async (flag) => {
    if (!flag) return
    if (form.typeId == 3) {
      form.answer = JSON.stringify(form.answer)
    }
    const res = await request.post<any, ApiResult>('/topic', form)
    MyElNotification(res, Code.SAVA_OK, '添加')
    settingStore.refresh = !settingStore.refresh;
  })
}

</script>

<style scoped lang="scss">
.el-form {
  max-width: 500px;
  margin: 0 auto;
}

.my-item-deep {
  :deep(.el-form-item__content) {
    justify-content: center;
  }
}
</style>