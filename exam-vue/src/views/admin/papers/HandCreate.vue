<template>
  <my-el-top-bottom
      title="手动组卷"
  >
    <template #main>
      <el-form
          ref="ruleFormRef"
          :model="form"
      >
        <el-form-item
            prop="name"
            label="试卷名称"
            required
        >
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <div class="type-num-check">
          <div class="my-title el-form-item__label">
            <span>* 题型分值</span>
          </div>
          <ul class="my-ul">
            <li
                v-for="(key, index) in Object.keys(form.topicScore)"
                :key="key">
              <span>{{ topicTypeNameList[key] }}</span>
              <el-slider
                  v-model="form.topicScore[key]"
                  show-input
                  show-stops
                  :min="0"
                  :max="10"
              />
            </li>
          </ul>
        </div>
        <div>
          <div class="my-title el-form-item__label" style="margin-bottom: 15px">
            <span>* 选择题库</span>
          </div>
          <el-transfer
              v-model="value"
              filterable
              :filter-method="filterMethod"
              filter-placeholder="State Abbreviations"
              :data="data"
              :titles="['现有题库', '待选择题库']"
              @right-check-change="choiceHandle"
          />
        </div>
        <el-form-item class="my-item-deep">
          <el-button
              type="primary"
              @click="addHandPapers"
          >
            生成试卷
          </el-button>
        </el-form-item>
      </el-form>
    </template>

  </my-el-top-bottom>
</template>

<script setup lang="ts">
import MyElTopBottom from "@/views/admin/components/MyElTopBottom.vue";
import {onMounted, reactive, ref} from 'vue'
import myRequest from "@/utils/request.ts";
import {ApiResult} from "@/utils/type.ts";
import useUserStore from "@/store/modules/user.ts";
import request from "@/utils/request.ts";
import {ElNotification} from "element-plus";
import {MyElNotification} from "@/hook/requestTooltip.ts";
import {Code} from "@/utils/Code.ts";
import useLayOutSettingStore from "@/store/modules/layoutTabBar.ts";

onMounted(() => {
  getResData();
  getTopicTypeList();
})
const userStore = useUserStore()

interface ResDataType {
  tId: number,
  uId: number,
  typeId: number,
  typeName: string,
  tagId: number,
  tagName: string,
  difficultyId: number,
  difficultyName: string,
  question: string,
  answer: string,
  status: number,
  isExist: number,
  createTime: string
}

const choiceHandle = (key) => {
  form.tIds = key;
  console.log(form.tIds)
}
// 获取题库数据
const getResData = async () => {
  const res = await myRequest.get<any, ApiResult>(`/topic/${userStore.uId}`)
  const redData = res.data as ResDataType[]
  redData.forEach(item => {
    const label = `${item.typeName}、${item.tagName}、${item.question}`
    data.value.push({
      key: item.tId,
      label: label,
      initial: label
    })
  })

}
const form = reactive({
  uId: userStore.uId,
  tIds: [],
  name: '',
  topicScore: {
    "1": 1,
    "2": 2,
    "3": 2,
    "4": 2,
    "5": 5
  },
})
const topicTypeList = ref([])
const topicTypeNameList = ref({})
const getTopicTypeList = async () => {
  const res = await request.get<any, ApiResult>(`/topicType`)
  topicTypeList.value = res.data as [];
  topicTypeList.value.forEach((item) => {
    topicTypeNameList.value[item.typeId] = item.name
  })
}
const ruleFormRef = ref();

const settingStore = useLayOutSettingStore()
const addHandPapers = async () => {
  ruleFormRef.value?.validate(async (flag) => {
    if (!flag) {
      ElNotification({
        title: '请完善信息！',
        message: '试卷名是不是还没填呐~',
        type: 'warning'
      })
      return
    }
    if (form.tIds.length == 0) {
      ElNotification({
        title: '请完善信息！',
        message: '请选择要试卷对应的题目呐~',
        type: 'warning'
      })
      return
    }
    const res = await request.post<any, ApiResult>('/topic/topicToPapers', form)
    MyElNotification(res, Code.SAVA_OK, '试卷生成', 3000);
    if (res.code == Code.SAVA_OK) {
      settingStore.refreshFn();
    }
  })
}

interface Option {
  key: number
  label: string
  initial: string
}

const data = ref<Option[]>([])

const value = ref([])
const filterMethod = (query, item) => {
  return item.initial.toLowerCase().includes(query.toLowerCase())
}


</script>
<style scoped lang="scss">
$one-title-font-size: 18px;
.el-transfer {
  display: flex;
  align-items: center;

  :deep(.el-transfer-panel) {
    flex: 1;
  }

  :deep(.el-transfer-panel__body) {
    height: 90%;
  }
}

.el-form-item {
  :deep(.el-form-item__content) {
    flex-wrap: nowrap;
  }
}

.my-item-deep {
  :deep(.el-form-item__content) {
    justify-content: center;
  }
}

.my-title {
  font-size: $one-title-font-size;
}

.el-form-item {
  :deep(.el-form-item__label) {
    font-size: $one-title-font-size;
  }
}

.my-ul {
  margin-left: 30px;

  li {
    display: flex;
    align-items: center;
    margin: 10px;

    span {
      display: block;
      white-space: nowrap;
      margin-right: 30px;
    }

    .remark {
      font-size: 14px;
      color: #a6a3a3;
    }
  }
}
</style>