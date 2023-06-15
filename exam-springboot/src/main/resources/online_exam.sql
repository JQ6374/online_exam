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

 Date: 16/06/2023 02:19:27
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
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
-- Table structure for student_course
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course`  (
  `uc_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NULL DEFAULT NULL COMMENT '学生ID',
  `c_id` int(11) NULL DEFAULT NULL COMMENT '课程ID',
  PRIMARY KEY (`uc_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
