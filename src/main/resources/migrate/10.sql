SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS styklistelinje;
CREATE TABLE styklistelinje (
                       id int AUTO_INCREMENT NOT NULL,
                       ordreid int NOT NULL,
                       materialid int NOT NULL,
                       antal int NOT NULL,
                       PRIMARY KEY (id),
                       KEY ordre (ordreid),
                       KEY materialer (materialid),
                       CONSTRAINT ordre FOREIGN KEY (ordreid) REFERENCES ordre(id) ON DELETE CASCADE ON UPDATE CASCADE,
                       CONSTRAINT materialer FOREIGN KEY (materialid) REFERENCES materialer(id) ON DELETE CASCADE ON UPDATE CASCADE
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
INSERT INTO `properties` VALUES ('version', '10');
COMMIT;
SET FOREIGN_KEY_CHECKS=1;