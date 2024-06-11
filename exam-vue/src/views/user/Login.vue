<template>
  <section class="w3l-hotair-form">
    <div class="container">
      <!-- /form -->
      <div class="workinghny-form-grid">
        <div class="main-hotair">
          <div class="content-wthree">
            <h2>登录</h2>
            <p>在线考试系统</p>
            <el-form ref="ruleFormRef"
                     :model="ruleForm"
                     :rules="rules"
                     class="demo-ruleForm"
                     status-icon>
              <el-form-item prop="email">
                <el-input
                    size="large"
                    prefix-icon="Message"
                    placeholder="请输入邮箱"
                    v-model="ruleForm.email"
                />
              </el-form-item>
              <el-form-item prop="password">
                <el-input
                    type="password"
                    show-password
                    size="large"
                    prefix-icon="Lock"
                    placeholder="请输入密码"
                    v-model="ruleForm.password"
                />
              </el-form-item>
              <el-radio-group
                  v-model="ruleForm.rId" class="ml-4"
                  style="width: 100%; justify-content: space-around">
                <el-radio
                    v-for="item in roleData" :key="item.rId" :label="item.rId"
                    size="large"
                    style="margin: 0"
                >
                  {{ item.name }}
                </el-radio>
              </el-radio-group>
              <el-button
                  type="primary"
                  round
                  @click="login"
                  :loading="loading">
                登录
              </el-button>
            </el-form>
            <p class="account">没有账号？<a href="#/register">注册</a></p>
          </div>
          <div class="w3l_form align-self">
            <div class="left_grid_info"></div>
          </div>
        </div>
      </div>
      <!-- //form -->
    </div>
  </section>
</template>

<script setup lang="ts">
import {onMounted, reactive, ref} from 'vue'
import {Lock, Message} from "@element-plus/icons-vue";
import {ElNotification} from "element-plus";
import type {FormInstance, FormRules} from 'element-plus'
import useUserStore from "@/store/modules/user.ts";
import {ApiResult, LoginData} from "@/utils/type.ts";
import {useRouter, useRoute} from "vue-router";
import request from "@/utils/request.ts";

onMounted(() => {
  getRoleData();
})

let $router = useRouter();
let $route = useRoute()

const roleData = ref([])
const getRoleData = async () => {
  const res = await request.get<any, ApiResult>('/role')
  roleData.value = res.data as []
}

const userStore = useUserStore()
// 表单
const ruleFormRef = ref<FormInstance>()

const ruleForm = reactive({
  rId: 1,
  email: '',
  password: '',
  emailCode: ''
})

// 表单检验
const rules = reactive<FormRules>({
  email: [
    {required: true, message: '请输入邮箱', trigger: 'blur'},
    {
      pattern: '^([a-zA-Z]|[0-9])(\\w|\\-)+@[a-zA-Z0-9]+\\.([a-zA-Z]{2,4})',
      message: '格式错误，请输入正确的邮箱', trigger: 'blur'
    }
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
    {min: 6, message: '长度不少于6个字符', trigger: 'blur'}
  ],
})

let loading = ref(false)
// 登录
const login = async () => {
  // 等待结果，若失败则不会向后执行
  await ruleFormRef.value?.validate();
  loading.value = true;
  try {
    await userStore.userLogin(ruleForm)
    loading.value = false;
    let redirect: any = $route.query.redirect;
    let toPath = '/index'
    if (ruleForm.rId == 1) {
      toPath = '/admin'
    }
    await $router.push({path: redirect || toPath});
    ElNotification({
      title: '登录成功！',
      message: `欢迎回来${userStore.username} ~`,
      type: 'success'
    })

  } catch (error) {
    loading.value = false;
    //登录失败的提示信息
    ElNotification({
      title: '登录失败！',
      message: (error as Error).message,
      type: 'warning',
    })
  }

}

</script>

<style scoped src="@/assets/css/login.css" lang="scss">

</style>
<style scoped lang="scss">
.w3l-hotair-form {
  background-color: $base-background-color;
}
</style>