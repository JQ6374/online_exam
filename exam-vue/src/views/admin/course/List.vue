<template>
  <my-el-top-bottom
      title="课程列表"
      placeholder="根据课程名查找"
      isSearch
      requestUrl="/course"
      @update="updateTableData"
  >
    <template #main>
      <el-table
          width="100%"
          :height="useMainHeight().mainHeight"
          :data="tableData" stripe>
        <el-table-column align="center" prop="cId" label="课程号" fixed/>
        <el-table-column align="center" prop="name" label="课程名称"/>
        <el-table-column align="center" prop="courseCode" label="课程口令"/>
        <el-table-column align="center" prop="createTime" label="创建时间" sortable/>
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
      <el-dialog v-model="dialogFormVisible" title="修改课程数据">
        <el-form :model="form">
          <el-form-item label="课程名称" :label-width="formLabelWidth">
            <el-input v-model="form.name" autocomplete="off"/>
          </el-form-item>
          <el-form-item label="课程码" :label-width="formLabelWidth">
            <el-input v-model="form.courseCode" disabled autocomplete="off"/>
          </el-form-item>
        </el-form>
        <template #footer>
                <span class="dialog-footer">
                  <el-button @click="dialogFormVisible = false">取消</el-button>
                  <el-button type="primary" @click="updateName">
                    更改
                  </el-button>
                </span>
        </template>
      </el-dialog>
    </template>
  </my-el-top-bottom>
</template>

<script setup lang="ts">
import myRequest from "@/utils/request.ts";
import request from "@/utils/request.ts";
import {onMounted, reactive, ref} from 'vue'
import {ApiResult} from "@/utils/type.ts";
import {ElMessageBox, ElNotification} from "element-plus";
import {Code} from "@/utils/Code.ts";
import {MyElNotification} from "@/hook/requestTooltip.ts";
import MyElTopBottom from "@/views/admin/components/MyElTopBottom.vue";
import {useMainHeight} from "@/store/modules/mainHeight.ts";
import useUserStore from "@/store/modules/user.ts";
import setting from "@/setting.ts";

const userStore = useUserStore();

const updateTableData = (res) => {
  tableData.value = res
}

onMounted(() => {
  getTableData();
})
const tableData = ref([])
const form = reactive({
  cId: '',
  uId: '',
  name: '',
  courseCode: '',
  createTime: ''
})

const getTableData = async () => {
  await myRequest.get<any, ApiResult>(`/course/${userStore.uId}`)
      .then(res => {
        tableData.value = res.data as []
      })
}

const dialogFormVisible = ref(false)
const formLabelWidth = '140px'


const handleEdit = (row) => {
  dialogFormVisible.value = true;
  for (let key in form) {
    form[key] = row[key];
  }
}
const updateName = async () => {
  dialogFormVisible.value = false;
  try {
    const res = await request.put<any, ApiResult>('/course/updateName', form)
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
    const res = await request.delete<any, ApiResult>(`/course/${row.cId}`)
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