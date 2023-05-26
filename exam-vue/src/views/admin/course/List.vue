<template>
  <el-container>
    <el-header>
      <span>课表列表</span>
    </el-header>
    <el-main>
      <el-table
          :data="tableData"
          stripe
          style="width: 100%">
        <el-table-column align="center" prop="index" label="序号"/>
        <el-table-column align="center" prop="name" label="课程名称"/>
        <el-table-column align="center" prop="courseCode" label="课程口令"/>
        <el-table-column align="center" prop="createTime" label="创建时间"/>
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
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import myRequest from "@/utils/request.ts";
import {reactive, ref, onMounted} from 'vue'
import {ApiResult} from "@/utils/type.ts";
import {Action, ElMessage, ElMessageBox, ElNotification} from "element-plus";
import request from "@/utils/request.ts";
import {Code} from "@/utils/Code.ts";
import myElNotification from "@/hook/requestTooltip.ts";

onMounted(() => {
  getTableData();
})
const tableData = ref([])
const getTableData = async () => {
  const uId = 9;
  await myRequest.get<any, ApiResult>(`/course/${uId}`)
      .then(res => {
        const data = res.data as [];
        data.map((item: object, index) => {
          item.index = index + 1;
          return item;
        })
        tableData.value = data
      })
}

const dialogFormVisible = ref(false)
const formLabelWidth = '140px'

const form = reactive({
  cId: '',
  uId: '',
  name: '',
  courseCode: '',
  createTime: ''
})

const handleEdit = (row) => {
  dialogFormVisible.value = true;
  for (let key in form) {
    form[key] = row[key];
  }
}
const updateName = () => {
  dialogFormVisible.value = false;
  console.log(form.valueOf())
  request
      .put<any, ApiResult>(
          '/updateName',
          {cId: form.cId, name: form.name}
      )
      .then((res) => {
        console.log(res)
        myElNotification(res, Code.UPDATE_OK, '修改');
      })
}
const handleDelete = (row) => {
  ElMessageBox.confirm('此操作将永久删除数据, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    request.delete<any, ApiResult>(`/course/${row.cId}`)
        .then(res => {
          const isSuccess = res.code == Code.DELETE_OK
          ElNotification({
            title: isSuccess ? '删除成功' : '删除失败',
            message: isSuccess ? res.msg : Code.ERROR_MSG,
            type: isSuccess ? 'success' : 'error',
          })
        })
  }).catch(() => {
    // 点击取消按钮后的回调函数
    ElNotification({
      title: '提示信息',
      message: '您取消了该操作！',
      type: 'warning',
    })
  });
}

</script>

<style scoped lang="scss">
.el-header {
  display: flex;
  height: 40px;
  align-items: center;
  border-left: 5px solid #3b82f6;

  span {
    padding-right: 15px;
    font-size: 24px;
  }
}

.el-button--text {
  margin-right: 15px;
}

.el-select {
  width: 300px;
}

.el-input {
  width: 300px;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}

</style>