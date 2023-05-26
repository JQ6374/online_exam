<template>
  <el-container>
    <el-header>
      <span>课表列表</span>
    </el-header>
    <el-main>
      <el-table :data="tableData" stripe style="width: 100%">
        <el-table-column prop="index" label="序号" width="180"/>
        <el-table-column prop="name" label="课程名称" width="180"/>
        <el-table-column prop="courseCode" label="课程口令"/>
        <el-table-column prop="createTime" label="创建时间"/>
      </el-table>
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import myRequest from "@/utils/request.ts";
import {onMounted, ref} from "vue";
import {ApiResult} from "@/utils/type.ts";

onMounted(() => {
  getTableData();
})
const tableData = ref([])
const getTableData = async () => {
  const uId = 9;
  await myRequest.get<any, ApiResult>(`/course/${uId}`)
      .then(res => {
        const data = res.data;
        // (data as []).map((index, item) => {
        //   item['index'] = index
        //   item['createTime'] = Date.parse(item['createTime'])
        //   return item;
        // })
        console.log(data)
        tableData.value = (data as [])
      })
}
// const tableData = [
//   {
//     name: '2016-05-03',
//     code: 'Tom',
//     createTime: 'No. 189, Grove St, Los Angeles',
//   },
//   {
//     name: '2016-05-02',
//     code: 'Tom',
//     createTime: 'No. 189, Grove St, Los Angeles',
//   },
//   {
//     name: '2016-05-04',
//     code: 'Tom',
//     createTime: 'No. 189, Grove St, Los Angeles',
//   },
//   {
//     name: '2016-05-01',
//     code: 'Tom',
//     createTime: 'No. 189, Grove St, Los Angeles',
//   },
// ]
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

</style>