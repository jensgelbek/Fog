SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS sælger;
CREATE TABLE sælger (
                        id int AUTO_INCREMENT NOT NULL,
                        name VARCHAR(255) NOT NULL,
                        userName VARCHAR(255) NOT NULL,
                        salt BINARY(16),
                        secret BINARY(32),
                        PRIMARY KEY (id)
) ENGINE=InnoDB;

-- ----------------------------
-- Table structure for properties
-- ----------------------------
DROP TABLE IF EXISTS `properties`;
CREATE TABLE `properties` (
                              name varchar(255) NOT NULL,
                              value varchar(255) NOT NULL,
                              PRIMARY KEY (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of properties
-- ----------------------------
BEGIN;
INSERT INTO `properties` VALUES ('version', '3');
COMMIT;

SET FOREIGN_KEY_CHECKS=1;


DROP TABLE IF EXISTS materialer;
CREATE TABLE materialer (
    id int AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description name VARCHAR(255) NOT NULL,
    pris int not null,
    PRIMARY KEY (id)
) ENGINE=InnoDB;


