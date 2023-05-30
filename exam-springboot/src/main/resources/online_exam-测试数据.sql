/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50543
 Source Host           : localhost:3306
 Source Schema         : online_exam

 Target Server Type    : MySQL
 Target Server Version : 50543
 File Encoding         : 65001

 Date: 31/05/2023 03:24:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `c_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程ID ',
  `u_id` int(11) NULL DEFAULT NULL COMMENT '教师id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `course_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程代码，学生可通过班级码加入班级',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_exist` int(11) NULL DEFAULT NULL COMMENT '用于逻辑删除，默认值1代表存在，0代表删除',
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (8, 9, '22软件专升本1班', '52570384', '2023-05-16 19:32:15', 1);
INSERT INTO `course` VALUES (9, 9, '党校考试', '52571111', '2023-05-26 19:32:15', 1);
INSERT INTO `course` VALUES (10, 9, '测试数据', '52571111', '2023-05-26 19:32:15', 1);
INSERT INTO `course` VALUES (11, 9, '22软件专升本1班', '52570384', '2023-05-16 19:32:15', 0);
INSERT INTO `course` VALUES (12, 9, '123', '52571111', '2023-05-26 19:32:15', 1);
INSERT INTO `course` VALUES (13, 9, '测试数据', '52571111', '2023-05-26 19:32:15', 0);
INSERT INTO `course` VALUES (14, 9, '测试数据', '52570384', '2023-05-16 19:32:15', 1);
INSERT INTO `course` VALUES (15, 9, '测试数据', '52571111', '2023-05-26 19:32:15', 1);
INSERT INTO `course` VALUES (16, 9, '测试数据', '52571111', '2023-05-26 19:32:15', 0);
INSERT INTO `course` VALUES (17, 9, '22软件专升本1班', '52570384', '2023-05-16 19:32:15', 1);
INSERT INTO `course` VALUES (18, 9, '测试数据', '52571111', '2023-05-26 19:32:15', 1);
INSERT INTO `course` VALUES (19, 9, '测试数据', '52571111', '2023-05-26 19:32:15', 1);
INSERT INTO `course` VALUES (20, 9, '22软件专升本1班', '52570384', '2023-05-16 19:32:15', 1);
INSERT INTO `course` VALUES (21, 9, '测试数据', '52571111', '2023-05-26 19:32:15', 1);
INSERT INTO `course` VALUES (22, 9, '测试数据', '52571111', '2023-05-26 19:32:15', 1);
INSERT INTO `course` VALUES (23, 9, '22软件专升本1班', '52570384', '2023-05-16 19:32:15', 1);
INSERT INTO `course` VALUES (24, 9, '测试数据123', '52571111', '2023-05-26 19:32:15', 0);
INSERT INTO `course` VALUES (25, 9, '123', '52571111', '2023-05-26 19:32:15', 0);
INSERT INTO `course` VALUES (26, 9, 'test', '92115221', '2023-05-28 21:52:29', 0);
INSERT INTO `course` VALUES (27, 9, 'test1', '63424299', '2023-05-28 21:52:56', 1);
INSERT INTO `course` VALUES (28, 9, 'test2', '34256349', '2023-05-28 21:53:53', 1);
INSERT INTO `course` VALUES (29, 9, '', '81270355', '2023-05-29 15:40:18', 0);
INSERT INTO `course` VALUES (30, 9, '', '57006164', '2023-05-29 15:41:35', 0);
INSERT INTO `course` VALUES (31, 9, '123', '41425394', '2023-05-29 16:27:24', 1);
INSERT INTO `course` VALUES (32, 9, '123', '88166553', '2023-05-29 17:34:48', 1);

