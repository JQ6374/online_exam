<!--
  组件说明：
    属性：
      title<String>：标题
      placeholder<String>：输入框提示信息
      isSearch<Boolean>：是否显示搜索框
      requestUrl<String>: 所搜接口的请求地址
        例子：/course
    事件：
      update：(res:[]) => {}  res中存放着查询出来的数据
  后台接口格式（REST风格）：/baseUrl/{老师Id}/{搜索的值}
-->
<template>
  <el-container>
    <el-header>
      <span>{{ title }}</span>
      <div class="search" v-if="isSearch">
        <el-input
            v-model="searchValue"
            :placeholder="placeholder"
            prefix-icon="Search"
        />
        <el-button icon="Search" circle @click="searchFn"/>
      </div>
      <slot name="header"></slot>
    </el-header>
    <el-main>
      <slot name="main"></slot>
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import {ref} from "vue";
import {ElNotification} from "element-plus";
import request from "@/utils/request.ts";
import {ApiResult} from "@/utils/type.ts";
import {MyElNotification} from "@/hook/requestTooltip.ts";
import {Code} from "@/utils/code.ts";
import useUserStore from "@/store/modules/user.ts";

const userStore = useUserStore();

const props = defineProps({
  title: {
    type: String
  },
  placeholder: {
    type: String
  },
  isSearch: {
    type: Boolean,
    default: false
  },
  requestUrl: {
    type: String
  }
})

const $emit = defineEmits(['update'])

const searchValue = ref('')
const searchFn = async () => {
  if (searchValue.value == '') {
    ElNotification({
      title: '查询失败！',
      message: '请输入查询内容',
      type: 'info'
    })
    return
  }
  const url = props.requestUrl + `/${userStore.uId}/${searchValue.value}`
  const res = await request.get<any, ApiResult>(url)
  MyElNotification(res, Code.GET_OK, '查询');
  // searchDateStore.tableData = res.data as []
  $emit('update', res.data as [])
}
</script>

<style scoped lang="scss">
.search {
  display: flex;
  margin-left: 50px;

  .el-input {
    margin-right: 15px;
    width: 300px;
  }
}

.el-header {
  display: flex;
  height: $el-header-height;
  align-items: center;
  border-left: 5px solid #3b82f6;

  span {
    padding-right: 15px;
    font-size: 24px;
  }
}
</style>