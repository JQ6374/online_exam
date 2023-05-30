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
            <span>{{ examName }}</span>
          </div>
        </div>
        <div class="header-right">
          <el-button
              type="primary"
              round
          >
            提交试卷
          </el-button>
        </div>
      </el-header>
      <el-container class="my-main">
        <el-aside class="my-item">
          <el-scrollbar ref="mainScrollbar" height="560px">
            <div class="topic-type"
                 v-for="(content,topicType, index) in cleanContent"
                 v-show="content && content.length != 0"
                 :key="topicType"
            >
              <div class="title">
                <span>{{ index + 1 }}、{{ generalDataStore.topicTypeDict[topicType] }}</span>
              </div>
              <div class="tag-list">
                <el-tag
                    v-for="(topic, index) in paperContent[topicType]"
                    :key="topic.tId"
                    :data-index="topic.tId"
                    @click="handleTag"
                >
                  <el-icon>
                    <!--                    <SemiSelect/>-->
                    <!--                    <Select/>-->
                    <!--                    <CloseBold/>-->
                    {{ index + 1 }}
                  </el-icon>
                </el-tag>
              </div>
            </div>
          </el-scrollbar>
        </el-aside>
        <el-main class="my-item">
          <el-scrollbar height="555px" ref="mainScrollbar">
            <div class="topic-list"
                 v-for="(content,topicType,topicTypeIndex) in cleanContent"
                 v-show="content && content.length != 0"
                 :key="topicType"
            >
              <div class="topic-item"
                   v-for="(topic, index) in content"
                   :key="topic.tId"
                   :ref="`item-${topic.tId}`"
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
                    <el-checkbox
                        v-for="item in typeSelect.choice"
                        v-model="topic.answer.answerContent"
                        :label="item.label"
                        border/>
                  </div>
                </div>
                <el-divider/>
              </div>
            </div>
            <div id="ceshi">123</div>
          </el-scrollbar>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import Logo from "@/components/Logo/index.vue";
import {onMounted, ref} from "vue";
import {useGeneralDataStore} from "@/store/modules/generalData.ts";

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

const props = defineProps({
  examName: {
    type: String,
    default: '考试标题'
  },
  paperContent: {
    type: Object,
    default: {
      "1": [{
        "answer": "{\"answerContent\":\"1\",\"typeId\":1}",
        "question": "我被修改了",
        "score": 1,
        "tId": 1
      }, {
        "answer": "{\"answerContent\":\"0\",\"typeId\":1}",
        "question": "这是一个判断题，难度为中等",
        "score": 1,
        "tId": 4
      }, {
        "answer": "{\"answerContent\":\"0\",\"typeId\":1}",
        "question": "我被修改了，我是凑数的",
        "score": 1,
        "tId": 5
      }],
      "2": [],
      "3": [{
        "answer": "{\"answerContent\":\"[\\\"A\\\",\\\"B\\\",\\\"D\\\"]\",\"typeId\":3}",
        "question": "这是一个共享的题目",
        "score": 2,
        "tId": 3
      }, {
        "answer": "{\"answerContent\":\"[\\\"A\\\",\\\"C\\\"]\",\"typeId\":3}",
        "question": "这是一个多选题",
        "score": 2,
        "tId": 6
      }],
      "4": [],
      "5": [{
        "answer": "{\"answerContent\":\"呼呼，我是第一题的答案\",\"typeId\":5}",
        "question": "这是主观题",
        "score": 5,
        "tId": 2
      }]
    }
  }
})
// props.paperContent.
const cleanContent = Object.fromEntries(
    Object.entries(props.paperContent)
        .filter(([k, v]) => v && (v.length !== 0))
);

onMounted(() => {
  getGeneralData();
})
const generalDataStore = useGeneralDataStore()
const getGeneralData = () => {
  generalDataStore.getTopicTypeDict();
  // topicTypeDict.value = generalDataStore.topicTypeDict;
}

const mainScrollbar = ref()
const handleTag = (event) => {
  const tId = event.target.dataset.index;
  const el = document.querySelector(`[data-t-id="${tId}"]`);
  console.log(el)
  console.log(mainScrollbar)
  // mainScrollbar.value.scrollbar.scrollToElement(el);
  // console.log(event)
  // console.log(mainScrollbar)
  // mainScrollbar.value.scrollTo(0)
}
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
        margin-left: 25px;
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