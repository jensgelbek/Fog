SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS volumeMaterialer;
DROP TABLE IF EXISTS unitmaterialer;

DROP TABLE IF EXISTS materialer;
CREATE TABLE materialer (
                            id int AUTO_INCREMENT NOT NULL,
                            name VARCHAR(255) NOT NULL,
                            details VARCHAR(255) NOT NULL,
                            pris int not null,
                            PRIMARY KEY (id)

) ENGINE=InnoDB;


CREATE TABLE volumeMaterialer (
                                  volumeMaterialeId int,
                                  length int NOT NULL,
                                  PRIMARY KEY (volumeMaterialeId),
                                  KEY materiale (volumeMaterialeId),
                                  CONSTRAINT materiale FOREIGN KEY (volumeMaterialeId) REFERENCES materialer(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE unitMaterialer (
                                unitMaterialeId int,
                                unitType VARCHAR(255) NOT NULL,
                                PRIMARY KEY (unitMaterialeId),
                                KEY materiale2 (unitMaterialeId),
                                CONSTRAINT materiale2 FOREIGN KEY (unitMaterialeId) REFERENCES materialer(id) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB;

DROP TABLE IF EXISTS volumeMaterialer;
CREATE TABLE volumeMaterialer (
                                  volumeMaterialeId int,
                                  length int NOT NULL,
                                  PRIMARY KEY (volumeMaterialeId),
                                  KEY materiale (volumeMaterialeId),
                                  CONSTRAINT materiale FOREIGN KEY (volumeMaterialeId) REFERENCES materialer(id) ON DELETE CASCADE ON UPDATE CASCADE
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
INSERT INTO `properties` VALUES ('version', '4');

SET FOREIGN_KEY_CHECKS=1;



