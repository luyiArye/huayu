/*
Navicat MySQL Data Transfer

Source Server         : localhost_root
Source Server Version : 50140
Source Host           : localhost:3306
Source Database       : huayu

Target Server Type    : MYSQL
Target Server Version : 50140
File Encoding         : 65001

Date: 2015-03-05 23:00:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hy_category_t
-- ----------------------------
DROP TABLE IF EXISTS `hy_category_t`;
CREATE TABLE `hy_category_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `category_name` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `category_desc` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for hy_comment_resources_t
-- ----------------------------
DROP TABLE IF EXISTS `hy_comment_resources_t`;
CREATE TABLE `hy_comment_resources_t` (
  `comment_id` int(11) DEFAULT NULL,
  `resource_id` int(11) DEFAULT NULL,
  KEY `FK_Reference_12` (`comment_id`),
  KEY `FK_Reference_13` (`resource_id`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`comment_id`) REFERENCES `hy_production_comment_t` (`id`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`resource_id`) REFERENCES `hy_resources_t` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for hy_production_comment_t
-- ----------------------------
DROP TABLE IF EXISTS `hy_production_comment_t`;
CREATE TABLE `hy_production_comment_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ref_id` int(11) DEFAULT NULL,
  `content` text COLLATE utf8_bin,
  `production_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_10` (`production_id`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`production_id`) REFERENCES `hy_production_t` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for hy_production_operation_t
-- ----------------------------
DROP TABLE IF EXISTS `hy_production_operation_t`;
CREATE TABLE `hy_production_operation_t` (
  `production_id` int(11) DEFAULT NULL,
  `operation_type` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `operation_date` datetime DEFAULT NULL,
  `operation_desc` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  KEY `FK_Reference_11` (`production_id`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`production_id`) REFERENCES `hy_production_t` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for hy_production_resources_t
-- ----------------------------
DROP TABLE IF EXISTS `hy_production_resources_t`;
CREATE TABLE `hy_production_resources_t` (
  `production_id` int(11) DEFAULT NULL,
  `resource_id` int(11) DEFAULT NULL,
  `description` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `seq_no` int(11) DEFAULT NULL,
  KEY `FK_Reference_8` (`production_id`),
  KEY `FK_Reference_9` (`resource_id`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`production_id`) REFERENCES `hy_production_t` (`id`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`resource_id`) REFERENCES `hy_resources_t` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for hy_production_t
-- ----------------------------
DROP TABLE IF EXISTS `hy_production_t`;
CREATE TABLE `hy_production_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `content` text COLLATE utf8_bin,
  `category_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `lastupdated_by` int(11) DEFAULT NULL,
  `lastupdated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_14` (`category_id`),
  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`category_id`) REFERENCES `hy_category_t` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for hy_resources_t
-- ----------------------------
DROP TABLE IF EXISTS `hy_resources_t`;
CREATE TABLE `hy_resources_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `resource_path` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `resource_type` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `content_type` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `resource_size` int(11) DEFAULT NULL,
  `resource_width` int(11) DEFAULT NULL,
  `resource_height` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for hy_user_head_ico_t
-- ----------------------------
DROP TABLE IF EXISTS `hy_user_head_ico_t`;
CREATE TABLE `hy_user_head_ico_t` (
  `user_id` int(11) DEFAULT NULL,
  `resource_id` int(11) DEFAULT NULL,
  `head_ico_type` int(11) DEFAULT NULL,
  KEY `FK_Reference_15` (`user_id`),
  KEY `FK_Reference_16` (`resource_id`),
  CONSTRAINT `FK_Reference_15` FOREIGN KEY (`user_id`) REFERENCES `tpl_user_t` (`ID`),
  CONSTRAINT `FK_Reference_16` FOREIGN KEY (`resource_id`) REFERENCES `hy_resources_t` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tpl_operations_logs_t
-- ----------------------------
DROP TABLE IF EXISTS `tpl_operations_logs_t`;
CREATE TABLE `tpl_operations_logs_t` (
  `USER_ID` int(11) DEFAULT NULL,
  `OPERATION_DATE` datetime DEFAULT NULL,
  `PERMISSIONS_KEY` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `FUNCTION_URL` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `IP_ADDRESS` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `OPERATION_DESC` longtext COLLATE utf8_bin,
  `OPERATION_PARAMS` longtext COLLATE utf8_bin,
  `OPERATION_STATUS` int(11) DEFAULT NULL,
  KEY `TPL_OPERATIONS_LOGS_N1` (`OPERATION_DATE`),
  KEY `TPL_OPERATIONS_LOGS_N2` (`USER_ID`,`PERMISSIONS_KEY`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tpl_permissions_limit_t
-- ----------------------------
DROP TABLE IF EXISTS `tpl_permissions_limit_t`;
CREATE TABLE `tpl_permissions_limit_t` (
  `PERMISSIONS_KEY` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `OPERATE_USER_ID` int(11) DEFAULT NULL,
  `LIMIT_DATE` datetime DEFAULT NULL,
  `LIMIT_DESC` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  UNIQUE KEY `TPL_PERMISSIONS_LIMIT_U1` (`PERMISSIONS_KEY`,`USER_ID`) USING BTREE,
  KEY `FK_REFERENCE_6` (`USER_ID`),
  KEY `FK_REFERENCE_7` (`OPERATE_USER_ID`),
  CONSTRAINT `FK_PL_P` FOREIGN KEY (`PERMISSIONS_KEY`) REFERENCES `tpl_permissions_t` (`PERMISSIONS_KEY`),
  CONSTRAINT `FK_REFERENCE_6` FOREIGN KEY (`USER_ID`) REFERENCES `tpl_user_t` (`ID`),
  CONSTRAINT `FK_REFERENCE_7` FOREIGN KEY (`OPERATE_USER_ID`) REFERENCES `tpl_user_t` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tpl_permissions_t
-- ----------------------------
DROP TABLE IF EXISTS `tpl_permissions_t`;
CREATE TABLE `tpl_permissions_t` (
  `PERMISSIONS_KEY` varchar(200) COLLATE utf8_bin NOT NULL,
  `PARENT_KEY` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `PERMISSIONS_DESC` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`PERMISSIONS_KEY`),
  KEY `PERMISSIONS_KEY` (`PERMISSIONS_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tpl_role_permissions_ref_t
-- ----------------------------
DROP TABLE IF EXISTS `tpl_role_permissions_ref_t`;
CREATE TABLE `tpl_role_permissions_ref_t` (
  `ROLE_ID` int(11) DEFAULT NULL,
  `PERMISSIONS_KEY` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LASTUPDATED_DATE` datetime DEFAULT NULL,
  `LASTUPDATED_BY` int(11) DEFAULT NULL,
  KEY `FK_RP_P` (`PERMISSIONS_KEY`),
  KEY `FK_ROLE_REFERENCE_USER` (`ROLE_ID`,`PERMISSIONS_KEY`) USING BTREE,
  CONSTRAINT `FK_ROLE_REFERENCE_USER` FOREIGN KEY (`ROLE_ID`) REFERENCES `tpl_user_role_t` (`ID`),
  CONSTRAINT `FK_RP_P` FOREIGN KEY (`PERMISSIONS_KEY`) REFERENCES `tpl_permissions_t` (`PERMISSIONS_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tpl_user_role_ref_t
-- ----------------------------
DROP TABLE IF EXISTS `tpl_user_role_ref_t`;
CREATE TABLE `tpl_user_role_ref_t` (
  `ROLE_ID` int(11) DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `EXPIRY_DATE` datetime DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LASTUPDATED_DATE` datetime DEFAULT NULL,
  `LASTUPDATED_BY` int(11) DEFAULT NULL,
  UNIQUE KEY `TPL_USER_ROLE_REF_U1` (`ROLE_ID`,`USER_ID`) USING BTREE,
  KEY `FK_USER_REFERENCE_USER` (`USER_ID`),
  CONSTRAINT `FK_USER_REFERENCE_USER` FOREIGN KEY (`USER_ID`) REFERENCES `tpl_user_t` (`ID`),
  CONSTRAINT `FK_USER_REF_USER2` FOREIGN KEY (`ROLE_ID`) REFERENCES `tpl_user_role_t` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tpl_user_role_t
-- ----------------------------
DROP TABLE IF EXISTS `tpl_user_role_t`;
CREATE TABLE `tpl_user_role_t` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `ROLE_DESC` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `ADMIN_ROLE` int(11) DEFAULT '0',
  `DEFAULT_ROLE` int(11) DEFAULT '0',
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LASTUPDATED_DATE` datetime DEFAULT NULL,
  `LASTUPDATED_BY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tpl_user_t
-- ----------------------------
DROP TABLE IF EXISTS `tpl_user_t`;
CREATE TABLE `tpl_user_t` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ACCOUNT` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `USER_NAME` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `USER_PASSWORD` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `USER_DESC` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `SYSTEM_USER` int(11) DEFAULT '0',
  `USER_STATUS` int(11) DEFAULT '1',
  `ACCOUNT_TYPE` int(11) DEFAULT '1',
  `signature_img_id` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LASTUPDATED_DATE` datetime DEFAULT NULL,
  `LASTUPDATED_BY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
