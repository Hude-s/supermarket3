/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : supermarket

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 05/05/2022 10:15:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `emp_id` int(10) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `emp_gender` int(1) NULL DEFAULT NULL,
  `emp_age` int(2) NULL DEFAULT NULL,
  `emp_post` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emp_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emp_salary` double(7, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`emp_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, '张三', 1, 18, '货物管理', '12345678912', 12345.00);
INSERT INTO `employee` VALUES (6, '王五', 1, 20, '收银员', '13122048859', 4561.00);
INSERT INTO `employee` VALUES (11, '王五', 1, 18, '收银员', '13122048859', 4561.00);
INSERT INTO `employee` VALUES (13, '王五', 0, 18, '收银员', '13122048859', 4561.00);
INSERT INTO `employee` VALUES (22, '王xi明', 1, 32, '管理', '456123523', 5612.00);
INSERT INTO `employee` VALUES (23, '王1', 1, 22, '收银员', '13122048859', 4561.00);
INSERT INTO `employee` VALUES (27, '王1', 0, 22, '收银员', '13122048859', 4561.00);
INSERT INTO `employee` VALUES (28, '王1', 0, 22, '收银员', '13122048859', 4561.00);
INSERT INTO `employee` VALUES (29, '王1', 0, 24, '收银员', '13122048859', 4561.00);
INSERT INTO `employee` VALUES (30, '王2', 0, 24, '收银员', '13122048859', 4561.00);
INSERT INTO `employee` VALUES (31, '王1', 0, 24, '收银员', '13122048859', 4561.00);
INSERT INTO `employee` VALUES (32, '王1', 0, 24, '收银员', '13122048859', 4561.00);
INSERT INTO `employee` VALUES (33, '王1', 0, 24, '收银员', '13122048859', 4561.00);
INSERT INTO `employee` VALUES (34, '王1', 0, 24, '收银员', '13122048859', 4561.00);
INSERT INTO `employee` VALUES (35, '王1', 0, 24, '收银员', '13122048859', 4561.00);
INSERT INTO `employee` VALUES (36, '王1', 0, 24, '收银员', '13122048859', 4561.00);
INSERT INTO `employee` VALUES (37, '王1', 0, 24, '收银员', '13122048859', 4561.00);
INSERT INTO `employee` VALUES (38, '王1', 0, 25, '收银员', '13122048859', 4561.00);
INSERT INTO `employee` VALUES (39, '王1', 0, 25, '收银员', '13122048859', 4561.00);
INSERT INTO `employee` VALUES (40, '王1', 0, 25, '收银员', '13122048859', 4561.00);
INSERT INTO `employee` VALUES (41, '王1', 0, 25, '收银员', '13122048859', 4561.00);
INSERT INTO `employee` VALUES (42, '王1', 0, 25, '收银员', '13122048859', 4561.00);
INSERT INTO `employee` VALUES (46, '钱五', 0, 19, '货物管理', '13142245118', 4123.47);
INSERT INTO `employee` VALUES (47, '赵六', 1, 20, '货物管理', '13142245118', 1497.74);
INSERT INTO `employee` VALUES (48, '孙七', 0, 20, '货物管理', '13142245118', 2687.90);
INSERT INTO `employee` VALUES (49, '赵三', 0, 19, '货物管理', '13142245118', 4641.28);
INSERT INTO `employee` VALUES (50, '张小白', 1, 19, '销售经理', '18820002255', 8000.00);
INSERT INTO `employee` VALUES (51, '123', 1, 19, '经理', '18820002000', 6000.00);
INSERT INTO `employee` VALUES (52, '小黑', 0, 19, '总管', '1991234654', 8000.00);
INSERT INTO `employee` VALUES (53, 'baibai', 1, 18, '经理', '19918813331', 8000.00);
INSERT INTO `employee` VALUES (54, '李四', 1, 19, '销售', '19812345623', 7000.00);
INSERT INTO `employee` VALUES (55, '李四4', 1, 19, 'kszx', '18651032', 5000.00);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` int(40) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `goods_unit` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_price` float(10, 2) NULL DEFAULT NULL,
  `number` int(20) NULL DEFAULT NULL,
  `production_date` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '花生', '斤', 12.50, 500, '2022-3-20');
INSERT INTO `goods` VALUES (2, '酒', '斤', 5.00, 200, '2021-3-19');
INSERT INTO `goods` VALUES (3, '风扇', '台', 50.00, 80, '2022-05-04');
INSERT INTO `goods` VALUES (5, '不知道', '位', 999.00, 1, '2022-05-04');
INSERT INTO `goods` VALUES (6, '没头脑', '个', 999.00, 1, '2022-05-04');
INSERT INTO `goods` VALUES (12, '1232', '12', 12.00, 52, '2022-05-04');

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock`  (
  `stock_id` int(10) NOT NULL AUTO_INCREMENT,
  `goods_id` int(20) NOT NULL,
  `goods_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_stock` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`stock_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stock
-- ----------------------------

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `supplier_id` int(20) NOT NULL AUTO_INCREMENT,
  `supplier_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `link_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`supplier_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES (1, '南京机电设备有限公司', '江苏南京', '18905187335', '张鸣');
INSERT INTO `supplier` VALUES (2, '佛山市建材有限公司', '广东佛山', '18805167552', '李华');
INSERT INTO `supplier` VALUES (3, '北京市食品有限公司', '河北北京', '1861860231', '张三峰');
INSERT INTO `supplier` VALUES (8, '123', '123', '132', '123');
INSERT INTO `supplier` VALUES (9, '123', '132', '13', '123');
INSERT INTO `supplier` VALUES (12, 'sd', 'fsd', '13', '123');
INSERT INTO `supplier` VALUES (13, '12', '123', '32', '23');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `state` int(1) NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'root', 'root', 1);
INSERT INTO `user` VALUES (2, 'user1', 'user1', 1);
INSERT INTO `user` VALUES (3, 'user2', 'user2', 1);
INSERT INTO `user` VALUES (5, 'root1', 'root1', 1);
INSERT INTO `user` VALUES (7, 'baibai', 'heiei', 1);
INSERT INTO `user` VALUES (8, 'aaaa', 'heiei', 1);
INSERT INTO `user` VALUES (9, 'cccccc', 'ddddd', 1);
INSERT INTO `user` VALUES (13, 'min', '13312', 1);
INSERT INTO `user` VALUES (14, 'bai', 'hei', 1);
INSERT INTO `user` VALUES (15, 'root231', 'root2', 1);
INSERT INTO `user` VALUES (16, '白', '456', 1);
INSERT INTO `user` VALUES (17, '黑', '456', 1);

SET FOREIGN_KEY_CHECKS = 1;
