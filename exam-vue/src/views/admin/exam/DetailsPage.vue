<template>
  <div class="page">
    <el-container>
      <el-header class="my-item">
        <div class="header-left">
          <div class="logo">
            <Logo
                :showTitle="false"
            />
          </div>
          <div class="title">
            <span>{{ joinExamStore.examName }}</span>
          </div>
        </div>
        <div class="header-right">
          <exam-countdown
              :start-time="joinExamStore.startTime"
              :end-time="joinExamStore.endTime"
          >
          </exam-countdown>
          <el-button
              type="primary"
              round
              @click="submitPapers"
          >
            提交试卷
          </el-button>
        </div>
      </el-header>
      <el-container class="my-main">
        <el-aside class="my-item">
          <el-scrollbar ref="mainScrollbar" height="560px">
            <div class="topic-type"
                 v-for="(content,topicType) in cleanContent"
                 v-show="content && content.length != 0"
                 :key="topicType"
            >
              <div class="title">
                <span>{{ generalDataStore.topicTypeDict[topicType] }}</span>
              </div>
              <div class="tag-list">
                <el-tag
                    v-for="(topic, i) in joinExamStore.papersContent[topicType]"
                    :key="topic.tId"
                    :data-t-id="topic.tId"
                >
                  <el-icon
                      :data-t-id="topic.tId"
                      @click.prevent="scrollToBottom"
                  >
                    <SemiSelect
                        :data-t-id="topic.tId"
                        v-if="changeStatus(topic.tId)"
                    />
                    <span
                        :data-t-id="topic.tId"
                        v-else>{{ i + 1 }}</span>

                  </el-icon>
                </el-tag>
              </div>
            </div>
          </el-scrollbar>
        </el-aside>
        <el-main class="my-item">
          <el-scrollbar
              height="555px"
              ref="mainScrollbar"
          >
            <div class="topic-list"
                 v-for="(content,topicType,topicTypeIndex) in cleanContent"
                 v-show="content && content.length != 0"
                 :key="topicType"
            >
              <div
                  v-for="(topic, index) in content"
                  class="topic-item"
                  :key="topic.tId"
                  :data-t-id="topic.tId"
              >
                <div class="header">
                  <span style="font-size: 16px">{{ topicTypeIndex + 1 }}-{{ index + 1 }}</span>
                  <el-tag
                      type="info"
                      round
                  >
                    分数 {{ topic.score }}
                  </el-tag>
                </div>
                <div class="content">
                  {{ topic.question }}
                </div>
                <div class="choice">
                  <div class="check-box">
                    <!-- 判断和单选的框 -->
                    <el-radio-group
                        v-if="[1,2].includes(topic.answer.typeId)"
                        v-model="topic.answer.answerContent"
                    >
                      <el-radio
                          v-for="item in topic.answer.typeId == 1 ? typeSelect.trueFalse: typeSelect.choice"
                          :key="item.value"
                          :label="item.value"
                          border>
                        {{ item.label }}
                      </el-radio>
                    </el-radio-group>
                    <!-- 多选题的复选框 -->
                    <el-checkbox-group
                        v-if="topic.answer.typeId == 3"
                        v-model="topic.answer.answerContent"
                        border>
                      <el-checkbox
                          v-for="item in typeSelect.choice"
                          :key="item.value"
                          :label="item.label"
                          border/>
                    </el-checkbox-group>
                    <!--填空或者主观题-->
                    <el-input
                        v-if="[4,5].includes(topic.answer.typeId)"
                        v-model="topic.answer.answerContent"
                        :rows="5"
                        :type="inputType(topic.answer.typeId)"
                        placeholder="请输入答案"
                    />
                  </div>
                </div>
                <el-divider/>
              </div>
            </div>
          </el-scrollbar>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import Logo from "@/components/Logo/index.vue";
