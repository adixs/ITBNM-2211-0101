/*
 Navicat Premium Data Transfer

 Source Server         : thalathuoyadb
 Source Server Type    : MariaDB
 Source Server Version : 110302 (11.3.2-MariaDB)
 Source Host           : localhost:3307
 Source Schema         : tdb

 Target Server Type    : MariaDB
 Target Server Version : 110302 (11.3.2-MariaDB)
 File Encoding         : 65001

 Date: 13/06/2024 12:15:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for billing
-- ----------------------------
DROP TABLE IF EXISTS `billing`;
CREATE TABLE `billing`  (
  `bill_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `amount` double NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `description` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`bill_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of billing
-- ----------------------------
INSERT INTO `billing` VALUES (1, 'dfs', 54243, NULL, 'sfg');
INSERT INTO `billing` VALUES (2, 'gjf', 636, NULL, 'kg');
INSERT INTO `billing` VALUES (3, 'dasun shanaka', 50000, NULL, '1 room');

-- ----------------------------
-- Table structure for bills
-- ----------------------------
DROP TABLE IF EXISTS `bills`;
CREATE TABLE `bills`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `description` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `amount` decimal(10, 2) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bills
-- ----------------------------
INSERT INTO `bills` VALUES (1, 'wanidu', '1000 offer', 1000.00);
INSERT INTO `bills` VALUES (2, 'dasun shanaka', 'single daya room', 50000.00);
INSERT INTO `bills` VALUES (3, 'mahela', 'single room', 50000.00);
INSERT INTO `bills` VALUES (4, 'wanidu', 'dinner', 7800.00);
INSERT INTO `bills` VALUES (5, 'malinga', 'room 001', 65000.00);
INSERT INTO `bills` VALUES (6, 'sanam', 'dinner', 1500.00);
INSERT INTO `bills` VALUES (7, 'pubudu', 'premium package for one day with dinner', 58000.00);
INSERT INTO `bills` VALUES (8, 'pubudu', 'premium package for one day with dinner', 58000.00);

-- ----------------------------
-- Table structure for driver
-- ----------------------------
DROP TABLE IF EXISTS `driver`;
CREATE TABLE `driver`  (
  `name` varchar(25) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `age` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `gender` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `company` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `brand` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `available` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `location` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of driver
-- ----------------------------
INSERT INTO `driver` VALUES ('122', '23', 'Male', 'bg', 'ed', NULL, NULL);
INSERT INTO `driver` VALUES ('265', '55', 'Male', 'fdg', 'fxbg', NULL, NULL);
INSERT INTO `driver` VALUES ('5363', '63', 'Male', '536', 'gjfh', NULL, NULL);
INSERT INTO `driver` VALUES ('323', '352', 'Male', '23', '[]k', NULL, NULL);
INSERT INTO `driver` VALUES ('42', '42', 'Male', '574', 'hfj', NULL, NULL);
INSERT INTO `driver` VALUES ('012', '121', 'Male', '5l', '5', NULL, NULL);
INSERT INTO `driver` VALUES ('223', '13', 'Male', '32m', '23', 'Available', 'bjlg');
INSERT INTO `driver` VALUES ('353', '32', 'Female', 'hjfd', '24g', 'Busy', 'asfsf');
INSERT INTO `driver` VALUES ('5253', '21', 'Male', 'ijl', 'jll', 'Available', 'hill');
INSERT INTO `driver` VALUES ('shantha', '53', 'Male', 'toyota', 'prius', 'Available', 'kandy town');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `name` varchar(25) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `age` varchar(25) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `gender` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `job` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `salary` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `phone` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `email` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('shradda', '', 'null', 'Front Desk Clerks', '', '', '');
INSERT INTO `employee` VALUES ('juliyan', '25', 'Male', 'Porters', '150000', '46451', 'igj@gmail.com');
INSERT INTO `employee` VALUES ('fghdgh', '22', 'Male', 'Front Desk Clerks', '5243643', '6434336', 'fgds@hj');
INSERT INTO `employee` VALUES ('vcbn', '11', 'Male', 'Front Desk Clerks', '23', '', '');
INSERT INTO `employee` VALUES ('gkgk', '12', 'Female', 'Front Desk Clerks', '543', '543', 'jhh@');
INSERT INTO `employee` VALUES ('dg', '25', 'Male', 'Front Desk Clerks', '57', '8572', '875');
INSERT INTO `employee` VALUES ('wanidu', '35', 'Male', 'Housekeeping', '55000', '5545', 'fg@');
INSERT INTO `employee` VALUES ('ranidu', '32', 'Male', 'Accountant', '150000', '25465465', 'hgiyg@gmail.com');
INSERT INTO `employee` VALUES ('Adithya', '23', 'Male', 'Manager', '150000', '0777153205', 'sudar.sb@gmail.com');
INSERT INTO `employee` VALUES ('gayani', '', 'Female', 'Manager', '200000', '6565656', 'sfrgs@gmail.com');

-- ----------------------------
-- Table structure for food_menu
-- ----------------------------
DROP TABLE IF EXISTS `food_menu`;
CREATE TABLE `food_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dish_name` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of food_menu
-- ----------------------------
INSERT INTO `food_menu` VALUES (1, 'prowns', 1000);
INSERT INTO `food_menu` VALUES (2, 'chicken rice', 750);
INSERT INTO `food_menu` VALUES (4, 'sushi', 1500);
INSERT INTO `food_menu` VALUES (5, 'Rice and curry', 350);
INSERT INTO `food_menu` VALUES (6, 'pork kottu', 880);
INSERT INTO `food_menu` VALUES (7, 'Momo', 500);
INSERT INTO `food_menu` VALUES (8, 'special large biriyani ', 2700);
INSERT INTO `food_menu` VALUES (9, 'parata 4 with gravy', 450);

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login`  (
  `username` varchar(25) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `PASSWORD` varchar(25) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login
-- ----------------------------
INSERT INTO `login` VALUES ('admin', '12345');

-- ----------------------------
-- Table structure for login_history
-- ----------------------------
DROP TABLE IF EXISTS `login_history`;
CREATE TABLE `login_history`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `login_time` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login_history
-- ----------------------------

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `roomnumber` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `availability` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `cleaning_status` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `price` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `bed_type` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `room_number` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `available` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (NULL, NULL, 'Dirty', '853', 'Single Bed', '125', 'Available');
INSERT INTO `room` VALUES (NULL, NULL, 'Dirty', '10000', 'Single Bed', '122', 'Available');
INSERT INTO `room` VALUES (NULL, NULL, 'Cleaned', '124', 'Single Bed', '12', 'Available');
INSERT INTO `room` VALUES (NULL, NULL, 'Cleaned', '54365', 'Single Bed', '12', 'Available');
INSERT INTO `room` VALUES (NULL, NULL, 'Dirty', '15000', 'Double Bed', '001', 'Available');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `password` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `role` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT 'customer',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (3, 'employee', 'emp001', 'employee');
INSERT INTO `users` VALUES (6, 'admin_user', 'adminpass', 'admin');
INSERT INTO `users` VALUES (9, 'manager', 'm001', 'employee');
INSERT INTO `users` VALUES (12, 'wanidu', 'waniya', 'customer');
INSERT INTO `users` VALUES (13, 'admin', '12345', 'admin');
INSERT INTO `users` VALUES (14, 'sanam', 'samma', 'admin');
INSERT INTO `users` VALUES (15, 'pubudu', 'pubba', 'admin');
INSERT INTO `users` VALUES (16, 'chami', 'chami123', 'admin');
INSERT INTO `users` VALUES (17, 'vijay', 'vajay', 'customer');
INSERT INTO `users` VALUES (18, 'gayani', 'gayani', 'admin');
INSERT INTO `users` VALUES (19, 'gayani', 'gayani', 'customer');

SET FOREIGN_KEY_CHECKS = 1;
