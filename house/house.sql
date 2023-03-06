/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : house

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 01/01/2021 23:22:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for contract
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `starttime` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `stoptime` datetime NULL DEFAULT NULL COMMENT '结束时间（提前退则修改这里）',
  `price` float NULL DEFAULT NULL COMMENT '价格（/月）（最终成交价格）',
  `roomid` int(11) NULL DEFAULT NULL COMMENT '房间id',
  `useraccount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账户',
  `salesmanaccount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务员账户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userid`(`useraccount`) USING BTREE,
  INDEX `11`(`roomid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of contract
-- ----------------------------
INSERT INTO `contract` VALUES (1, '20201228221620-123', '2019-02-28 00:00:00', '2020-01-28 00:00:00', 1600, 1, 'ma', 'mc');
INSERT INTO `contract` VALUES (2, '20201212421420-124', '2018-10-28 22:33:48', '2020-02-13 00:00:00', 5200, 3, 'ma1', 'mcc');
INSERT INTO `contract` VALUES (3, '20201212214620-342', '2019-02-28 00:00:00', '2020-05-09 00:00:00', 2700, 6, 'ma1', 'mcc');
INSERT INTO `contract` VALUES (4, '20201228221620-654', '2019-01-28 22:35:03', '2020-07-13 00:00:00', 5400, 2, 'ma2', 'mcc');
INSERT INTO `contract` VALUES (5, '20201212312320-557', '2019-07-25 22:35:17', '2020-09-12 00:00:00', 3700, 5, 'ma', 'mc');
INSERT INTO `contract` VALUES (6, '20201211312330-142', '2019-07-02 22:35:41', '2020-12-08 00:00:00', 2300, 9, 'ma2', 'mc');
INSERT INTO `contract` VALUES (7, '20201228123352-432', '2019-12-29 22:36:01', '2020-03-11 00:00:00', 4200, 10, 'ma1', 'mc');
INSERT INTO `contract` VALUES (8, '20201231421411-325', '2020-01-04 22:36:18', '2020-04-02 00:00:00', 1900, 7, 'ma2', 'mcc');
INSERT INTO `contract` VALUES (9, '20201228221620-344', '2020-02-28 22:36:40', '2020-06-17 00:00:00', 2800, 6, 'ma', 'mc');
INSERT INTO `contract` VALUES (10, '20201232423420-523', '2020-07-22 22:36:51', '2020-08-13 00:00:00', 3200, 8, 'ma', 'mc');
INSERT INTO `contract` VALUES (11, '20201228221620-423', '2020-05-12 22:37:23', '2020-10-27 00:00:00', 3100, 2, 'ma2', 'mc');
INSERT INTO `contract` VALUES (12, '20202343523620-523', '2020-04-10 22:37:37', '2020-11-21 00:00:00', 2900, 3, 'ma1', 'mcc');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `starttime` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `stoptime` datetime NULL DEFAULT NULL COMMENT '结束时间（提前退则修改这里）',
  `price` float NULL DEFAULT NULL COMMENT '价格（/月）（最终成交价格）',
  `roomid` int(11) NULL DEFAULT NULL COMMENT '房间id',
  `useraccount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账户',
  `salesmanaccount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务员账户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userid`(`useraccount`) USING BTREE,
  INDEX `11`(`roomid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, '20201228221620-123', '2019-02-28 00:00:00', '2020-01-28 00:00:00', 1600, 1, 'ma', 'mc');
