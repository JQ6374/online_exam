# online_exam

#### 介绍

基于SpringBoot+Vue3前后分离项目的在线考试系统；

PC在线地址：http://47.120.40.41/#/login

#### 软件架构

前端：

+ vue3
+ typescript
+ vue-router
+ pinia
+ vite

后端：

+ SpringBoot 2.7
+ SpringSecurity
+ Mybatis
+ MySQL 5.7
+ Redis

#### 安装教程

> 前端采用npnm包管理工具、后端采用maven管理依赖

+ 启动后端服务（确保安装MySQL以及Redis）

  + 打开exam_springboot项目
  + 利用maven下载依赖
  + 修改配置`exam-springboot/src/main/resources/application.yml`
    + 数据库连接
    + 邮箱验证信息
      + 修改`username以及password`（需要开通邮箱权限，可自行百度）
  + 运行`com/zz/Application.java`即可

+ 启动前端服务

  + 打开exam_vue项目，执行如下指令：

    ```shell
    # 安装包依赖
    pnpm install
    # 启动服务
    pnpm run dev
    ```

#### 说明补充

> 取消了主观题判分功能，因为需要依赖哈工大的同义词词林，依赖文件太大不好上传与部署；
>
> 若需要主观题判分功能，可以从百度网盘自行下载依赖，并将文件放在`resources`目录下
>
> + `resources/hanlp.properties`并将第3行`root=`的文件地址修改，本地的绝对地址(文档中说明就是要求绝对地址)
> + `resources/hanlp-dir`
>
> 链接：https://pan.baidu.com/s/15G9D7dPxZGFdZDtyAsTVCQ 
> 提取码：9x0o

#### 参与贡献者

1.  **Z**
2.  **GGGx**


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)