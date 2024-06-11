/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80200 (8.2.0)
 Source Host           : localhost:3306
 Source Schema         : online_exam

 Target Server Type    : MySQL
 Target Server Version : 80200 (8.2.0)
 File Encoding         : 65001

 Date: 11/06/2024 09:12:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `c_id` int NOT NULL AUTO_INCREMENT COMMENT '课程ID ',
  `u_id` int DEFAULT NULL COMMENT '教师id',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '课程名称',
  `course_code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '课程代码，学生可通过班级码加入班级',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_exist` int DEFAULT NULL COMMENT '用于逻辑删除，默认值1代表存在，0代表删除',
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;


-- ----------------------------
-- Table structure for difficulty
-- ----------------------------
DROP TABLE IF EXISTS `difficulty`;
CREATE TABLE `difficulty` (
  `d_id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`d_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of difficulty
-- ----------------------------
BEGIN;
INSERT INTO `difficulty` (`d_id`, `name`) VALUES (1, '简单');
INSERT INTO `difficulty` (`d_id`, `name`) VALUES (2, '中等');
INSERT INTO `difficulty` (`d_id`, `name`) VALUES (3, '困难');
COMMIT;

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `e_id` int NOT NULL AUTO_INCREMENT COMMENT '教师Id',
  `u_id` int DEFAULT NULL COMMENT '教师ID',
  `c_id` int DEFAULT NULL COMMENT '课程ID',
  `p_id` int DEFAULT NULL COMMENT '试卷ID',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '考试名称',
  `start_time` datetime DEFAULT NULL COMMENT '考试开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '考试结束时间',
  `status` int DEFAULT NULL COMMENT '考试状态',
  `is_exist` int DEFAULT NULL COMMENT '逻辑删除，默认值1表示存在，0表示删除',
  PRIMARY KEY (`e_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for papers
-- ----------------------------
DROP TABLE IF EXISTS `papers`;
CREATE TABLE `papers` (
  `p_id` int NOT NULL AUTO_INCREMENT,
  `u_id` int DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '试卷名称',
  `content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '整张试卷的内容，格式：{\r\n    "tId": {\r\n        "question":"xxx",\r\n         "answer":"xxxx"\r\n    },\r\n}',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_exist` int DEFAULT NULL COMMENT '逻辑删除；1代表存在；0代表删除',
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;


-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `r_id` int NOT NULL COMMENT '角色ID',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`r_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` (`r_id`, `name`) VALUES (1, '教师');
INSERT INTO `role` (`r_id`, `name`) VALUES (2, '学生');
COMMIT;

-- ----------------------------
-- Table structure for student_course
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course` (
  `uc_id` int NOT NULL AUTO_INCREMENT,
  `u_id` int DEFAULT NULL COMMENT '学生ID',
  `c_id` int DEFAULT NULL COMMENT '课程ID',
  PRIMARY KEY (`uc_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;


-- ----------------------------
-- Table structure for student_exam
-- ----------------------------
DROP TABLE IF EXISTS `student_exam`;
CREATE TABLE `student_exam` (
  `se_id` int NOT NULL AUTO_INCREMENT,
  `u_id` int DEFAULT NULL COMMENT '学生ID',
  `e_id` int DEFAULT NULL COMMENT '考试ID',
  `right_student_answer` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '整张试卷的正确答案，可以存储成Json格式的字符串',
  `create_time` datetime DEFAULT NULL COMMENT '提交试卷时间',
  PRIMARY KEY (`se_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;


-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tag_id` int NOT NULL AUTO_INCREMENT,
  `u_id` int DEFAULT NULL COMMENT '教师ID，标签可由教师管理，0代表所有教师可共享的标签',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '标签名称',
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tag
-- ----------------------------
BEGIN;
INSERT INTO `tag` (`tag_id`, `u_id`, `name`) VALUES (1, 0, '其他');
INSERT INTO `tag` (`tag_id`, `u_id`, `name`) VALUES (2, 0, '可在标签表中加入对应的课程标签');
COMMIT;

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `t_id` int NOT NULL AUTO_INCREMENT COMMENT '题目ID',
  `u_id` int DEFAULT NULL COMMENT '教师ID',
  `type_id` int DEFAULT NULL COMMENT '题目类型：判断、单选、多选、填空、主观题',
  `tag_id` int DEFAULT NULL COMMENT '题目标签：Java、Python等等',
  `difficulty_id` int DEFAULT NULL COMMENT '难度类型：简单题、中等题、困难题',
  `question` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '问题',
  `answer` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '答案，格式：["A","B"]||["填空题和主观题的答案"]，\r\n判断题[1|0]，1代表√，0代表×',
  `status` int DEFAULT NULL COMMENT '状态：是否与其他人共享题目，1代表共享，0代表不共享',
  `is_exist` int DEFAULT NULL COMMENT '逻辑删除，默认值1代表存在，0代表删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;


-- ----------------------------
-- Table structure for topic_type
-- ----------------------------
DROP TABLE IF EXISTS `topic_type`;
CREATE TABLE `topic_type` (
  `type_id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of topic_type
-- ----------------------------
BEGIN;
INSERT INTO `topic_type` (`type_id`, `name`) VALUES (1, '判断题');
INSERT INTO `topic_type` (`type_id`, `name`) VALUES (2, '单选题');
INSERT INTO `topic_type` (`type_id`, `name`) VALUES (3, '多选题');
INSERT INTO `topic_type` (`type_id`, `name`) VALUES (4, '填空题');
INSERT INTO `topic_type` (`type_id`, `name`) VALUES (5, '主观题');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `u_id` int NOT NULL AUTO_INCREMENT,
  `r_id` int NOT NULL COMMENT '角色ID',
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  PRIMARY KEY (`u_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

