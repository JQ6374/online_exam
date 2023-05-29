<template>
  <section class="w3l-hotair-form">
    <div class="container">
      <!-- /form -->
      <div class="workinghny-form-grid">
        <div class="main-hotair">
          <div class="w3l_form align-self" style="border-radius: 8px 0 0 8px;">
            <div class="left_grid_info"></div>
          </div>
          <div class="content-wthree" style="border-radius: 0 8px 8px 0;">
            <h2>注册</h2>
            <p>在线考试系统</p>
            <el-form ref="ruleFormRef"
                     :model="ruleForm"
                     :rules="rules"
                     class="demo-ruleForm"
                     status-icon>
              <el-form-item prop="username">
                <el-input
                    size="large"
                    prefix-icon="User"
                    placeholder="请输入用户名"
                    v-model="ruleForm.username"
                />
              </el-form-item>
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
              <el-form-item prop="passwordCheck">
                <el-input
                    type="password"
                    show-password
                    size="large"
                    prefix-icon="Lock"
                    placeholder="请再次输入密码"
                    v-model="ruleForm.passwordCheck"
                />
              </el-form-item>
              <el-form-item
                  prop="checkCode"
              >
                <el-input
                    size="large"
                    prefix-icon="Key"
                    placeholder="请输入图形校验码"
                    v-model="ruleForm.checkCode"
                >
                  <template #append>
                    <CheckCode @update:checkCode="handleCheckCode"></CheckCode>
                  </template>
                </el-input>
              </el-form-item>
              <el-form-item prop="emailCode">
                <el-input
                    size="large"
                    prefix-icon="Key"
                    placeholder="请输入邮箱验证码"
                    v-model="ruleForm.emailCode"
                >
                  <template #append>
                    <span
                        ref="sendEmailEL"
                        class="send-button"
                        :class="notAllowAction"
                        @click="sendEmail"
                    >{{ sendText }}</span>
                  </template>
                </el-input>
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
                  @click="registerFn"
                  :loading="loading">
                注册
              </el-button>
            </el-form>
            <p class="account">现在登录？<a href="#/login">登录</a></p>
          </div>
        </div>
      </div>
      <!-- //form -->
    </div>
  </section>
</template>

<script setup lang="ts">
import {onMounted, reactive, ref} from 'vue'
import {Lock, Message, Key, User} from "@element-plus/icons-vue";
import {ElNotification} from "element-plus";
import type {FormInstance, FormRules} from 'element-plus'
import {ApiResult} from "@/utils/type.ts";
import CheckCode from '@/components/CheckCode/index.vue'
import request from "@/utils/request.ts";
import {Code} from "@/utils/Code.ts";

onMounted(() => {
  getRoleData();
})

const roleData = ref([])
const getRoleData = async () => {
  const res = await request.get<any, ApiResult>('/role')
  roleData.value = res.data as []
  console.log(roleData)
}

// 表单
const ruleFormRef = ref<FormInstance>()

const ruleForm = reactive({
  rId: 1,
  username: '',
  email: '',
  password: '',
  passwordCheck: '',  // 第二次密码
  checkCode: '',  // 验证码
  emailCode: ''   // 邮箱验证码
})
const rightCheckCode = ref('')
const handleCheckCode = (num) => {
  rightCheckCode.value = num.join('')
}

// 验证两次密码是否正确
const checkPasswordFn = (rule: any, value: any, callback: any) => {
  console.log(value)
  if (!value) {
    return callback(new Error('请再次输入密码!'));
  }
  if (ruleForm.password != value) {
    return callback(new Error('两次密码输入不一致！'));
  } else {
    return callback();
  }
}

const checkCodeFn = (rule: any, value: any, callback: any) => {
  if (!value) {
    return callback(new Error('请输入验证码!'));
  }
  if (rightCheckCode.value != value) {
    return callback(new Error('验证码输入错误！'));
  } else {
    return callback();
  }
}

// 表单检验
const rules = reactive<FormRules>({
  username: {
    required: true, message: '请输入用户名', trigger: 'blur'
  },
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
  passwordCheck: [
    {validator: checkPasswordFn, trigger: 'blur'}
  ],
  checkCode: [
    {validator: checkCodeFn, trigger: 'blur'}
  ],
  emailCode: [
    {required: true, message: '请输入验证码', trigger: 'blur'}
  ]
})

const sendButtonStatus = ref(true)
const sendText = ref('发送验证码')
const notAllowAction = ref('')
const sendEmail = () => {
  ruleFormRef.value?.validateField(['checkCode', 'email'], (isValid, invalidFields) => {
    if (isValid && sendButtonStatus.value) {
      request
          .post<any, ApiResult>('/auth/send_email', {'toEmail': ruleForm.email})
          .then((res) => {
            const isSuccess = res.code == Code.SAVA_OK;
            ElNotification({
              title: res.msg,
              type: isSuccess ? 'success' : 'warning',
              message: isSuccess ? '请注意查收' : '',
            })
            sendButtonStatus.value = false;
            notAllowAction.value = 'not-allow'
            let timeTotal = 60;
            let intervalId = setInterval(() => {
              sendText.value = `${timeTotal}秒`;
              timeTotal--;
              if (timeTotal < 0) {
                sendButtonStatus.value = true;
                notAllowAction.value = ''
                sendText.value = '发送验证码';
                clearInterval(intervalId);
              }
            }, 1000);
          })
          .catch(error => {
            ElNotification({
              title: '发送失败',
              type: 'error',
              message: new Error(error).message
            })
          })
    }
  })
}

let loading = ref(false)

const registerFn = () => {
  ruleFormRef.value?.validate((isValid, invalidFields) => {
    if (isValid) {
      if (ruleForm.rId == '') {
        ElNotification({
          title: '等一等~',
          type: 'warning',
          message: '请选择要注册的角色！'
        })
      } else {
        request
            .post<any, ApiResult>('/user/register', ruleForm)
            .then(res => {
              if (res.code == Code.SAVA_OK) {
                ElNotification({
                  title: '注册成功！',
                  message: '快去登录叭！',
                  type: 'success'
                })
                for (let key in ruleForm) {
                  ruleForm[key] = ''
                }
              } else {
                ElNotification({
                  title: '注册失败！',
                  message: res.msg,
                  type: 'warning'
                })
              }
            })
      }

    }
  })
}

</script>

<style scoped src="@/assets/css/login.css" lang="scss"></style>
<style scoped lang="scss">
.w3l-hotair-form {
  background-color: $base-background-color;
  padding: 0;
}
</style>
