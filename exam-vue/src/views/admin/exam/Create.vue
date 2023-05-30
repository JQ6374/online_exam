<template>
  <my-el-top-bottom
      title="考试创建"
  >
    <template #main>
      <el-form
          ref="ruleFormRef"
          :model="form"
          :rules="rules"
      >
        <el-form-item
            prop="name"
            label="考试名"
        >
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item
            prop="cId"
            label="课程名"
        >
          <el-select
              v-model="form.cId"
              placeholder="请选择要对应的课程"
              style="width: 240px"
          >
            <el-option
                v-for="item in courseList"
                :key="item.cId"
                :label="item.name"
                :value="item.cId"
            />
          </el-select>
        </el-form-item>
        <el-form-item
            prop="pId"
            label="试卷名"
        >
          <el-select
              v-model="form.pId"
              placeholder="请选择试卷"
              style="width: 240px"
          >
            <el-option
                v-for="item in papersList"
                :key="item.pId"
                :label="item.name"
                :value="item.pId"
            />
          </el-select>
        </el-form-item>
        <el-form-item
            prop="startTime"
            label="选择时间">
          <el-date-picker
              v-model="timeRange"
              type="datetimerange"
              range-separator="-"
              start-placeholder="开始时间"
              end-placeholder="结束时间"
              @change="changeTime"
          />
        </el-form-item>
        <el-form-item class="my-item-deep">
          <el-button
              type="primary"
              @click="addExam">提交
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
import {timeFormat} from "@/hook/utils.ts";

onMounted(() => {
  getCourseList();
  getPapersList();
})
const uId = useUserStore().uId;


const ruleFormRef = ref<FormInstance>()
const form = reactive({
  uId: uId,
  cId: '',
  pId: '',
  name: '',
  startTime: '',
  endTime: '',
})
const rules = reactive<FormRules>({
  cId: {required: true, message: '请选择对应的课程', trigger: 'blur'},
  pId: {required: true, message: '请选择对应试卷'},
  name: {required: true, message: '请输入考试名', trigger: 'blur'},
  startTime: {required: true, message: '请选择开始时间'},
  endTime: {required: true, message: '请选择结束时间'},
})

const settingStore = useLayOutSettingStore();
const addExam = () => {
  ruleFormRef.value?.validate(async (flag) => {
    if (!flag) return
    console.log(form)
    const res = await request.post<any, ApiResult>('/exam/createExam', form)
    MyElNotification(res, Code.SAVA_OK, '添加')
    settingStore.refresh = !settingStore.refresh;
  })
}


const userStore = useUserStore();
const papersList = ref([])
const courseList = ref([])
const getPapersList = async () => {
  const res = await request.get<any, ApiResult>(`/papers/${userStore.uId}`)
  papersList.value = res.data as [];
}
const getCourseList = async () => {
  const res = await request.get<any, ApiResult>(`/course/${userStore.uId}`)
  courseList.value = res.data as [];
}
const timeRange = ref<[Date, Date]>([])
const changeTime = (val: [Date, Date]) => {
  form.startTime = timeFormat(val[0])
  form.endTime = timeFormat(val[1])
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