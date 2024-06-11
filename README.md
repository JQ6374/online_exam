# online_exam

#### 介绍
基于SpringBoot+Vue3前后分离项目的在线考试系统；

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

#### 测试账号
> 教师：
>  + 账号：teacher
>  + 密码：123456
>
> 学生
>  + 账号：student
>  + 密码：123456


#### 安装教程

> 前端采用pnpm包管理工具、后端采用maven管理依赖

+ 启动后端服务（确保安装MySQL以及Redis）
  + 打开exam_springboot项目
  + 利用maven下载依赖
  + 导入数据库脚本`online_exam.sql`
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

> 前后端代码书写风格，分别参考了黑马程序员以及尚硅谷的教学视频
>
> 链接地址：
>
> + https://www.bilibili.com/video/BV1Xh411V7b5/?p=51&spm_id_from=333.1007.top_right_bar_window_history.content.click
> + https://www.bilibili.com/video/BV1Fi4y1S7ix/?spm_id_from=333.1007.top_right_bar_window_custom_collection.content.click

> 取消了主观题判分功能，因为需要依赖哈工大的同义词词林，依赖文件太大不好上传与部署；
>
> 若需要主观题判分功能，可以从百度网盘自行下载依赖，解压`hanlp-dir`并将`hanlp.properties`文件放在`resources`目录下
>
> + `resources/hanlp.properties`并将第3行`root=`的文件地址修改成`hanlp-dir`文件夹的本地绝对地址(文档中说明就是要求绝对地址)
>
> 链接：https://pan.baidu.com/s/15G9D7dPxZGFdZDtyAsTVCQ 
> 提取码：9x0o

#### 参与贡献者

1.  **Z**
2.  **GGGx**