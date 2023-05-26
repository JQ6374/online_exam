// @ts-ignore
// @ts-ignore
export const constantRouter = [
  {
    path: '/',
    // @ts-ignore
    redirect: '/login',
    name: 'gen',
    meta: {
      title: 'gen',
      hidden: true
    }
  },
  {
    path: '/login',
    // @ts-ignore 路由懒加载
    component: () => import('@/views/user/Login.vue'),
    name: 'login',
    meta: {
      title: '登录',
      hidden: true
    }
  },
  {
    path: '/register',
    // @ts-ignore 路由懒加载
    component: () => import('@/views/user/Register.vue'),
    name: 'register',
    meta: {
      title: '注册',
      hidden: true
    }
  },
  {
    path: '/admin',
    name: 'admin',
    // @ts-ignore
    component: () => import('@/views/admin/index.vue'),
    redirect: {name: 'home'},
    meta: {
      title: '管理员页面',
      hidden: true
    },
    children: [
      {
        path: 'home',
        // @ts-ignore
        component: () => import('@/views/admin/home/Index.vue'),
        meta: {
          title: '主页',
          hidden: false,
          icon: 'HomeFilled'
        },
        name: 'home'
      },
      {
        path: 'user',
        name: 'user',
        meta: {
          title: '用户管理',
          hidden: false,
          icon: 'UserFilled',
        },
        children: [
          {
            path: 'student',
            // @ts-ignore
            component: () => import('@/views/admin/user/Student.vue'),
            meta: {
              title: '学生列表',
              hidden: false,
            },
            name: 'student'
          },
        ]
      },
      {
        path: 'courseTeacher',
        name: 'courseTeacher',
        meta: {
          title: '课程管理',
          hidden: false,
          icon: 'Collection'
        },
        children: [
          {
            path: 'courseList',
            name: 'courseList',
            // @ts-ignore
            component: () => import('@/views/admin/course/List.vue'),
            meta: {
              title: '课程列表',
              hidden: false,
            },
          },
          {
            path: 'courseCreate',
            name: 'courseCreate',
            // @ts-ignore
            component: () => import('@/views/admin/course/Create.vue'),
            meta: {
              title: '课程创建',
              hidden: false,
            },
          },
        ]
      },
      {
        path: 'topic',
        name: 'topic',
        meta: {
          title: '题库管理',
          hidden: false,
          icon: 'OfficeBuilding'
        },
        children: [
          {
            path: 'topicList',
            name: 'topicList',
            // @ts-ignore
            component: () => import('@/views/admin/topic/List.vue'),
            meta: {
              title: '题目列表',
              hidden: false,
            }
          },
          {
            path: 'topicTrueFalse',
            name: 'topicTrueFalse',
            // @ts-ignore
            component: () => import('@/views/admin/topic/TrueFalse.vue'),
            meta: {
              title: '判断题',
              hidden: false,
            }
          },
          {
            path: 'topicSingleChoice',
            name: 'topicSingleChoice',
            // @ts-ignore
            component: () => import('@/views/admin/topic/SingleChoice.vue'),
            meta: {
              title: '单选题',
              hidden: false,
            }
          },
          {
            path: 'topicMultipleChoice',
            name: 'topicMultipleChoice',
            // @ts-ignore
            component: () => import('@/views/admin/topic/MultipleChoice.vue'),
            meta: {
              title: '多选题',
              hidden: false,
            },
          },
          {
            path: 'topicGapFilling',
            name: 'topicGapFilling',
            // @ts-ignore
            component: () => import('@/views/admin/topic/GapFilling.vue'),
            meta: {
              title: '填空题',
              hidden: false,
            }
          },
          {
            path: 'topicShortAnswer',
            name: 'topicShortAnswer',
            // @ts-ignore
            component: () => import('@/views/admin/topic/ShortAnswer.vue'),
            meta: {
              title: '简答题',
              hidden: false,
            }
          },
        ]
      },
      {
        path: 'papers',
        name: 'papers',
        meta: {
          title: '试卷管理',
          hidden: false,
          icon: 'Reading'
        },
        children: [
          {
            path: 'papersList',
            name: 'papersList',
            // @ts-ignore
            component: () => import('@/views/admin/papers/List.vue'),
            meta: {
              title: '试卷列表',
              hidden: false,
            },
          },
          {
            path: 'papersCreate',
            name: 'papersCreate',
            // @ts-ignore
            component: () => import('@/views/admin/papers/Create.vue'),
            meta: {
              title: '试卷创建',
              hidden: false,
            },
          },
        ]
      },
      {
        path: 'exam',
        name: 'exam',
        meta: {
          title: '考试管理',
          hidden: false,
          icon: 'DataLine'
        },
        children: [
          {
            path: 'examList',
            name: 'examList',
            // @ts-ignore
            component: () => import('@/views/admin/exam/List.vue'),
            meta: {
              title: '考试列表',
              hidden: false,
            },
          },
          {
            path: 'examCreate',
            name: 'examCreate',
            // @ts-ignore
            component: () => import('@/views/admin/exam/Create.vue'),
            meta: {
              title: '考试创建',
              hidden: false,
            },
          },
        ]
      },
    ]
  },
  {
    path: '/index',
    name: 'index',
    redirect: {name: 'userHome'},
    // @ts-ignore
    component: () => import('@/views/admin/index.vue'),
    meta: {
      title: '用户页面',
      hidden: true
    },
    children: [
      {
        path: 'userHome',
        name: 'userHome',
        // @ts-ignore
        component: () => import('@/views/index/home/index.vue'),
        meta: {
          title: '主页',
          hidden: false,
          icon: 'HomeFilled'
        },
      },
      {
        path: 'userCourse',
        name: 'userCourse',
        // @ts-ignore
        component: () => import('@/views/index/course/index.vue'),
        meta: {
          title: '课程中心',
          hidden: false,
          icon: 'Collection'
        },
      },
      {
        path: 'userExam',
        name: 'userExam',
        // @ts-ignore
        component: () => import('@/views/index/exam/index.vue'),
        meta: {
          title: '考试中心',
          hidden: false,
          icon: 'DataLine'
        },
      }
    ]
  },
  {
    path: '/test',
    // @ts-ignore
    component: () => import('@/components/Test.vue'),
    name: 'test',
    meta: {
      title: '测试页',
      hidden: true
    }
  },
  {
    path: '/404',
    // @ts-ignore
    component: () => import('@/views/404/index.vue'),
    name: '404',
    meta: {
      title: '404',
      hidden: true
    }
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/404',
    name: 'Any',
    meta: {
      title: 'Any',
      hidden: true
    }
  },
]