import {computed, onMounted, reactive, ref} from "vue";
import {useGeneralDataStore} from "@/store/modules/generalData.ts";
import ExamCountdown from "@/components/Test/CountDown.vue";
import {useJoinExamStore} from "@/store/modules/joinExam.ts";
import {ElMessageBox, ElNotification} from "element-plus";
import setting from "@/setting.ts";
import {ApiResult} from "@/utils/type.ts";
import myRequest from "@/utils/request.ts";
import {MyElNotification} from "@/hook/requestTooltip.ts";
import {Code} from "@/utils/code.ts";
import {useRouter} from "vue-router";
import useUserStore from "@/store/modules/user.ts";
import user from "@/store/modules/user.ts";

onMounted(() => {
  getGeneralData();
})
const userStore = useUserStore()

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
const joinExamStore = useJoinExamStore();
const inputType = (typeId: number) => {
  return typeId == 5 ? "textarea" : "";
}
const $router = useRouter()

const cleanContent = ref(joinExamStore.papersContent)

const changeStatus = computed(() => (tId) => {
  const topic = Object.values(cleanContent.value).flat().find(item => item.tId == tId);
  return topic.answer.answerContent != '';
})

const submitPapers = () => {
  ElMessageBox.confirm('是否提交试卷?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    const submit = {
      "eId": joinExamStore.eId,
      "uId": userStore.uId,
      "content": JSON.stringify(joinExamStore.papersContent)
    }
    const res = await myRequest.post<any, ApiResult>('/exam/judge', submit)
    const isSuccess = res.code == Code.SAVA_OK;
    MyElNotification(res, Code.SAVA_OK, "试卷提交")
    if (isSuccess) {
      await $router.push({name: 'userExam'})
    }
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

const generalDataStore = useGeneralDataStore()
const getGeneralData = () => {
  generalDataStore.getTopicTypeDict();
  // topicTypeDict.value = generalDataStore.topicTypeDict;
}

const mainScrollbar = ref()
const scrollTop = ref(0);
const scrollToBottom = (event) => {
  const tId = event.target.dataset.tId;
  const element = document.querySelector(`.topic-item[data-t-id="${tId}"]`)
  element && element.scrollIntoView({behavior: "smooth"})
};

</script>

<style scoped lang="scss">
.page {
  height: 100vh;
  padding: 15px;

  > .el-container {
    height: 100%;

    .el-aside {
      width: 250px;

      .el-scrollbar {
        padding: 10px 20px;
      }
    }

    .el-header {
      display: flex;
      align-items: center;
      justify-content: space-between;

      .header-left {
        display: flex;
        align-items: center;

        .title {
          margin-left: 20px;
          border-right: 5px solid #3b82f6;

          span {
            padding-right: 15px;
            font-size: 24px;
          }
        }
      }

      .header-right {
        display: flex;
        align-items: center;
        margin-right: 30px;

        .el-button {
          margin-left: 25px;
        }

      }
    }
  }

  .topic-type {
    margin: 20px 0;

    .title {
      font-size: 18px;
      color: #404040FF;
      margin-bottom: 5px;
    }

    .tag-list {
      display: flex;
      flex-wrap: wrap;
      background: #fdfdfdc4;
      border-radius: 15px;
      padding: 10px 5px;

      $tag-size: 30px;

      .el-tag {
        margin: 5px;
        height: $tag-size;
        width: $tag-size;
      }

    }
  }

  .topic-list {
    padding: 10px;
    font-size: 18px;
    color: #606060;

    .header {
      display: flex;
      align-items: center;

      .el-tag {
        margin-left: 10px;
      }
    }

    .topic-item {
      margin-bottom: 30px;

      .choice {
        padding-bottom: 10px;
      }
    }

    .content {
      margin: 30px 15px;
    }

  }


  .my-item {
    margin: 10px;
    border-radius: $base-item-radius;
    background-color: $item-background-color;
  }

  .my-main {
    flex: 1;
  }
}
</style>