<template>
  <my-el-top-bottom
      title="考试列表"
  >
    <!--      placeholder="根据学号或者姓名查找"-->
    <!--      isSearch-->
    <!--      requestUrl="/user/searchStudent"-->
    <!--      @update="updateTableData"-->
    <template #main>
      <el-table
          width="100%"
          :height=useMainHeight().mainHeight
          :data="tableData"
          stripe
      >
        <el-table-column align="center" prop="eId" label="考试号"/>
        <el-table-column align="center" prop="name" label="考试名" sortable/>
        <el-table-column align="center" prop="courseName" label="课程名" sortable/>
        <el-table-column align="center" prop="startTime" label="开始时间" sortable/>
        <el-table-column align="center" prop="endTime" label="结束时间" sortable/>
        <el-table-column align="center" label="操作">
          <template #default="scope">
            <el-button
                type="primary"
                icon="Edit"
                circle
                @click="handleEdit(scope.row)"
            />
            <el-button
                type="danger"
                icon="Delete"
                circle
                @click="handleDelete(scope.row)"
            />
          </template>
        </el-table-column>
      </el-table>
      <!-- 提示框 -->
      <el-dialog
          v-model="dialogFormVisible"
          title="修改考试数据"
          draggable
      >
        <el-form :model="form">
          <el-form-item
              prop="name"
              label="考试名"
              :label-width="formLabelWidth">
            <el-input v-model="form.name" autocomplete="off"/>
          </el-form-item>
          <el-form-item
              prop="courseName"
              label="课程名"
              :label-width="formLabelWidth">
            <el-select
                v-model="form.cId"
                placeholder="请选择要更改的课程"
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
              label="修改时间"
              :label-width="formLabelWidth">
            <el-date-picker
                v-model="timeRange"
                type="datetimerange"
                range-separator="-"
                start-placeholder="开始时间"
                end-placeholder="结束时间"
                @change="changeTime"
            />
          </el-form-item>
        </el-form>
        <template #footer>
                <span class="dialog-footer">
                  <el-button @click="dialogFormVisible = false">取消</el-button>
                  <el-button type="primary" @click="updateInfo">
                    更改
                  </el-button>
                </span>
        </template>
      </el-dialog>
    </template>
  </my-el-top-bottom>
</template>

<script setup lang="ts">
import MyElTopBottom from "@/views/admin/components/MyElTopBottom.vue";
import {useMainHeight} from "@/store/modules/mainHeight.ts";
import {onMounted, reactive, ref} from "vue";
import {ApiResult} from "@/utils/type.ts";
import {MyElNotification} from "@/hook/requestTooltip.ts";
import {Code} from "@/utils/Code.ts";
import {ElMessageBox, ElNotification} from "element-plus";
import useUserStore from "@/store/modules/user.ts";
import setting from "@/setting.ts";
import request from "@/utils/request.ts";
import {timeFormat} from "@/hook/utils.ts";
import {loadExamStatus} from "@/hook/updateExamStatus.ts";

onMounted(() => {
  getTableData();
  getCourseList();
  loadExamStatus()
})
const courseList = ref([])
const getCourseList = async () => {
  const res = await request.get<any, ApiResult>(`/course/${userStore.uId}`)
  courseList.value = res.data as [];
}

const userStore = useUserStore();
const updateTableData = (res) => {
  tableData.value = res
}

const tableData = ref([])

const getTableData = async () => {
  const res = await request.get<any, ApiResult>(`/exam/${userStore.uId}`)
  tableData.value = res.data as []
}

const dialogFormVisible = ref(false)
const formLabelWidth = '140px'

const form = reactive({
  eId: '',
  uId: '',
  pId: '',
  cId: '',
  name: '',
  courseName: '',
  teacherName: '',
  content: '',
  startTime: '',
  endTime: '',
  status: '',
  isExist: ''
})
const timeRange = ref<[Date, Date]>([])
const handleEdit = (row) => {
  dialogFormVisible.value = true;
  for (let key in form) {
    form[key] = row[key];
  }
}


const changeTime = (val: [Date, Date]) => {
  form.startTime = timeFormat(val[0])
  form.endTime = timeFormat(val[1])
}
const updateInfo = async () => {
  dialogFormVisible.value = false;
  console.log(form)
  const res = await request.put<any, ApiResult>('/exam/updateExam', form)
  console.log(res)
  MyElNotification(res, Code.UPDATE_OK, '修改');
  await getTableData();
}

const handleDelete = (row) => {
  ElMessageBox.confirm('此操作将永久删除数据, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    const res = await request.delete<any, ApiResult>(`/exam/${row.eId}`)
    MyElNotification(res, Code.DELETE_OK, '删除');
    await getTableData();
  }).catch(() => {
    // 点击取消按钮后的回调函数
    ElNotification({
      title: '提示信息',
      message: '您取消了该操作！',
      type: 'warning',
      duration: setting.duration
    })
  });
}

</script>

<style scoped lang="scss">
@import "@/styles/admin-style";

</style>