INSERT INTO `orders` VALUES (2, '20201212421420-124', '2018-10-28 22:33:48', '2020-02-13 00:00:00', 5200, 3, 'ma1', 'mcc');
INSERT INTO `orders` VALUES (3, '20201212214620-342', '2019-02-28 00:00:00', '2020-05-09 00:00:00', 2700, 6, 'ma1', 'mcc');
INSERT INTO `orders` VALUES (4, '20201228221620-654', '2019-01-28 22:35:03', '2020-07-13 00:00:00', 5400, 2, 'ma2', 'mcc');
INSERT INTO `orders` VALUES (5, '20201212312320-557', '2019-07-25 22:35:17', '2020-09-12 00:00:00', 3700, 5, 'ma', 'mc');
INSERT INTO `orders` VALUES (6, '20201211312330-142', '2019-07-02 22:35:41', '2020-12-08 00:00:00', 2300, 9, 'ma2', 'mc');
INSERT INTO `orders` VALUES (7, '20201228123352-432', '2019-12-29 22:36:01', '2020-03-11 00:00:00', 4200, 10, 'ma1', 'mc');
INSERT INTO `orders` VALUES (8, '20201231421411-325', '2020-01-04 22:36:18', '2020-04-02 00:00:00', 1900, 7, 'ma2', 'mcc');
INSERT INTO `orders` VALUES (9, '20201228221620-344', '2020-02-28 22:36:40', '2020-06-17 00:00:00', 2800, 6, 'ma', 'mc');
INSERT INTO `orders` VALUES (10, '20201232423420-523', '2020-07-22 22:36:51', '2020-08-13 00:00:00', 3200, 8, 'ma', 'mc');
INSERT INTO `orders` VALUES (11, '20201228221620-423', '2020-05-12 22:37:23', '2020-10-27 00:00:00', 3100, 2, 'ma2', 'mc');
INSERT INTO `orders` VALUES (12, '20202343523620-523', '2020-04-10 22:37:37', '2020-11-21 00:00:00', 2900, 3, 'ma1', 'mcc');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间名',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '空闲' COMMENT '非空 应为“空闲”、“已租”',
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户（住户），无则空',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '面积',
  `price` float NULL DEFAULT NULL COMMENT '价格（/月）（标价）',
  `structure` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结构，如两室一厅等',
  `owner` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房东姓名',
  `ownerphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房东联系电话',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (1, '翻斗花园001', '空闲', '', '西工区137街道', '200', 800, '三室两厅', '赵', '111');
INSERT INTO `room` VALUES (2, '爱情公寓030', '空闲', '', '涧西区123街道', '130', 900, '三室一厅', '钱', '222');
INSERT INTO `room` VALUES (3, '乾园公寓435', '空闲', '', '洛龙区开元大道', '80', 1300, '一室一厅', '孙', '333');
INSERT INTO `room` VALUES (5, '幸福小区1#07', '空闲', '', '涧西区0101街道', '165', 1000, '三室两厅', '周', '555');
INSERT INTO `room` VALUES (6, '临湖花园112', '空闲', '', '洛龙区11街道', '122', 990, '两室一厅', '吴', '666');
INSERT INTO `room` VALUES (7, '汉水之家011', '空闲', '', '西工区999街道', '200', 1000, '四室两厅', '刘', '777');
INSERT INTO `room` VALUES (8, '幸福小区2#04', '空闲', '', '洛龙区222街道', '190', 1500, '五室三厅', '李', '888');
INSERT INTO `room` VALUES (9, '临湖花园394', '空闲', '', '涧西区666街道', '210', 1700, '一室一厅', '徐', '999');
INSERT INTO `room` VALUES (10, '翻斗花园211', '空闲', '', '洛龙区777街道', '155', 1900, '三室一厅', '张', '000');

-- ----------------------------
-- Table structure for sensor
-- ----------------------------
DROP TABLE IF EXISTS `sensor`;
CREATE TABLE `sensor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room` int(11) NOT NULL COMMENT '房间id',
  `temperature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '温度',
  `humidity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '湿度',
  `pm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'pm2.5',
  `noise` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '噪声',
  `time` datetime NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 90 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sensor
-- ----------------------------
INSERT INTO `sensor` VALUES (1, 1, '11', '22', '33', '44', '2020-12-24 21:10:22');
INSERT INTO `sensor` VALUES (2, 1, '00', '88', '77', '99', '2020-12-24 21:11:35');
INSERT INTO `sensor` VALUES (4, 2, '12', '23', '34', '89', '2020-12-24 21:17:04');
INSERT INTO `sensor` VALUES (60, 1, '-2.0', '0.00', '25', '17', '2020-12-25 18:01:06');
INSERT INTO `sensor` VALUES (61, 1, '8.0', '0.19', '25', '17', '2020-12-25 18:01:07');
INSERT INTO `sensor` VALUES (62, 1, '-12.0', '0.36', '25', '17', '2020-12-25 18:01:08');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '进入系统账户',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '进入系统密码',
  `type` int(11) NOT NULL DEFAULT 2 COMMENT '类型：系统管理员0，业务员1，用户2，默认2',
  PRIMARY KEY (`id`, `account`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', NULL, 'admin', 'admin', 0);
INSERT INTO `user` VALUES (2, '业务小红', '15836985566', 'mc', '111', 1);
INSERT INTO `user` VALUES (3, '业务小小', '15655854477', 'mcc', '111', 1);
INSERT INTO `user` VALUES (4, '用户小风', '13255329898', 'user1', '111', 2);
INSERT INTO `user` VALUES (5, '用户小明', '13156564747', 'ma1', '111', 2);
INSERT INTO `user` VALUES (6, '用户小龙', '13115524563', 'ma2', '111', 2);
INSERT INTO `user` VALUES (27, '', '', '', '', 1);

SET FOREIGN_KEY_CHECKS = 1;
