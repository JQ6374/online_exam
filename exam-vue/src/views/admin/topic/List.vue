<template>
  <my-el-top-bottom
      title="题目列表"
      placeholder="根据题型或者标签或者难度或者题目查找"
      isSearch
      requestUrl="/topic"
      @update="updateTableData"
  >
    <template #main>
      <el-table
          width="100%"
          :height=useMainHeight().mainHeight
          :data="tableData"
          stripe
          style="width: 100%">
        <el-table-column align="center" prop="tId" label="题目号" width="80"/>
        <el-table-column align="center" prop="typeName" label="题型" width="100" sortable/>
        <el-table-column align="center" prop="tagName" label="标签" width="100" sortable/>
        <el-table-column align="center" prop="difficultyName" label="难度" width="100" sortable/>
        <el-table-column align="center" prop="question" label="题目" show-overflow-tooltip/>
        <el-table-column align="center" prop="answer" label="答案" show-overflow-tooltip/>
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
      <el-dialog v-model="dialogFormVisible" title="修改题目数据" @close="cleanValue">
        <el-form :model="form">
          <el-form-item label="题目" :label-width="formLabelWidth">
            <el-input v-model="form.question" autocomplete="off"/>
          </el-form-item>

          <el-form-item label="答案" :label-width="formLabelWidth">
            <el-select v-show="[1].includes(form.typeId)"
                       v-model="form.answer"
                       placeholder="请选择答案"
                       style="width: 240px"
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
                       style="width: 240px"
            >
              <el-option
                  v-for="item in typeSelect.choice"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              />
            </el-select>
            <el-input v-show="[4, 5].includes(form.typeId)" v-model="form.answer" autocomplete="off"/>
          </el-form-item>
        </el-form>
        <template #footer>
                <span class="dialog-footer">
                  <el-button @click="dialogFormVisible = false">取消</el-button>
                  <el-button type="primary" @click="updateData">
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
import {onMounted, reactive, ref} from "vue";
import myRequest from "@/utils/request.ts";
import {ApiResult, Topic} from "@/utils/type.ts";
import request from "@/utils/request.ts";
import {MyElNotification} from "@/hook/requestTooltip.ts";
import {Code} from "@/utils/code.ts";
import {ElMessageBox, ElNotification} from "element-plus";
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
const getTableData = async () => {
  await myRequest.get<any, ApiResult>(`/topic/${userStore.uId}`)
      .then(res => {
        const data = res.data as [];
        data.map((item: Topic, index) => {
          const answerDict = JSON.parse(item.answer)
          const answer: string = answerDict.answerContent
          const typeId = answerDict.typeId
          if (typeId == '1') {
            // 1是对，0是错
            item.answer = answer == '1' ? '√' : '×';
          } else {
            item.answer = answer;
          }
          return item;
        })
        tableData.value = data
      })
}

const dialogFormVisible = ref(false)
const formLabelWidth = '140px'

interface Form {
  tId: number;
  uId: number;
  typeId: number;
  typeName: string;
  tagId: number;
  tagName: string;
  difficultyId: number;
  difficultyName: string;
  question: string;
  answer: string[] | string | number;
}

const form: Form = reactive({
  tId: 0,
  uId: 0,
  typeId: 0,
  typeName: '',
  tagId: 0,
  tagName: '',
  difficultyId: 0,
  difficultyName: '',
  question: '',
  answer: [],
})

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
  form.answer = [];
}
const handleEdit = (row) => {
  dialogFormVisible.value = true;
  for (let key in form) {
    if (key == 'answer') {
      form[key] = JSON.parse(row[key]);
    } else {
      form[key] = row[key];
    }
  }
}

const updateData = async () => {
  if (form.typeId == 3) {
    form.answer = JSON.stringify(form.answer);
  }
  dialogFormVisible.value = false;
  try {
    const res = await request.put<any, ApiResult>('/topic', form)
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
    const res = await request.delete<any, ApiResult>(`/topic/${row.tId}`)
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

<style scoped>
@import "@/styles/admin-style.scss";
</style>