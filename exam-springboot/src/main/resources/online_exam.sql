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

 Date: 31/05/2023 05:07:39
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
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (33, 2, '2023（上）JavaEE', '87548766', '2023-05-31 03:26:48', 1);
INSERT INTO `course` VALUES (34, 2, '2023（上）Python基础', '31462561', '2023-05-31 04:59:53', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES (6, 2, 33, 33, '2023（上）JavaEE小测-1', '2023-05-31 00:00:00', '2023-06-01 00:00:00', 1, 1);
INSERT INTO `exam` VALUES (7, 2, 33, 35, '2023（上）JavaEE小测-2', '2023-05-31 00:00:00', '2023-06-02 00:00:00', 1, 1);
INSERT INTO `exam` VALUES (8, 2, 33, 36, '2023（上）JavaEE小测-手动组卷', '2023-05-31 00:00:00', '2023-06-01 00:00:00', 1, 1);
INSERT INTO `exam` VALUES (9, 2, 34, 36, '2023（上）Python小测-1', '2023-05-31 00:00:00', '2023-06-01 00:00:00', 1, 1);
INSERT INTO `exam` VALUES (10, 2, 34, 37, '2023（上）Python小测-2', '2023-05-31 00:00:00', '2023-06-01 00:00:00', 1, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of papers
-- ----------------------------
INSERT INTO `papers` VALUES (33, 2, '手动组卷', '{\"1\":[],\"2\":[{\"answer\":\"{\\\"answerContent\\\":\\\"A\\\",\\\"typeId\\\":2}\",\"question\":\"1、2018年2月12日,习近平在打好精准脱贫攻坚战座谈会上发表重要讲话强调,要强化监管,做到____。 \\nA、 阳光扶贫、廉洁扶贫 \\nB、 科学扶贫、廉洁扶贫 \\nC、 阳光扶贫、科学扶贫 \\nD、 公正扶贫、科学扶贫 \",\"score\":2,\"tId\":22},{\"answer\":\"{\\\"answerContent\\\":\\\"A\\\",\\\"typeId\\\":2}\",\"question\":\"2、全面从严治党永远在路上,要深刻认识党面临的( )的尖锐性和严峻性。① 精神懈怠危险② 能力不足危险③ 脱离群众危险④ 消极腐败危险 \\nA、 ①②③④ \\nB、 ①②③ \\nC、 ①② \\nD、 ①③④ \",\"score\":2,\"tId\":23}],\"3\":[],\"4\":[],\"5\":[]}', '2023-05-31 04:11:31', 1);
INSERT INTO `papers` VALUES (34, 2, '随机组卷', '{\"1\":[],\"2\":[],\"3\":[],\"4\":[],\"5\":[]}', '2023-05-31 04:41:22', 1);
INSERT INTO `papers` VALUES (35, 2, '随机组卷-1', '{\"1\":[],\"2\":[{\"answer\":\"{\\\"answerContent\\\":\\\"A\\\",\\\"typeId\\\":2}\",\"question\":\"2、全面从严治党永远在路上,要深刻认识党面临的( )的尖锐性和严峻性。① 精神懈怠危险② 能力不足危险③ 脱离群众危险④ 消极腐败危险 \\nA、 ①②③④ \\nB、 ①②③ \\nC、 ①② \\nD、 ①③④ \",\"score\":2,\"tId\":23},{\"answer\":\"{\\\"answerContent\\\":\\\"A\\\",\\\"typeId\\\":2}\",\"question\":\"2、全面从严治党永远在路上,要深刻认识党面临的( )的尖锐性和严峻性。① 精神懈怠危险② 能力不足危险③ 脱离群众危险④ 消极腐败危险 \\nA、 ①②③④ \\nB、 ①②③ \\nC、 ①② \\nD、 ①③④ \",\"score\":2,\"tId\":23}],\"3\":[],\"4\":[],\"5\":[]}', '2023-05-31 04:44:48', 1);
INSERT INTO `papers` VALUES (36, 2, '手动组卷-1', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"据新华社2022年5月24日报道,截至2021年底,我国医疗卫生机构实现县乡村 全覆盖。 \",\"score\":1,\"tId\":26}],\"2\":[{\"answer\":\"{\\\"answerContent\\\":\\\"A\\\",\\\"typeId\\\":2}\",\"question\":\"1、2018年2月12日,习近平在打好精准脱贫攻坚战座谈会上发表重要讲话强调,要强化监管,做到____。 \\nA、 阳光扶贫、廉洁扶贫 \\nB、 科学扶贫、廉洁扶贫 \\nC、 阳光扶贫、科学扶贫 \\nD、 公正扶贫、科学扶贫 \",\"score\":2,\"tId\":22},{\"answer\":\"{\\\"answerContent\\\":\\\"A\\\",\\\"typeId\\\":2}\",\"question\":\"2、全面从严治党永远在路上,要深刻认识党面临的( )的尖锐性和严峻性。① 精神懈怠危险② 能力不足危险③ 脱离群众危险④ 消极腐败危险 \\nA、 ①②③④ \\nB、 ①②③ \\nC、 ①② \\nD、 ①③④ \",\"score\":2,\"tId\":23},{\"answer\":\"{\\\"answerContent\\\":\\\"A\\\",\\\"typeId\\\":2}\",\"question\":\"国家主席习近平发表的二〇二一年新年贺词指出：站在“两个一百年”的历史交汇点,全面建设社会主义现代化国家新征程即将开启。( )。我们通过奋斗,披荆斩棘,走过了万水千山。我们还要继续奋斗,勇往直前,创造更加灿烂的辉煌! \\nA、 征途漫漫,惟有奋斗 \\nB、 撸起袖子加油干 \\nC、 不忘初心,方得始终 \\nD、 幸福都是奋斗出来的 \\n\",\"score\":2,\"tId\":27},{\"answer\":\"{\\\"answerContent\\\":\\\"B\\\",\\\"typeId\\\":2}\",\"question\":\"要强化战斗队思想,坚持____这个唯一的根本的标准,各项工作和建设、各方面力量和资源都要聚焦军事斗争准备、服务军事斗争准备,推动军事斗争准备工作有一个很大加强。 \\nA、 执行力 \\nB、 战斗力 \\nC、 凝聚力 \\nD、 竞争力 \",\"score\":2,\"tId\":28},{\"answer\":\"{\\\"answerContent\\\":\\\"D\\\",\\\"typeId\\\":2}\",\"question\":\"( )是引领发展的第一动力,是建设现代化经济体系的战略支撑。 \\nA、 改革 \\nB、 开放 \\nC、 发展 \\nD、 创新 \",\"score\":2,\"tId\":29},{\"answer\":\"{\\\"answerContent\\\":\\\"D\\\",\\\"typeId\\\":2}\",\"question\":\"1923年6月,中国共产党在广州举行第( )次全国代表大会,决定共产党员以个人身份加入国民党,实行国共合作,建立革命统一战线。 \\nA、 二 \\nB、 四 \\nC、 五 \\nD、 三 \",\"score\":2,\"tId\":30}],\"3\":[{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\",\\\\\\\"B\\\\\\\",\\\\\\\"C\\\\\\\",\\\\\\\"D\\\\\\\"]\\\",\\\"typeId\\\":3}\",\"question\":\"党的十八大以来,以习近平同志为核心的党中央以前所未有的力度抓生态文明建设,全党全国推动绿色发展的自觉性和主动性显著增强,美丽中国建设迈出重大步伐。这十年,是生态文明建设和生态环境保护____的十年。 \\nA、 认识最深 \\nB、 力度最大 \\nC、 举措最实 \\nD、 推进最快 \",\"score\":2,\"tId\":25}],\"4\":[],\"5\":[{\"answer\":\"{\\\"answerContent\\\":\\\"坚决维护习近平总书记党中央的核心。全党的核心地位。坚决维护党中央权威和集中统一领导。\\\",\\\"typeId\\\":5}\",\"question\":\"两个维护的内容：\",\"score\":5,\"tId\":24}]}', '2023-05-31 04:47:47', 1);
INSERT INTO `papers` VALUES (37, 2, '手动组卷-主观题刁钻测试', '{\"1\":[{\"answer\":\"{\\\"answerContent\\\":\\\"1\\\",\\\"typeId\\\":1}\",\"question\":\"据新华社2022年5月24日报道,截至2021年底,我国医疗卫生机构实现县乡村 全覆盖。 \",\"score\":1,\"tId\":26}],\"2\":[{\"answer\":\"{\\\"answerContent\\\":\\\"A\\\",\\\"typeId\\\":2}\",\"question\":\"1、2018年2月12日,习近平在打好精准脱贫攻坚战座谈会上发表重要讲话强调,要强化监管,做到____。 \\nA、 阳光扶贫、廉洁扶贫 \\nB、 科学扶贫、廉洁扶贫 \\nC、 阳光扶贫、科学扶贫 \\nD、 公正扶贫、科学扶贫 \",\"score\":2,\"tId\":22},{\"answer\":\"{\\\"answerContent\\\":\\\"A\\\",\\\"typeId\\\":2}\",\"question\":\"2、全面从严治党永远在路上,要深刻认识党面临的( )的尖锐性和严峻性。① 精神懈怠危险② 能力不足危险③ 脱离群众危险④ 消极腐败危险 \\nA、 ①②③④ \\nB、 ①②③ \\nC、 ①② \\nD、 ①③④ \",\"score\":2,\"tId\":23},{\"answer\":\"{\\\"answerContent\\\":\\\"A\\\",\\\"typeId\\\":2}\",\"question\":\"国家主席习近平发表的二〇二一年新年贺词指出：站在“两个一百年”的历史交汇点,全面建设社会主义现代化国家新征程即将开启。( )。我们通过奋斗,披荆斩棘,走过了万水千山。我们还要继续奋斗,勇往直前,创造更加灿烂的辉煌! \\nA、 征途漫漫,惟有奋斗 \\nB、 撸起袖子加油干 \\nC、 不忘初心,方得始终 \\nD、 幸福都是奋斗出来的 \\n\",\"score\":2,\"tId\":27},{\"answer\":\"{\\\"answerContent\\\":\\\"B\\\",\\\"typeId\\\":2}\",\"question\":\"要强化战斗队思想,坚持____这个唯一的根本的标准,各项工作和建设、各方面力量和资源都要聚焦军事斗争准备、服务军事斗争准备,推动军事斗争准备工作有一个很大加强。 \\nA、 执行力 \\nB、 战斗力 \\nC、 凝聚力 \\nD、 竞争力 \",\"score\":2,\"tId\":28},{\"answer\":\"{\\\"answerContent\\\":\\\"D\\\",\\\"typeId\\\":2}\",\"question\":\"( )是引领发展的第一动力,是建设现代化经济体系的战略支撑。 \\nA、 改革 \\nB、 开放 \\nC、 发展 \\nD、 创新 \",\"score\":2,\"tId\":29},{\"answer\":\"{\\\"answerContent\\\":\\\"A\\\",\\\"typeId\\\":2}\",\"question\":\"1923年6月,中国共产党在广州举行第( )次全国代表大会,决定共产党员以个人身份加入国民党,实行国共合作,建立革命统一战线。 \\nA、 二 \\nB、 四 \\nC、 五 \\nD、 三 \",\"score\":2,\"tId\":30}],\"3\":[{\"answer\":\"{\\\"answerContent\\\":\\\"[\\\\\\\"A\\\\\\\",\\\\\\\"B\\\\\\\",\\\\\\\"C\\\\\\\",\\\\\\\"D\\\\\\\"]\\\",\\\"typeId\\\":3}\",\"question\":\"党的十八大以来,以习近平同志为核心的党中央以前所未有的力度抓生态文明建设,全党全国推动绿色发展的自觉性和主动性显著增强,美丽中国建设迈出重大步伐。这十年,是生态文明建设和生态环境保护____的十年。 \\nA、 认识最深 \\nB、 力度最大 \\nC、 举措最实 \\nD、 推进最快 \",\"score\":2,\"tId\":25}],\"4\":[],\"5\":[{\"answer\":\"{\\\"answerContent\\\":\\\"坚决维护习近平总书记党中央的核心。全党的核心地位。坚决维护党中央权威和集中统一领导。\\\",\\\"typeId\\\":5}\",\"question\":\"两个维护的内容：\",\"score\":5,\"tId\":24},{\"answer\":\"{\\\"answerContent\\\":\\\"小天明明\\\",\\\"typeId\\\":5}\",\"question\":\"主观题刁钻测试\",\"score\":5,\"tId\":31}]}', '2023-05-31 05:02:42', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student_course
-- ----------------------------
INSERT INTO `student_course` VALUES (14, 1, 33);
INSERT INTO `student_course` VALUES (15, 20, 33);
INSERT INTO `student_course` VALUES (16, 20, 34);

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
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student_exam
-- ----------------------------
INSERT INTO `student_exam` VALUES (5, 1, 6, '{\"totalScore\":2.0,\"content\":[{\"score\":2,\"rightAnswer\":\"A\",\"answer\":{\"answerContent\":\"A\",\"typeId\":2},\"question\":\"1、2018年2月12日,习近平在打好精准脱贫攻坚战座谈会上发表重要讲话强调,要强化监管,做到____。 \\nA、 阳光扶贫、廉洁扶贫 \\nB、 科学扶贫、廉洁扶贫 \\nC、 阳光扶贫、科学扶贫 \\nD、 公正扶贫、科学扶贫 \",\"getScore\":2.0,\"tId\":22},{\"score\":2,\"rightAnswer\":\"A\",\"answer\":{\"answerContent\":\"D\",\"typeId\":2},\"question\":\"2、全面从严治党永远在路上,要深刻认识党面临的( )的尖锐性和严峻性。① 精神懈怠危险② 能力不足危险③ 脱离群众危险④ 消极腐败危险 \\nA、 ①②③④ \\nB、 ①②③ \\nC、 ①② \\nD、 ①③④ \",\"getScore\":0.0,\"tId\":23}]}', '2023-05-31 04:19:30');
INSERT INTO `student_exam` VALUES (6, 1, 7, '{\"totalScore\":4.0,\"content\":[{\"score\":2,\"rightAnswer\":\"A\",\"answer\":{\"answerContent\":\"A\",\"typeId\":2},\"question\":\"2、全面从严治党永远在路上,要深刻认识党面临的( )的尖锐性和严峻性。① 精神懈怠危险② 能力不足危险③ 脱离群众危险④ 消极腐败危险 \\nA、 ①②③④ \\nB、 ①②③ \\nC、 ①② \\nD、 ①③④ \",\"getScore\":2.0,\"tId\":23},{\"score\":2,\"rightAnswer\":\"A\",\"answer\":{\"answerContent\":\"A\",\"typeId\":2},\"question\":\"2、全面从严治党永远在路上,要深刻认识党面临的( )的尖锐性和严峻性。① 精神懈怠危险② 能力不足危险③ 脱离群众危险④ 消极腐败危险 \\nA、 ①②③④ \\nB、 ①②③ \\nC、 ①② \\nD、 ①③④ \",\"getScore\":2.0,\"tId\":23}]}', '2023-05-31 04:47:12');
INSERT INTO `student_exam` VALUES (7, 1, 8, '{\"totalScore\":8.0,\"content\":[{\"score\":1,\"rightAnswer\":\"1\",\"answer\":{\"answerContent\":1,\"typeId\":1},\"question\":\"据新华社2022年5月24日报道,截至2021年底,我国医疗卫生机构实现县乡村 全覆盖。 \",\"getScore\":1.0,\"tId\":26},{\"score\":2,\"rightAnswer\":\"A\",\"answer\":{\"answerContent\":\"A\",\"typeId\":2},\"question\":\"1、2018年2月12日,习近平在打好精准脱贫攻坚战座谈会上发表重要讲话强调,要强化监管,做到____。 \\nA、 阳光扶贫、廉洁扶贫 \\nB、 科学扶贫、廉洁扶贫 \\nC、 阳光扶贫、科学扶贫 \\nD、 公正扶贫、科学扶贫 \",\"getScore\":2.0,\"tId\":22},{\"score\":2,\"rightAnswer\":\"A\",\"answer\":{\"answerContent\":\"A\",\"typeId\":2},\"question\":\"2、全面从严治党永远在路上,要深刻认识党面临的( )的尖锐性和严峻性。① 精神懈怠危险② 能力不足危险③ 脱离群众危险④ 消极腐败危险 \\nA、 ①②③④ \\nB、 ①②③ \\nC、 ①② \\nD、 ①③④ \",\"getScore\":2.0,\"tId\":23},{\"score\":2,\"rightAnswer\":\"A\",\"answer\":{\"answerContent\":\"A\",\"typeId\":2},\"question\":\"国家主席习近平发表的二〇二一年新年贺词指出：站在“两个一百年”的历史交汇点,全面建设社会主义现代化国家新征程即将开启。( )。我们通过奋斗,披荆斩棘,走过了万水千山。我们还要继续奋斗,勇往直前,创造更加灿烂的辉煌! \\nA、 征途漫漫,惟有奋斗 \\nB、 撸起袖子加油干 \\nC、 不忘初心,方得始终 \\nD、 幸福都是奋斗出来的 \\n\",\"getScore\":2.0,\"tId\":27},{\"score\":2,\"rightAnswer\":\"B\",\"answer\":{\"answerContent\":\"A\",\"typeId\":2},\"question\":\"要强化战斗队思想,坚持____这个唯一的根本的标准,各项工作和建设、各方面力量和资源都要聚焦军事斗争准备、服务军事斗争准备,推动军事斗争准备工作有一个很大加强。 \\nA、 执行力 \\nB、 战斗力 \\nC、 凝聚力 \\nD、 竞争力 \",\"getScore\":0.0,\"tId\":28},{\"score\":2,\"rightAnswer\":\"D\",\"answer\":{\"answerContent\":\"A\",\"typeId\":2},\"question\":\"( )是引领发展的第一动力,是建设现代化经济体系的战略支撑。 \\nA、 改革 \\nB、 开放 \\nC、 发展 \\nD、 创新 \",\"getScore\":0.0,\"tId\":29},{\"score\":2,\"rightAnswer\":\"D\",\"answer\":{\"answerContent\":\"B\",\"typeId\":2},\"question\":\"1923年6月,中国共产党在广州举行第( )次全国代表大会,决定共产党员以个人身份加入国民党,实行国共合作,建立革命统一战线。 \\nA、 二 \\nB、 四 \\nC、 五 \\nD、 三 \",\"getScore\":0.0,\"tId\":30},{\"score\":2,\"rightAnswer\":\"[\\\"A\\\",\\\"B\\\",\\\"C\\\",\\\"D\\\"]\",\"answer\":{\"answerContent\":[\"A\",\"B\",\"C\"],\"typeId\":3},\"question\":\"党的十八大以来,以习近平同志为核心的党中央以前所未有的力度抓生态文明建设,全党全国推动绿色发展的自觉性和主动性显著增强,美丽中国建设迈出重大步伐。这十年,是生态文明建设和生态环境保护____的十年。 \\nA、 认识最深 \\nB、 力度最大 \\nC、 举措最实 \\nD、 推进最快 \",\"getScore\":1.0,\"tId\":25},{\"score\":5,\"rightAnswer\":\"坚决维护习近平总书记党中央的核心。全党的核心地位。坚决维护党中央权威和集中统一领导。\",\"answer\":{\"answerContent\":\"坚决维护党中央权威统一领导，坚决维护习近平总书记党中央的核心领导地位。\",\"typeId\":5},\"question\":\"两个维护的内容：\",\"getScore\":0.0,\"tId\":24}]}', '2023-05-31 04:50:19');
INSERT INTO `student_exam` VALUES (9, 20, 8, '{\"totalScore\":8.0,\"content\":[{\"score\":1,\"rightAnswer\":\"1\",\"answer\":{\"answerContent\":1,\"typeId\":1},\"question\":\"据新华社2022年5月24日报道,截至2021年底,我国医疗卫生机构实现县乡村 全覆盖。 \",\"getScore\":1.0,\"tId\":26},{\"score\":2,\"rightAnswer\":\"A\",\"answer\":{\"answerContent\":\"A\",\"typeId\":2},\"question\":\"1、2018年2月12日,习近平在打好精准脱贫攻坚战座谈会上发表重要讲话强调,要强化监管,做到____。 \\nA、 阳光扶贫、廉洁扶贫 \\nB、 科学扶贫、廉洁扶贫 \\nC、 阳光扶贫、科学扶贫 \\nD、 公正扶贫、科学扶贫 \",\"getScore\":2.0,\"tId\":22},{\"score\":2,\"rightAnswer\":\"A\",\"answer\":{\"answerContent\":\"A\",\"typeId\":2},\"question\":\"2、全面从严治党永远在路上,要深刻认识党面临的( )的尖锐性和严峻性。① 精神懈怠危险② 能力不足危险③ 脱离群众危险④ 消极腐败危险 \\nA、 ①②③④ \\nB、 ①②③ \\nC、 ①② \\nD、 ①③④ \",\"getScore\":2.0,\"tId\":23},{\"score\":2,\"rightAnswer\":\"A\",\"answer\":{\"answerContent\":\"A\",\"typeId\":2},\"question\":\"国家主席习近平发表的二〇二一年新年贺词指出：站在“两个一百年”的历史交汇点,全面建设社会主义现代化国家新征程即将开启。( )。我们通过奋斗,披荆斩棘,走过了万水千山。我们还要继续奋斗,勇往直前,创造更加灿烂的辉煌! \\nA、 征途漫漫,惟有奋斗 \\nB、 撸起袖子加油干 \\nC、 不忘初心,方得始终 \\nD、 幸福都是奋斗出来的 \\n\",\"getScore\":2.0,\"tId\":27},{\"score\":2,\"rightAnswer\":\"B\",\"answer\":{\"answerContent\":\"A\",\"typeId\":2},\"question\":\"要强化战斗队思想,坚持____这个唯一的根本的标准,各项工作和建设、各方面力量和资源都要聚焦军事斗争准备、服务军事斗争准备,推动军事斗争准备工作有一个很大加强。 \\nA、 执行力 \\nB、 战斗力 \\nC、 凝聚力 \\nD、 竞争力 \",\"getScore\":0.0,\"tId\":28},{\"score\":2,\"rightAnswer\":\"D\",\"answer\":{\"answerContent\":\"A\",\"typeId\":2},\"question\":\"( )是引领发展的第一动力,是建设现代化经济体系的战略支撑。 \\nA、 改革 \\nB、 开放 \\nC、 发展 \\nD、 创新 \",\"getScore\":0.0,\"tId\":29},{\"score\":2,\"rightAnswer\":\"D\",\"answer\":{\"answerContent\":\"A\",\"typeId\":2},\"question\":\"1923年6月,中国共产党在广州举行第( )次全国代表大会,决定共产党员以个人身份加入国民党,实行国共合作,建立革命统一战线。 \\nA、 二 \\nB、 四 \\nC、 五 \\nD、 三 \",\"getScore\":0.0,\"tId\":30},{\"score\":2,\"rightAnswer\":\"[\\\"A\\\",\\\"B\\\",\\\"C\\\",\\\"D\\\"]\",\"answer\":{\"answerContent\":[\"A\"],\"typeId\":3},\"question\":\"党的十八大以来,以习近平同志为核心的党中央以前所未有的力度抓生态文明建设,全党全国推动绿色发展的自觉性和主动性显著增强,美丽中国建设迈出重大步伐。这十年,是生态文明建设和生态环境保护____的十年。 \\nA、 认识最深 \\nB、 力度最大 \\nC、 举措最实 \\nD、 推进最快 \",\"getScore\":1.0,\"tId\":25},{\"score\":5,\"rightAnswer\":\"坚决维护习近平总书记党中央的核心。全党的核心地位。坚决维护党中央权威和集中统一领导。\",\"answer\":{\"answerContent\":\"坚决维护。坚决维护。\",\"typeId\":5},\"question\":\"两个维护的内容：\",\"getScore\":0.0,\"tId\":24}]}', '2023-05-31 04:55:40');
INSERT INTO `student_exam` VALUES (10, 20, 10, '{\"totalScore\":17.0,\"content\":[{\"score\":1,\"rightAnswer\":\"1\",\"answer\":{\"answerContent\":1,\"typeId\":1},\"question\":\"据新华社2022年5月24日报道,截至2021年底,我国医疗卫生机构实现县乡村 全覆盖。 \",\"getScore\":1.0,\"tId\":26},{\"score\":2,\"rightAnswer\":\"A\",\"answer\":{\"answerContent\":\"A\",\"typeId\":2},\"question\":\"1、2018年2月12日,习近平在打好精准脱贫攻坚战座谈会上发表重要讲话强调,要强化监管,做到____。 \\nA、 阳光扶贫、廉洁扶贫 \\nB、 科学扶贫、廉洁扶贫 \\nC、 阳光扶贫、科学扶贫 \\nD、 公正扶贫、科学扶贫 \",\"getScore\":2.0,\"tId\":22},{\"score\":2,\"rightAnswer\":\"A\",\"answer\":{\"answerContent\":\"A\",\"typeId\":2},\"question\":\"2、全面从严治党永远在路上,要深刻认识党面临的( )的尖锐性和严峻性。① 精神懈怠危险② 能力不足危险③ 脱离群众危险④ 消极腐败危险 \\nA、 ①②③④ \\nB、 ①②③ \\nC、 ①② \\nD、 ①③④ \",\"getScore\":2.0,\"tId\":23},{\"score\":2,\"rightAnswer\":\"A\",\"answer\":{\"answerContent\":\"A\",\"typeId\":2},\"question\":\"国家主席习近平发表的二〇二一年新年贺词指出：站在“两个一百年”的历史交汇点,全面建设社会主义现代化国家新征程即将开启。( )。我们通过奋斗,披荆斩棘,走过了万水千山。我们还要继续奋斗,勇往直前,创造更加灿烂的辉煌! \\nA、 征途漫漫,惟有奋斗 \\nB、 撸起袖子加油干 \\nC、 不忘初心,方得始终 \\nD、 幸福都是奋斗出来的 \\n\",\"getScore\":2.0,\"tId\":27},{\"score\":2,\"rightAnswer\":\"B\",\"answer\":{\"answerContent\":\"A\",\"typeId\":2},\"question\":\"要强化战斗队思想,坚持____这个唯一的根本的标准,各项工作和建设、各方面力量和资源都要聚焦军事斗争准备、服务军事斗争准备,推动军事斗争准备工作有一个很大加强。 \\nA、 执行力 \\nB、 战斗力 \\nC、 凝聚力 \\nD、 竞争力 \",\"getScore\":0.0,\"tId\":28},{\"score\":2,\"rightAnswer\":\"D\",\"answer\":{\"answerContent\":\"A\",\"typeId\":2},\"question\":\"( )是引领发展的第一动力,是建设现代化经济体系的战略支撑。 \\nA、 改革 \\nB、 开放 \\nC、 发展 \\nD、 创新 \",\"getScore\":0.0,\"tId\":29},{\"score\":2,\"rightAnswer\":\"A\",\"answer\":{\"answerContent\":\"A\",\"typeId\":2},\"question\":\"1923年6月,中国共产党在广州举行第( )次全国代表大会,决定共产党员以个人身份加入国民党,实行国共合作,建立革命统一战线。 \\nA、 二 \\nB、 四 \\nC、 五 \\nD、 三 \",\"getScore\":2.0,\"tId\":30},{\"score\":2,\"rightAnswer\":\"[\\\"A\\\",\\\"B\\\",\\\"C\\\",\\\"D\\\"]\",\"answer\":{\"answerContent\":[\"A\"],\"typeId\":3},\"question\":\"党的十八大以来,以习近平同志为核心的党中央以前所未有的力度抓生态文明建设,全党全国推动绿色发展的自觉性和主动性显著增强,美丽中国建设迈出重大步伐。这十年,是生态文明建设和生态环境保护____的十年。 \\nA、 认识最深 \\nB、 力度最大 \\nC、 举措最实 \\nD、 推进最快 \",\"getScore\":1.0,\"tId\":25},{\"score\":5,\"rightAnswer\":\"坚决维护习近平总书记党中央的核心。全党的核心地位。坚决维护党中央权威和集中统一领导。\",\"answer\":{\"answerContent\":\"坚决维护党中央权威和集中统一领导。\",\"typeId\":5},\"question\":\"两个维护的内容：\",\"getScore\":2.0,\"tId\":24},{\"score\":5,\"rightAnswer\":\"小天明明\",\"answer\":{\"answerContent\":\"小明明天。\",\"typeId\":5},\"question\":\"主观题刁钻测试\",\"getScore\":5.0,\"tId\":31}]}', '2023-05-31 05:04:33');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NULL DEFAULT NULL COMMENT '教师ID，标签可由教师管理，0代表所有教师可共享的标签',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (1, 0, '其他');
INSERT INTO `tag` VALUES (2, 2, '政治');

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
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES (22, 2, 2, 2, 1, '1、2018年2月12日,习近平在打好精准脱贫攻坚战座谈会上发表重要讲话强调,要强化监管,做到____。 \nA、 阳光扶贫、廉洁扶贫 \nB、 科学扶贫、廉洁扶贫 \nC、 阳光扶贫、科学扶贫 \nD、 公正扶贫、科学扶贫 ', '{\"answerContent\":\"A\",\"typeId\":2}', 1, 1, '2023-05-31 03:37:40');
INSERT INTO `topic` VALUES (23, 2, 2, 2, 2, '2、全面从严治党永远在路上,要深刻认识党面临的( )的尖锐性和严峻性。① 精神懈怠危险② 能力不足危险③ 脱离群众危险④ 消极腐败危险 \nA、 ①②③④ \nB、 ①②③ \nC、 ①② \nD、 ①③④ ', '{\"answerContent\":\"A\",\"typeId\":2}', 1, 1, '2023-05-31 03:38:12');
INSERT INTO `topic` VALUES (24, 2, 5, 2, 3, '两个维护的内容：', '{\"answerContent\":\"坚决维护习近平总书记党中央的核心。全党的核心地位。坚决维护党中央权威和集中统一领导。\",\"typeId\":5}', 1, 1, '2023-05-31 04:21:10');
INSERT INTO `topic` VALUES (25, 2, 3, 2, 3, '党的十八大以来,以习近平同志为核心的党中央以前所未有的力度抓生态文明建设,全党全国推动绿色发展的自觉性和主动性显著增强,美丽中国建设迈出重大步伐。这十年,是生态文明建设和生态环境保护____的十年。 \nA、 认识最深 \nB、 力度最大 \nC、 举措最实 \nD、 推进最快 ', '{\"answerContent\":\"[\\\"A\\\",\\\"B\\\",\\\"C\\\",\\\"D\\\"]\",\"typeId\":3}', NULL, 1, '2023-05-31 04:22:23');
INSERT INTO `topic` VALUES (26, 2, 1, 2, 2, '据新华社2022年5月24日报道,截至2021年底,我国医疗卫生机构实现县乡村 全覆盖。 ', '{\"answerContent\":\"1\",\"typeId\":1}', 1, 1, '2023-05-31 04:22:55');
INSERT INTO `topic` VALUES (27, 2, 2, 2, 1, '国家主席习近平发表的二〇二一年新年贺词指出：站在“两个一百年”的历史交汇点,全面建设社会主义现代化国家新征程即将开启。( )。我们通过奋斗,披荆斩棘,走过了万水千山。我们还要继续奋斗,勇往直前,创造更加灿烂的辉煌! \nA、 征途漫漫,惟有奋斗 \nB、 撸起袖子加油干 \nC、 不忘初心,方得始终 \nD、 幸福都是奋斗出来的 \n', '{\"answerContent\":\"A\",\"typeId\":2}', 1, 1, '2023-05-31 04:42:19');
INSERT INTO `topic` VALUES (28, 2, 2, 2, 1, '要强化战斗队思想,坚持____这个唯一的根本的标准,各项工作和建设、各方面力量和资源都要聚焦军事斗争准备、服务军事斗争准备,推动军事斗争准备工作有一个很大加强。 \nA、 执行力 \nB、 战斗力 \nC、 凝聚力 \nD、 竞争力 ', '{\"answerContent\":\"B\",\"typeId\":2}', 1, 1, '2023-05-31 04:42:36');
INSERT INTO `topic` VALUES (29, 2, 2, 2, 1, '( )是引领发展的第一动力,是建设现代化经济体系的战略支撑。 \nA、 改革 \nB、 开放 \nC、 发展 \nD、 创新 ', '{\"answerContent\":\"D\",\"typeId\":2}', 1, 1, '2023-05-31 04:42:56');
INSERT INTO `topic` VALUES (30, 2, 2, 2, 1, '1923年6月,中国共产党在广州举行第( )次全国代表大会,决定共产党员以个人身份加入国民党,实行国共合作,建立革命统一战线。 \nA、 二 \nB、 四 \nC、 五 \nD、 三 ', '{\"answerContent\":\"A\",\"typeId\":2}', NULL, 1, '2023-05-31 04:43:18');
INSERT INTO `topic` VALUES (31, 2, 5, 2, 2, '主观题刁钻测试', '{\"answerContent\":\"小天明明\",\"typeId\":5}', 1, 1, '2023-05-31 05:02:17');

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
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 1, 'S-JQ', '$2a$10$mMMCsky1DR2akheQuXfyBu1Gt5uz7mCe/gYvft8PW.zXy2br88hDq', '2410360091@qq.com', '2023-05-25 12:27:06');
INSERT INTO `user` VALUES (2, 2, 'T-JQ', '$2a$10$cauSOxYgNk7VC6iN/6RrcO/GGhGZdAzZxLupK6.KKTEKObaX75Jna', 'z13706035322@163.com', '2023-05-16 13:35:32');
INSERT INTO `user` VALUES (11, 2, 'zxy', '$2a$10$cyuQB4.SdiQgS4yVoa9aV.lPFFm4pDZUYQAWrn/FpwnjhNRiy4WV6', '2673492930@qq.com', '2023-05-16 23:47:57');
INSERT INTO `user` VALUES (20, 1, 'sxl', '$2a$10$6Rp4yyYSnrWTECP0gouwtOrGzGAKELRqWrHsKeSce7DL2cRD.2qxu', '2608055142@qq.com', '2023-05-29 17:17:39');
INSERT INTO `user` VALUES (21, 2, 'zk', '$2a$10$t.wOpQEb5lcBwEewp.PzBunA.lS2fZGTgYiI4b099UZHaGgCGQnrm', '1206796814@qq.com', '2023-05-16 15:39:02');

SET FOREIGN_KEY_CHECKS = 1;
