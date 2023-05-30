<template>
  <my-el-top-bottom
      title="考试列表"
      placeholder="根据学号或者姓名查找"
      isSearch
      requestUrl="/user/searchStudent"
      @update="updateTableData"
  >
    <template #main>
      <el-table width="100%" :height=useMainHeight().mainHeight :data="tableData" stripe>
        <el-table-column align="center" prop="uId" label="学号" fixed/>
        <el-table-column align="center" prop="userName" label="学生姓名"/>
        <el-table-column align="center" prop="courseName" label="课程名"/>
        <el-table-column align="center" prop="email" label="邮箱" sortable/>
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
      <el-dialog v-model="dialogFormVisible" title="修改学生数据">
        <el-form :model="form">
          <el-form-item label="学生姓名" :label-width="formLabelWidth">
            <el-input v-model="form.userName" autocomplete="off" disabled />
          </el-form-item>
          <el-form-item label="课程名称" :label-width="formLabelWidth">
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
        </el-form>
        <template #footer>
                <span class="dialog-footer">
                  <el-button @click="dialogFormVisible = false">取消</el-button>
                  <el-button type="primary" @click="updateStudentCourse">
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
import myRequest from "@/utils/request.ts";
import {ApiResult} from "@/utils/type.ts";
import request from "@/utils/request.ts";
import {MyElNotification} from "@/hook/requestTooltip.ts";
import {Code} from "@/utils/Code.ts";
import {ElMessageBox, ElNotification} from "element-plus";
import useUserStore from "@/store/modules/user.ts";
import setting from "@/setting.ts";

const userStore = useUserStore();
const updateTableData = (res) => {
  tableData.value = res
}

onMounted(() => {
  getTableData();
  getCourseList();
})

const tableData = ref([])
const courseList = ref([])

const getTableData = async () => {
  const res = await myRequest.get<any, ApiResult>(`/user/student/${userStore.uId}`)
  tableData.value = res.data as []
}

const getCourseList = async () => {
  const res = await request.get<any, ApiResult>(`/course/${userStore.uId}`)
  courseList.value = res.data as [];
}

const dialogFormVisible = ref(false)
const formLabelWidth = '140px'

const form = reactive({
  uId: '',
  cId: '',
  ucId: '',
  userName: '',
  courseName: '',
  email: '',
})

const handleEdit = (row) => {
  dialogFormVisible.value = true;
  for (let key in form) {
    form[key] = row[key];
  }
}
const updateStudentCourse = async () => {
  dialogFormVisible.value = false;
  try {
    const res = await request.put<any, ApiResult>('/course/updateStudentByCourse', form)
    MyElNotification(res, Code.UPDATE_OK, '修改');
    await getTableData();
  } catch (e) {
    console.log(e)
  }
}
const handleDelete = (row) => {
  ElMessageBox.confirm('此操作将永久删除数据, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    const res = await request.delete<any, ApiResult>(`/user/deleteStudentByCourse/${row.ucId}`)
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