-- ----------------------------
-- Table structure for difficulty
-- ----------------------------
DROP TABLE IF EXISTS `difficulty`;
CREATE TABLE `difficulty`  (
  `d_id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`d_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of difficulty
-- ----------------------------
INSERT INTO `difficulty` VALUES (1, '简单');
INSERT INTO `difficulty` VALUES (2, '中等');
INSERT INTO `difficulty` VALUES (3, '困难');

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `e_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '教师Id',
  `u_id` int(11) NULL DEFAULT NULL COMMENT '教师ID',
  `c_id` int(11) NULL DEFAULT NULL COMMENT '课程ID',
  `p_id` int(11) NULL DEFAULT NULL COMMENT '试卷ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考试名称',
  `start_time` datetime NULL DEFAULT NULL COMMENT '考试开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '考试结束时间',
  `status` int(11) NULL DEFAULT NULL COMMENT '考试状态',
  `is_exist` int(11) NULL DEFAULT NULL COMMENT '逻辑删除，默认值1表示存在，0表示删除',
  PRIMARY KEY (`e_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES (1, 9, 8, 12, '测试几下', '2023-05-31 00:00:00', '2023-06-02 00:00:00', 1, 1);
INSERT INTO `exam` VALUES (2, 9, 9, 1, '测试一下', '2023-05-03 16:00:00', '2023-05-31 16:00:00', 1, 1);
INSERT INTO `exam` VALUES (3, 9, 10, 1, '测试2下', '2023-05-02 16:00:00', '2023-05-03 16:00:00', 0, 1);
INSERT INTO `exam` VALUES (4, 9, 17, 1, '测试4下', '2023-06-08 18:00:00', '2023-07-04 19:00:00', 0, 1);
INSERT INTO `exam` VALUES (5, 9, 8, 22, '2023（上）JavaEE期末考', '2023-06-01 12:00:00', '2023-06-01 14:00:00', 0, 1);

-- ----------------------------
-- Table structure for papers
-- ----------------------------
DROP TABLE IF EXISTS `papers`;
CREATE TABLE `papers`  (
  `p_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '试卷名称',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '整张试卷的内容，格式：{\r\n    \"tId\": {\r\n        \"question\":\"xxx\",\r\n         \"answer\":\"xxxx\"\r\n    },\r\n}',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_exist` int(11) NULL DEFAULT NULL COMMENT '逻辑删除；1代表存在；0代表删除',
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of papers
-- ----------------------------
INSERT INTO `papers` VALUES (1, 9, '手动组卷（测试1）', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"我被修改了\",\"score\":1,\"tId\":1},{\"answer\":\"{\\\"answerContent\\\":\\\"0\\\",\\\"typeId\\\":1}\",\"question\":\"这是一个判断题，难度为中等\",\"score\":1,\"tId\":4},{\"answer\":\"{\\\"answerContent\\\":\\\"0\\\",\\\"typeId\\\":1}\",\"question\":\"我被修改了，我是凑数的\",\"score\":1,\"tId\":5}],\"2\":[],\"3\":[{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\",\\\\\\\"B\\\\\\\",\\\\\\\"D\\\\\\\"]\\\",\\\"typeId\\\":3}\",\"question\":\"这是一个共享的题目\",\"score\":2,\"tId\":3},{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\",\\\\\\\"C\\\\\\\"]\\\",\\\"typeId\\\":3}\",\"question\":\"这是一个多选题\",\"score\":2,\"tId\":6}],\"4\":[],\"5\":[{\"answer\":\"{\\\"answerContent\\\":\\\"呼呼，我是第一题的答案\\\",\\\"typeId\\\":5}\",\"question\":\"这是主观题\",\"score\":5,\"tId\":2}]}', '2023-05-27 17:25:45', 1);
INSERT INTO `papers` VALUES (12, 9, '123', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":14},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"这是一道判断题\",\"score\":1,\"tId\":15},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"das\",\"score\":1,\"tId\":19}],\"2\":[{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\"]\\\",\\\"typeId\\\":2}\",\"question\":\"又是一题单选选题\",\"score\":2,\"tId\":10}],\"3\":[{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\",\\\\\\\"B\\\\\\\"]\\\",\\\"typeId\\\":3}\",\"question\":\"这是一个多选题\",\"score\":2,\"tId\":6}],\"4\":[{\"answer\":\"{\\\"answerContent\\\":\\\"你好呀\\\",\\\"typeId\\\":4}\",\"question\":\"这是一道填空题\",\"score\":2,\"tId\":16}],\"5\":[{\"answer\":\"{\\\"answerContent\\\":\\\"呼呼，我是第一题的答案\\\",\\\"typeId\\\":5}\",\"question\":\"这是主观题\",\"score\":5,\"tId\":2},{\"answer\":\"{\\\"answerContent\\\":\\\"嘿嘿\\\",\\\"typeId\\\":5}\",\"question\":\"啦啦啦\",\"score\":5,\"tId\":17}]}', '2023-05-29 17:40:18', 1);
INSERT INTO `papers` VALUES (15, 9, '随机组卷123', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"0\\\",\\\"typeId\\\":1}\",\"question\":\"我被修改了，我是凑数的\",\"score\":1,\"tId\":5}],\"2\":[],\"3\":[],\"4\":[],\"5\":[{\"answer\":\"{\\\"answerContent\\\":\\\"呼呼，我是第一题的答案\\\",\\\"typeId\\\":5}\",\"question\":\"这是主观题\",\"score\":5,\"tId\":2}]}', '2023-05-27 17:25:57', 0);
INSERT INTO `papers` VALUES (16, 9, '随机组卷', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"我被修改了，我是凑数的\",\"score\":1,\"tId\":5},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":14},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"这是一个判断题，难度为中等\",\"score\":1,\"tId\":4}],\"2\":[],\"3\":[],\"4\":[],\"5\":[{\"answer\":\"{\\\"answerContent\\\":\\\"呼呼，我是第一题的答案\\\",\\\"typeId\\\":5}\",\"question\":\"这是主观题\",\"score\":5,\"tId\":2}]}', '2023-05-28 23:41:20', 1);
INSERT INTO `papers` VALUES (17, 9, '随机组卷', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":11}],\"2\":[],\"3\":[],\"4\":[],\"5\":[{\"answer\":\"{\\\"answerContent\\\":\\\"呼呼，我是第一题的答案\\\",\\\"typeId\\\":5}\",\"question\":\"这是主观题\",\"score\":5,\"tId\":2}]}', '2023-05-29 00:53:43', 1);
INSERT INTO `papers` VALUES (18, 9, '123', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":11}],\"2\":[{\"answer\":\"{\\\"answerContent\\\":\\\"B\\\",\\\"typeId\\\":2}\",\"question\":\"又是一题单选选题\",\"score\":2,\"tId\":9}],\"3\":[],\"4\":[],\"5\":[]}', '2023-05-29 01:31:39', 1);
INSERT INTO `papers` VALUES (19, 9, '123', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"我被修改了，我是凑数的\",\"score\":1,\"tId\":5}],\"2\":[{\"answer\":\"{\\\"answerContent\\\":\\\"B\\\",\\\"typeId\\\":2}\",\"question\":\"又是一题单选选题\",\"score\":2,\"tId\":9}],\"3\":[],\"4\":[],\"5\":[]}', '2023-05-29 02:11:18', 1);
INSERT INTO `papers` VALUES (20, 9, '123', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"这是一个判断题，难度为中等\",\"score\":1,\"tId\":4},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"我被修改了，我是凑数的\",\"score\":1,\"tId\":5}],\"2\":[{\"answer\":\"{\\\"answerContent\\\":\\\"B\\\",\\\"typeId\\\":2}\",\"question\":\"又是一题单选选题\",\"score\":2,\"tId\":9}],\"3\":[{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\",\\\\\\\"B\\\\\\\"]\\\",\\\"typeId\\\":3}\",\"question\":\"这是一个多选题\",\"score\":2,\"tId\":6}],\"4\":[],\"5\":[{\"answer\":\"{\\\"answerContent\\\":\\\"呼呼，我是第一题的答案\\\",\\\"typeId\\\":5}\",\"question\":\"这是主观题\",\"score\":5,\"tId\":2}]}', '2023-05-29 14:58:55', 1);
INSERT INTO `papers` VALUES (21, 9, '测试手动组卷', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"这是一个判断题，难度为中等\",\"score\":1,\"tId\":4},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"我被修改了，我是凑数的\",\"score\":1,\"tId\":5},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":11},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":12},{\"answer\":\"{\\\"answerContent\\\":\\\"0\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":13},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":14}],\"2\":[{\"answer\":\"{\\\"answerContent\\\":\\\"B\\\",\\\"typeId\\\":2}\",\"question\":\"这是一个单选选题\",\"score\":2,\"tId\":8},{\"answer\":\"{\\\"answerContent\\\":\\\"B\\\",\\\"typeId\\\":2}\",\"question\":\"又是一题单选选题\",\"score\":2,\"tId\":9},{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\"]\\\",\\\"typeId\\\":2}\",\"question\":\"又是一题单选选题\",\"score\":2,\"tId\":10}],\"3\":[{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\",\\\\\\\"B\\\\\\\",\\\\\\\"C\\\\\\\"]\\\",\\\"typeId\\\":3}\",\"question\":\"这是一个共享的题目\",\"score\":2,\"tId\":3},{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\",\\\\\\\"B\\\\\\\"]\\\",\\\"typeId\\\":3}\",\"question\":\"这是一个多选题\",\"score\":2,\"tId\":6}],\"4\":[],\"5\":[{\"answer\":\"{\\\"answerContent\\\":\\\"呼呼，我是第一题的答案\\\",\\\"typeId\\\":5}\",\"question\":\"这是主观题\",\"score\":5,\"tId\":2}]}', '2023-05-29 15:00:59', 1);
INSERT INTO `papers` VALUES (22, 9, '测试随机组卷', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":14}],\"2\":[{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\"]\\\",\\\"typeId\\\":2}\",\"question\":\"又是一题单选选题\",\"score\":2,\"tId\":10}],\"3\":[{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\",\\\\\\\"B\\\\\\\"]\\\",\\\"typeId\\\":3}\",\"question\":\"这是一个多选题\",\"score\":2,\"tId\":6}],\"4\":[],\"5\":[]}', '2023-05-29 15:14:46', 1);
INSERT INTO `papers` VALUES (23, 9, '手动组卷啦', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":12},{\"answer\":\"{\\\"answerContent\\\":\\\"0\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":13},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":14}],\"2\":[{\"answer\":\"{\\\"answerContent\\\":\\\"B\\\",\\\"typeId\\\":2}\",\"question\":\"这是一个单选选题\",\"score\":2,\"tId\":8}],\"3\":[{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\",\\\\\\\"B\\\\\\\",\\\\\\\"C\\\\\\\"]\\\",\\\"typeId\\\":3}\",\"question\":\"这是一个共享的题目\",\"score\":2,\"tId\":3}],\"4\":[{\"answer\":\"{\\\"answerContent\\\":\\\"你好呀\\\",\\\"typeId\\\":4}\",\"question\":\"这是一道填空题\",\"score\":2,\"tId\":16}],\"5\":[]}', '2023-05-29 15:22:37', 1);
INSERT INTO `papers` VALUES (24, 9, '测试', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"这是一个判断题，难度为中等\",\"score\":1,\"tId\":4},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"我被修改了，我是凑数的\",\"score\":1,\"tId\":5},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":11},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":12},{\"answer\":\"{\\\"answerContent\\\":\\\"0\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":13},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":14},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"这是一道判断题\",\"score\":1,\"tId\":15}],\"2\":[{\"answer\":\"{\\\"answerContent\\\":\\\"B\\\",\\\"typeId\\\":2}\",\"question\":\"这是一个单选选题\",\"score\":2,\"tId\":8},{\"answer\":\"{\\\"answerContent\\\":\\\"B\\\",\\\"typeId\\\":2}\",\"question\":\"又是一题单选选题\",\"score\":2,\"tId\":9},{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\"]\\\",\\\"typeId\\\":2}\",\"question\":\"又是一题单选选题\",\"score\":2,\"tId\":10}],\"3\":[{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\",\\\\\\\"B\\\\\\\",\\\\\\\"C\\\\\\\"]\\\",\\\"typeId\\\":3}\",\"question\":\"这是一个共享的题目\",\"score\":2,\"tId\":3},{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\",\\\\\\\"B\\\\\\\"]\\\",\\\"typeId\\\":3}\",\"question\":\"这是一个多选题\",\"score\":2,\"tId\":6}],\"4\":[{\"answer\":\"{\\\"answerContent\\\":\\\"你好呀\\\",\\\"typeId\\\":4}\",\"question\":\"这是一道填空题\",\"score\":2,\"tId\":16}],\"5\":[{\"answer\":\"{\\\"answerContent\\\":\\\"呼呼，我是第一题的答案\\\",\\\"typeId\\\":5}\",\"question\":\"这是主观题\",\"score\":5,\"tId\":2},{\"answer\":\"{\\\"answerContent\\\":\\\"嘿嘿\\\",\\\"typeId\\\":5}\",\"question\":\"啦啦啦\",\"score\":5,\"tId\":17}]}', '2023-05-29 15:22:57', 1);
INSERT INTO `papers` VALUES (25, 9, '1233123', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":11}],\"2\":[{\"answer\":\"{\\\"answerContent\\\":\\\"B\\\",\\\"typeId\\\":2}\",\"question\":\"又是一题单选选题\",\"score\":2,\"tId\":9},{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\"]\\\",\\\"typeId\\\":2}\",\"question\":\"又是一题单选选题\",\"score\":2,\"tId\":10}],\"3\":[{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\",\\\\\\\"B\\\\\\\"]\\\",\\\"typeId\\\":3}\",\"question\":\"这是一个多选题\",\"score\":2,\"tId\":6}],\"4\":[],\"5\":[]}', '2023-05-29 15:25:45', 1);
INSERT INTO `papers` VALUES (26, 9, '测试手动组卷1', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"这是一个判断题，难度为中等\",\"score\":1,\"tId\":4},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"我被修改了，我是凑数的\",\"score\":1,\"tId\":5},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":11},{\"answer\":\"{\\\"answerContent\\\":\\\"0\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":13}],\"2\":[{\"answer\":\"{\\\"answerContent\\\":\\\"B\\\",\\\"typeId\\\":2}\",\"question\":\"又是一题单选选题\",\"score\":1,\"tId\":9}],\"3\":[{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\",\\\\\\\"B\\\\\\\"]\\\",\\\"typeId\\\":3}\",\"question\":\"这是一个多选题\",\"score\":2,\"tId\":6}],\"4\":[],\"5\":[{\"answer\":\"{\\\"answerContent\\\":\\\"呼呼，我是第一题的答案\\\",\\\"typeId\\\":5}\",\"question\":\"这是主观题\",\"score\":5,\"tId\":2}]}', '2023-05-29 16:09:22', 1);
INSERT INTO `papers` VALUES (27, 9, '随机组卷', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":14}],\"2\":[{\"answer\":\"{\\\"answerContent\\\":\\\"B\\\",\\\"typeId\\\":2}\",\"question\":\"这是一个单选选题\",\"score\":2,\"tId\":8}],\"3\":[{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\",\\\\\\\"B\\\\\\\"]\\\",\\\"typeId\\\":3}\",\"question\":\"这是一个多选题\",\"score\":2,\"tId\":6}],\"4\":[],\"5\":[]}', '2023-05-29 16:10:17', 1);
INSERT INTO `papers` VALUES (28, 9, '123', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"这是一个判断题，难度为中等\",\"score\":1,\"tId\":4},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"我被修改了，我是凑数的\",\"score\":1,\"tId\":5},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":11},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":12},{\"answer\":\"{\\\"answerContent\\\":\\\"0\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":13},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":14},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"这是一道判断题\",\"score\":1,\"tId\":15}],\"2\":[{\"answer\":\"{\\\"answerContent\\\":\\\"B\\\",\\\"typeId\\\":2}\",\"question\":\"这是一个单选选题\",\"score\":2,\"tId\":8},{\"answer\":\"{\\\"answerContent\\\":\\\"B\\\",\\\"typeId\\\":2}\",\"question\":\"又是一题单选选题\",\"score\":2,\"tId\":9},{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\"]\\\",\\\"typeId\\\":2}\",\"question\":\"又是一题单选选题\",\"score\":2,\"tId\":10}],\"3\":[{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\",\\\\\\\"B\\\\\\\",\\\\\\\"C\\\\\\\"]\\\",\\\"typeId\\\":3}\",\"question\":\"这是一个共享的题目\",\"score\":2,\"tId\":3},{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\",\\\\\\\"B\\\\\\\"]\\\",\\\"typeId\\\":3}\",\"question\":\"这是一个多选题\",\"score\":2,\"tId\":6},{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\",\\\\\\\"B\\\\\\\",\\\\\\\"C\\\\\\\"]\\\",\\\"typeId\\\":3}\",\"question\":\"这是多选题\",\"score\":2,\"tId\":18}],\"4\":[{\"answer\":\"{\\\"answerContent\\\":\\\"你好呀\\\",\\\"typeId\\\":4}\",\"question\":\"这是一道填空题\",\"score\":2,\"tId\":16}],\"5\":[{\"answer\":\"{\\\"answerContent\\\":\\\"呼呼，我是第一题的答案\\\",\\\"typeId\\\":5}\",\"question\":\"这是主观题\",\"score\":5,\"tId\":2},{\"answer\":\"{\\\"answerContent\\\":\\\"嘿嘿\\\",\\\"typeId\\\":5}\",\"question\":\"啦啦啦\",\"score\":5,\"tId\":17}]}', '2023-05-29 17:30:24', 1);
INSERT INTO `papers` VALUES (29, 9, '123', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"我被修改了，我是凑数的\",\"score\":1,\"tId\":5}],\"2\":[{\"answer\":\"{\\\"answerContent\\\":\\\"B\\\",\\\"typeId\\\":2}\",\"question\":\"又是一题单选选题\",\"score\":2,\"tId\":9}],\"3\":[{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\",\\\\\\\"B\\\\\\\"]\\\",\\\"typeId\\\":3}\",\"question\":\"这是一个多选题\",\"score\":2,\"tId\":6}],\"4\":[],\"5\":[{\"answer\":\"{\\\"answerContent\\\":\\\"呼呼，我是第一题的答案\\\",\\\"typeId\\\":5}\",\"question\":\"这是主观题\",\"score\":5,\"tId\":2}]}', '2023-05-29 17:35:52', 1);
INSERT INTO `papers` VALUES (30, 9, '123', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":12},{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"123\",\"score\":1,\"tId\":14}],\"2\":[],\"3\":[],\"4\":[{\"answer\":\"{\\\"answerContent\\\":\\\"你好呀\\\",\\\"typeId\\\":4}\",\"question\":\"这是一道填空题\",\"score\":2,\"tId\":16}],\"5\":[]}', '2023-05-29 17:39:46', 1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `r_id` int(11) NOT NULL COMMENT '角色ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`r_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '学生');
INSERT INTO `role` VALUES (2, '教师');

-- ----------------------------
-- Table structure for student_course
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course`  (
  `uc_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NULL DEFAULT NULL COMMENT '学生ID',
  `c_id` int(11) NULL DEFAULT NULL COMMENT '课程ID',
  PRIMARY KEY (`uc_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student_course
-- ----------------------------
INSERT INTO `student_course` VALUES (1, 15, 8);
INSERT INTO `student_course` VALUES (8, 15, 17);
INSERT INTO `student_course` VALUES (9, 18, 17);
INSERT INTO `student_course` VALUES (10, 19, 17);
INSERT INTO `student_course` VALUES (11, 15, 28);

-- ----------------------------
-- Table structure for student_exam
-- ----------------------------
DROP TABLE IF EXISTS `student_exam`;
CREATE TABLE `student_exam`  (
  `se_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NULL DEFAULT NULL COMMENT '学生ID',
  `e_id` int(11) NULL DEFAULT NULL COMMENT '考试ID',
  `right_student_answer` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '整张试卷的正确答案，可以存储成Json格式的字符串',
  `create_time` datetime NULL DEFAULT NULL COMMENT '提交试卷时间',
  PRIMARY KEY (`se_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student_exam
-- ----------------------------
INSERT INTO `student_exam` VALUES (4, 15, 1, '{\"totalScore\":3.0,\"content\":[{\"score\":1,\"rightAnswer\":\"1\",\"answer\":{\"answerContent\":1,\"typeId\":1},\"question\":\"123\",\"getScore\":1.0,\"tId\":14},{\"score\":1,\"rightAnswer\":\"1\",\"answer\":{\"answerContent\":0,\"typeId\":1},\"question\":\"这是一道判断题\",\"getScore\":0.0,\"tId\":15},{\"score\":1,\"rightAnswer\":\"1\",\"answer\":{\"answerContent\":1,\"typeId\":1},\"question\":\"das\",\"getScore\":1.0,\"tId\":19},{\"score\":2,\"rightAnswer\":\"[\\\"A\\\"]\",\"answer\":{\"answerContent\":\"B\",\"typeId\":2},\"question\":\"又是一题单选选题\",\"getScore\":0.0,\"tId\":10},{\"score\":2,\"rightAnswer\":\"[\\\"A\\\",\\\"B\\\"]\",\"answer\":{\"answerContent\":[\"C\"],\"typeId\":3},\"question\":\"这是一个多选题\",\"getScore\":1.0,\"tId\":6},{\"score\":2,\"rightAnswer\":\"你好呀\",\"answer\":{\"answerContent\":\"123\",\"typeId\":4},\"question\":\"这是一道填空题\",\"getScore\":0.0,\"tId\":16},{\"score\":5,\"rightAnswer\":\"呼呼，我是第一题的答案\",\"answer\":{\"answerContent\":\"123\",\"typeId\":5},\"question\":\"这是主观题\",\"getScore\":0.0,\"tId\":2},{\"score\":5,\"rightAnswer\":\"嘿嘿\",\"answer\":{\"answerContent\":\"123\",\"typeId\":5},\"question\":\"啦啦啦\",\"getScore\":0.0,\"tId\":17}]}', '2023-05-31 02:13:07');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NULL DEFAULT NULL COMMENT '教师ID，标签可由教师管理，0代表所有教师可共享的标签',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (1, 0, '其他');
INSERT INTO `tag` VALUES (2, 9, '政治');

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic`  (
  `t_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '题目ID',
  `u_id` int(11) NULL DEFAULT NULL COMMENT '教师ID',
  `type_id` int(11) NULL DEFAULT NULL COMMENT '题目类型：判断、单选、多选、填空、主观题',
  `tag_id` int(11) NULL DEFAULT NULL COMMENT '题目标签：Java、Python等等',
  `difficulty_id` int(11) NULL DEFAULT NULL COMMENT '难度类型：简单题、中等题、困难题',
  `question` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '问题',
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '答案，格式：[\"A\",\"B\"]||[\"填空题和主观题的答案\"]，\r\n判断题[1|0]，1代表√，0代表×',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态：是否与其他人共享题目，1代表共享，0代表不共享',
  `is_exist` int(11) NULL DEFAULT NULL COMMENT '逻辑删除，默认值1代表存在，0代表删除',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES (1, 9, 1, 1, 1, '我被修改了', '{\"answerContent\":\"1\",\"typeId\":1}', 1, 0, '2023-05-16 22:49:07');
INSERT INTO `topic` VALUES (2, 9, 5, 1, 1, '这是主观题', '{\"answerContent\":\"呼呼，我是第一题的答案\",\"typeId\":5}', 1, 1, '2023-05-16 23:45:07');
INSERT INTO `topic` VALUES (3, 11, 3, 2, 2, '这是一个共享的题目', '{\"answerContent\":\"[\\\"A\\\",\\\"B\\\"]\",\"typeId\":3}', 1, 1, '2023-05-16 23:49:53');
INSERT INTO `topic` VALUES (4, 11, 1, 1, 2, '这是一个判断题，难度为中等', '{\"answerContent\":\"1\",\"typeId\":1}', 1, 1, '2023-05-17 15:51:47');
INSERT INTO `topic` VALUES (5, 9, 1, 1, 1, '我被修改了，我是凑数的', '{\"answerContent\":\"1\",\"typeId\":1}', 1, 1, '2023-05-18 22:49:07');
INSERT INTO `topic` VALUES (6, 11, 3, 1, 1, '这是一个多选题', '{\"answerContent\":\"[\\\"A\\\",\\\"B\\\"]\",\"typeId\":3}', 1, 1, '2023-05-18 12:39:39');
INSERT INTO `topic` VALUES (8, 11, 2, 1, 1, '这是一个单选选题', '{\"answerContent\":\"B\",\"typeId\":2}', 1, 1, '2023-05-27 01:28:27');
INSERT INTO `topic` VALUES (9, 11, 2, 1, 1, '又是一题单选选题', '{\"answerContent\":\"B\",\"typeId\":2}', 1, 1, '2023-05-27 01:30:46');
INSERT INTO `topic` VALUES (10, 11, 2, 1, 1, '又是一题单选选题', '{\"answerContent\":\"A\",\"typeId\":2}', 1, 1, '2023-05-28 23:26:55');
INSERT INTO `topic` VALUES (11, 9, 1, 1, 1, '123', '{\"answerContent\":\"1\",\"typeId\":1}', 1, 1, '2023-05-28 23:27:46');
INSERT INTO `topic` VALUES (12, 9, 1, 2, 3, '123', '{\"answerContent\":\"1\",\"typeId\":1}', 1, 1, '2023-05-28 23:28:43');
INSERT INTO `topic` VALUES (13, 9, 1, 2, 2, '123', '{\"answerContent\":\"0\",\"typeId\":1}', 1, 1, '2023-05-28 23:30:32');
INSERT INTO `topic` VALUES (14, 9, 1, 1, 1, '123', '{\"answerContent\":\"1\",\"typeId\":1}', 1, 1, '2023-05-28 23:37:13');
INSERT INTO `topic` VALUES (15, 9, 1, 1, 1, '这是一道判断题', '{\"answerContent\":\"1\",\"typeId\":1}', 1, 1, '2023-05-29 15:13:49');
INSERT INTO `topic` VALUES (16, 9, 4, 1, 1, '这是一道填空题', '{\"answerContent\":\"你好呀\",\"typeId\":4}', 0, 1, '2023-05-29 15:18:06');
INSERT INTO `topic` VALUES (17, 9, 5, 1, 2, '啦啦啦', '{\"answerContent\":\"嘿嘿\",\"typeId\":5}', 0, 1, '2023-05-29 15:20:50');
INSERT INTO `topic` VALUES (18, 9, 3, 1, 2, '这是多选题', '{\"answerContent\":\"[\\\"A\\\",\\\"B\\\",\\\"C\\\"]\",\"typeId\":3}', 1, 1, '2023-05-29 16:08:47');
INSERT INTO `topic` VALUES (19, 9, 1, 1, 2, 'das', '{\"answerContent\":\"1\",\"typeId\":1}', 1, 1, '2023-05-29 17:35:06');
INSERT INTO `topic` VALUES (20, 9, 3, 1, 2, '这是一道共享的多选题', '{\"answerContent\":\"[\\\"A\\\",\\\"B\\\",\\\"C\\\"]\",\"typeId\":3}', 1, 1, '2023-05-29 20:17:12');
INSERT INTO `topic` VALUES (21, 9, 2, 1, 2, '测试答案是不是【】', '{\"answerContent\":\"A\",\"typeId\":2}', 1, 1, '2023-05-31 02:05:25');

-- ----------------------------
-- Table structure for topic_type
-- ----------------------------
DROP TABLE IF EXISTS `topic_type`;
CREATE TABLE `topic_type`  (
  `type_id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of topic_type
-- ----------------------------
INSERT INTO `topic_type` VALUES (1, '判断题');
INSERT INTO `topic_type` VALUES (2, '单选题');
INSERT INTO `topic_type` VALUES (3, '多选题');
INSERT INTO `topic_type` VALUES (4, '填空题');
INSERT INTO `topic_type` VALUES (5, '主观题');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `r_id` int(11) NOT NULL COMMENT '角色ID',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `register_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`u_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (3, 2, 'zk', '$2a$10$t.wOpQEb5lcBwEewp.PzBunA.lS2fZGTgYiI4b099UZHaGgCGQnrm', '1206796814@qq.com', '2023-05-16 15:39:02');
INSERT INTO `user` VALUES (9, 2, 'T-JQ', '$2a$10$cauSOxYgNk7VC6iN/6RrcO/GGhGZdAzZxLupK6.KKTEKObaX75Jna', 'z13706035322@163.com', '2023-05-16 13:35:32');
INSERT INTO `user` VALUES (11, 2, 'zxy', '$2a$10$O/RNV2QiKReJ2iCHwOKBeeF0Nv3SRHT9cq7NfvtQfmyrm9dDWY5ja', '2673492930@qq.com', '2023-05-16 23:47:57');
INSERT INTO `user` VALUES (15, 1, 'S-JQ', '$2a$10$mMMCsky1DR2akheQuXfyBu1Gt5uz7mCe/gYvft8PW.zXy2br88hDq', '2410360091@qq.com', '2023-05-25 12:27:06');
INSERT INTO `user` VALUES (20, 1, 'sxl', '$2a$10$6Rp4yyYSnrWTECP0gouwtOrGzGAKELRqWrHsKeSce7DL2cRD.2qxu', '2608055142@qq.com', '2023-05-29 17:17:39');

SET FOREIGN_KEY_CHECKS = 1;
