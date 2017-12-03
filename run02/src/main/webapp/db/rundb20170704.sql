/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : run

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2017-0   7-04 19:56:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for r_user
-- ----------------------------
DROP TABLE IF EXISTS `r_user`;
CREATE TABLE `r_user` (
  `id` varchar(50) NOT NULL COMMENT '用户主键id',
  `code` varchar(50) NOT NULL COMMENT '用户编码',
  `name` varchar(50) NOT NULL COMMENT '用户名',
  `mobile` varchar(50) default NULL COMMENT '手机号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `last_login_time` datetime NOT NULL COMMENT '最后修改时间',
  `status` int(10) default NULL COMMENT '状态',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of r_user
-- ----------------------------
INSERT INTO `r_user` VALUES ('1', '1', '1', '1', '2017-03-08 21:03:28', '2017-03-22 21:03:32', '1');
INSERT INTO `r_user` VALUES ('2', '2', '2', '2', '2017-06-15 20:42:05', '2017-06-24 20:42:09', '1');
INSERT INTO `r_user` VALUES ('287a807a541082da279fa0808666cfe6', '502', '502', '502', '2017-06-27 22:57:59', '2017-06-27 22:57:59', '1');
INSERT INTO `r_user` VALUES ('28855cf35650f300b48fbd44390ab5c3', '503', '503', '503', '2017-06-27 23:00:46', '2017-06-27 23:00:46', '1');
INSERT INTO `r_user` VALUES ('2c7fadff043ed55b54b50019fb6850cd', '501', '501', '501', '2017-06-27 22:56:43', '2017-06-27 22:56:43', '1');
INSERT INTO `r_user` VALUES ('3', '3', '3', '3', '2017-06-29 19:07:13', '2017-06-30 19:07:18', '1');
INSERT INTO `r_user` VALUES ('37e5bd39fc5c8ec67280fcd9d5a91621', '506', '506', '506', '2017-06-27 23:02:25', '2017-06-27 23:02:25', '1');
INSERT INTO `r_user` VALUES ('4', '4', '4', '4', '2017-06-28 19:07:32', '2017-06-21 19:07:35', '1');
INSERT INTO `r_user` VALUES ('5', '5', '5', '5', '2017-06-21 19:07:49', '2017-06-20 19:07:52', '1');
INSERT INTO `r_user` VALUES ('6', '6', '6', '6', '2017-06-21 19:08:03', '2017-06-28 19:08:07', '1');
INSERT INTO `r_user` VALUES ('7', '7', '7', '7', '2017-06-28 19:08:21', '2017-06-27 19:08:25', '0');
INSERT INTO `r_user` VALUES ('75893f0a5dea5267527c5569bafd47c3', '502', '502', '502', '2017-06-27 22:58:16', '2017-06-27 22:58:16', '1');
INSERT INTO `r_user` VALUES ('ab77b6fb10ad69cdc6a3665eed373fb4', '123', '3434', null, '2017-06-27 22:53:19', '2017-06-27 22:53:19', '1');
INSERT INTO `r_user` VALUES ('b75bd91db3ee49af0d9132bd7af2bb2c', '504', '504', '504', '2017-06-27 23:01:44', '2017-06-27 23:01:44', '1');
