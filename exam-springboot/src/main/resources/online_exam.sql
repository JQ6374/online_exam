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

 Date: 28/05/2023 21:56:32
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
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES (1, 9, 1, 1, '1', '2023-05-23 23:25:12', '2023-05-23 23:25:14', 1, 1);
INSERT INTO `exam` VALUES (2, 9, 2, 1, '测试一下', '2023-05-16 09:30:00', '2023-05-16 11:30:00', 1, 1);
INSERT INTO `exam` VALUES (3, 9, 2, 1, '测试2下', '2023-05-16 09:30:00', '2023-05-16 11:30:00', 1, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of papers
-- ----------------------------
INSERT INTO `papers` VALUES (12, 9, '手动组卷（测试）', '{\"1\":[{\"answer\":\"{\\\"answer\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"我被修改了\",\"score\":1,\"tId\":1},{\"answer\":\"{\\\"answer\\\":\\\"0\\\",\\\"typeId\\\":1}\",\"question\":\"这是一个判断题，难度为中等\",\"score\":1,\"tId\":4},{\"answer\":\"{\\\"answer\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"我被修改了，我是凑数的\",\"score\":1,\"tId\":5}],\"2\":[],\"3\":[{\"answer\":\"{\\\"answer\\\":\\\"[\'A\', \'B\', \'C\']\\\",\\\"typeId\\\":3}\",\"question\":\"这是一个共享的题目\",\"score\":2,\"tId\":3},{\"answer\":\"{\\\"answer\\\":\\\"[\'A\', \'B\']\\\",\\\"typeId\\\":3}\",\"question\":\"这是一个多选题\",\"score\":2,\"tId\":6}],\"4\":[],\"5\":[{\"answer\":\"{\\\"answer\\\":\\\"呼呼，我是第一题的答案\\\",\\\"typeId\\\":5}\",\"question\":\"这是主观题\",\"score\":5,\"tId\":2}]}', '2023-05-19 18:27:54', 1);
INSERT INTO `papers` VALUES (13, 9, '随机组卷', '{\"1\":[{\"answer\":\"{\\\"answer\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"我被修改了\",\"score\":1,\"tId\":1}],\"2\":[],\"3\":[],\"4\":[],\"5\":[{\"answer\":\"{\\\"answer\\\":\\\"呼呼，我是第一题的答案\\\",\\\"typeId\\\":5}\",\"question\":\"这是主观题\",\"score\":5,\"tId\":2}]}', '2023-05-19 18:30:05', 1);
INSERT INTO `papers` VALUES (14, 9, '手动组卷（测试1）', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"我被修改了\",\"score\":1,\"tId\":1},{\"answer\":\"{\\\"answerContent\\\":\\\"0\\\",\\\"typeId\\\":1}\",\"question\":\"这是一个判断题，难度为中等\",\"score\":1,\"tId\":4},{\"answer\":\"{\\\"answerContent\\\":\\\"0\\\",\\\"typeId\\\":1}\",\"question\":\"我被修改了，我是凑数的\",\"score\":1,\"tId\":5}],\"2\":[],\"3\":[{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\",\\\\\\\"B\\\\\\\",\\\\\\\"D\\\\\\\"]\\\",\\\"typeId\\\":3}\",\"question\":\"这是一个共享的题目\",\"score\":2,\"tId\":3},{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\",\\\\\\\"C\\\\\\\"]\\\",\\\"typeId\\\":3}\",\"question\":\"这是一个多选题\",\"score\":2,\"tId\":6}],\"4\":[],\"5\":[{\"answer\":\"{\\\"answerContent\\\":\\\"呼呼，我是第一题的答案\\\",\\\"typeId\\\":5}\",\"question\":\"这是主观题\",\"score\":5,\"tId\":2}]}', '2023-05-27 17:25:45', 1);
INSERT INTO `papers` VALUES (15, 9, '随机组卷123', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"0\\\",\\\"typeId\\\":1}\",\"question\":\"我被修改了，我是凑数的\",\"score\":1,\"tId\":5}],\"2\":[],\"3\":[],\"4\":[],\"5\":[{\"answer\":\"{\\\"answerContent\\\":\\\"呼呼，我是第一题的答案\\\",\\\"typeId\\\":5}\",\"question\":\"这是主观题\",\"score\":5,\"tId\":2}]}', '2023-05-27 17:25:57', 0);

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
INSERT INTO `role` VALUES (1, '教师');
INSERT INTO `role` VALUES (2, '学生');

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
INSERT INTO `student_course` VALUES (1, 16, 17);
INSERT INTO `student_course` VALUES (8, 17, 17);
INSERT INTO `student_course` VALUES (9, 18, 17);
INSERT INTO `student_course` VALUES (10, 19, 17);

-- ----------------------------
-- Table structure for student_exam
-- ----------------------------
DROP TABLE IF EXISTS `student_exam`;
CREATE TABLE `student_exam`  (
  `id` int(11) NOT NULL,
  `u_id` int(11) NULL DEFAULT NULL COMMENT '学生ID',
  `e_id` int(11) NULL DEFAULT NULL COMMENT '考试ID',
  `right_answer` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '整张试卷的正确答案，可以存储成Json格式的字符串',
  `student_answer` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '整张试卷的学生答案，可以存储成Json格式的字符串',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student_exam
-- ----------------------------

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
INSERT INTO `tag` VALUES (2, 8, '政治');

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
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES (1, 9, 1, 1, 1, '我被修改了', '{\"answerContent\":\"1\",\"typeId\":1}', 1, 0, '2023-05-16 22:49:07');
INSERT INTO `topic` VALUES (2, 9, 5, 1, 1, '这是主观题', '{\"answerContent\":\"呼呼，我是第一题的答案\",\"typeId\":5}', 1, 1, '2023-05-16 23:45:07');
INSERT INTO `topic` VALUES (3, 11, 3, 2, 2, '这是一个共享的题目', '{\"answerContent\":\"[\\\"A\\\",\\\"B\\\",\\\"C\\\"]\",\"typeId\":3}', 1, 1, '2023-05-16 23:49:53');
INSERT INTO `topic` VALUES (4, 11, 1, 1, 2, '这是一个判断题，难度为中等', '{\"answerContent\":\"1\",\"typeId\":1}', 1, 1, '2023-05-17 15:51:47');
INSERT INTO `topic` VALUES (5, 9, 1, 1, 1, '我被修改了，我是凑数的', '{\"answerContent\":\"1\",\"typeId\":1}', 1, 1, '2023-05-18 22:49:07');
INSERT INTO `topic` VALUES (6, 11, 3, 1, 1, '这是一个多选题', '{\"answerContent\":\"[\\\"A\\\",\\\"B\\\"]\",\"typeId\":3}', 1, 1, '2023-05-18 12:39:39');
INSERT INTO `topic` VALUES (8, 11, 2, 1, 1, '这是一个单选选题', '{\"answerContent\":\"B\",\"typeId\":2}', 1, 1, '2023-05-27 01:28:27');
INSERT INTO `topic` VALUES (9, 11, 2, 1, 1, '又是一题单选选题', '{\"answerContent\":\"B\",\"typeId\":2}', 1, 1, '2023-05-27 01:30:46');

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
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (3, 1, 'zk', '$2a$10$t.wOpQEb5lcBwEewp.PzBunA.lS2fZGTgYiI4b099UZHaGgCGQnrm', '1206796814@qq.com', '2023-05-16 15:39:02');
INSERT INTO `user` VALUES (9, 1, 'zjq', '$2a$10$cauSOxYgNk7VC6iN/6RrcO/GGhGZdAzZxLupK6.KKTEKObaX75Jna', 'z13706035322@163.com', '2023-05-16 13:35:32');
INSERT INTO `user` VALUES (11, 1, 'zxy', '$2a$10$O/RNV2QiKReJ2iCHwOKBeeF0Nv3SRHT9cq7NfvtQfmyrm9dDWY5ja', '2673492930@qq.com', '2023-05-16 23:47:57');
INSERT INTO `user` VALUES (15, 1, 'ZJQ', '$2a$10$DTvNc2goAbgSBGaQCSgQXukZKd6g7kLLd31SqrR6aCV4yS9P00l06', '2410360091@qq.com', '2023-05-25 12:27:06');
INSERT INTO `user` VALUES (16, 2, 'test1', NULL, '123test1@qq.com', NULL);
INSERT INTO `user` VALUES (17, 2, 'test2', NULL, '123test2@qq.com', NULL);
INSERT INTO `user` VALUES (18, 2, 'test3', NULL, '123test3@qq.com', NULL);
INSERT INTO `user` VALUES (19, 2, 'test4', NULL, '123test4@qq.com', NULL);

SET FOREIGN_KEY_CHECKS = 1;
