<template>
  <my-el-top-bottom
      title="随机组卷"
  >
    <template #main>
      <el-form
          ref="ruleFormRef"
          :model="form"
          :rules="rules"
      >
        <el-form-item
            prop="papersName"
            label="试卷名称"
        >
          <el-input v-model="form.papersName"></el-input>
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


        <div class="type-num-check">
          <div class="my-title el-form-item__label">
            <span>题型数量</span>
          </div>
          <ul class="my-ul">
            <li
                v-for="(key, index) in Object.keys(form.check.typeNumCheck)"
                :key="key">
              <span>{{ topicTypeNameList[key] }}</span>
              <el-slider
                  v-model="form.check.typeNumCheck[key]"
                  show-input
                  show-stops
                  :min="0"
                  :max="50"
              />
            </li>
          </ul>
        </div>

        <div class="type-num-check">
          <div class="my-title el-form-item__label">
            <span>题型分值</span>
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

        <div class="type-num-check">
          <div class="my-title el-form-item__label">
            <span>难度分布</span>
          </div>
          <ul class="my-ul">
            <li><span class="remark">说明：（例如，10题里面有8题简单题，那么简单题就选择8颗星）</span></li>
            <li
                v-for="(key, index) in Object.keys(form.check.difficultyCheck)"
                :key="key">
              <span>{{ difficultyNameList[key] }}</span>
              <el-rate
                  show-score
                  :max="10"
                  v-model="form.check.difficultyCheck[key]"/>
            </li>
          </ul>
        </div>
        <el-form-item class="my-item-deep">
          <el-button
              type="primary"
              @click="addRandomPapers"
          >
            生成试卷
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
import {ElNotification, FormInstance, FormRules} from "element-plus";
import {MyElNotification} from "@/hook/requestTooltip.ts";
import {Code} from "@/utils/code.ts";
import useLayOutSettingStore from "@/store/modules/layoutTabBar.ts";

onMounted(() => {
  getTagList();
  getTopicTypeList();
  getDifficultyList();
})

const userStore = useUserStore()
const ruleFormRef = ref<FormInstance>()
const form = reactive({
  uId: userStore.uId,
  tagId: '',
  papersName: '',
  topicScore: {
    "1": 1,
    "2": 2,
    "3": 2,
    "4": 2,
    "5": 5
  },
  check: {
    typeNumCheck: {
      "1": 10,
      "2": 10,
      "3": 10,
      "4": 5,
      "5": 2
    },
    difficultyCheck: {
      "1": 8,
      "2": 2,
      "3": 1
    }
  }
})
const rules = reactive<FormRules>({
  papersName: {required: true, message: '请选择题型', trigger: 'blur'},
  tagId: {required: true, message: '请选择标签', trigger: 'blur'}
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
const difficultyList = ref([])
const difficultyNameList = ref([])
const getDifficultyList = async () => {
  const res = await request.get<any, ApiResult>(`/difficulty`)
  difficultyList.value = res.data as [];
  difficultyList.value.forEach((item) => {
    difficultyNameList.value[item?.dId] = item.name
  })
}
const tagList = ref([])
const getTagList = async () => {
  const res = await request.get<any, ApiResult>(`/tag/${userStore.uId}`)
  tagList.value = res.data as [];
}
const settingStore = useLayOutSettingStore()
const addRandomPapers = () => {
  ruleFormRef.value?.validate(async (flag) => {
    if (!flag) {
      ElNotification({
        title: '请完善信息！',
        message: '试卷名或者标签是不是还没填呐~',
        type: 'warning'
      })
      return
    }
    const res = await request.post<any, ApiResult>('/topic/topicRandomToPapers', form)
    MyElNotification(res, Code.SAVA_OK, '试卷随机生成', 3000)
    if (res.code == Code.SAVA_OK) {
      settingStore.refreshFn();
    }
  })
}


</script>

<style scoped lang="scss">
$one-title-font-size: 18px;
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

