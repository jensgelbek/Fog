SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS ordre;
CREATE TABLE ordre (
    id int AUTO_INCREMENT NOT NULL,
    tilbudsDato timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ordreDato timestamp DEFAULT CURRENT_TIMESTAMP,
    leveringsDato VARCHAR(255),
    kundeEmail VARCHAR(255),

    carportId int NOT NULL,
    pris int NOT NULL,
    status VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    KEY kunde (kundeEmail),

    KEY carport (carportId),
    CONSTRAINT kunde FOREIGN KEY (kundeEmail) REFERENCES kunde(email) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT carport FOREIGN KEY (carportId) REFERENCES carport(id) ON DELETE CASCADE ON UPDATE CASCADE
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
INSERT INTO `properties` VALUES ('version', '11');
COMMIT;
SET FOREIGN_KEY_CHECKS=1;