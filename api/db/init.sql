SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `uuid` varchar(36) NOT NULL COMMENT '主键UUID',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '姓名',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `gender` int(1) NOT NULL DEFAULT '0' COMMENT '性别0:未知,1:男,2:女',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;