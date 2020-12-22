DROP TABLE IF EXISTS kunde;
CREATE TABLE kunde
(
    email     VARCHAR(255) NOT NULL,
    name      VARCHAR(255) NOT NULL,
    addresse  VARCHAR(255) NOT NULL,
    tlfNummer int          NOT NULL,
    salt      BINARY(16),
    secret    BINARY(32),
    PRIMARY KEY (email)
) ENGINE = InnoDB;


DROP TABLE IF EXISTS sælger;
CREATE TABLE sælger
(
    id   int AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)       NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;


DROP TABLE IF EXISTS materialer;
CREATE TABLE materialer
(
    id   int AUTO_INCREMENT NOT NULL,
    pris int                not null,
    PRIMARY KEY (id)
) ENGINE = InnoDB;


DROP TABLE IF EXISTS unitmaterialer;
CREATE TABLE unitmaterialer
(
    unitMaterialeId int,
    unitType        VARCHAR(255) NOT NULL,
    PRIMARY KEY (unitMaterialeId),
    KEY materiale2 (unitMaterialeId),
    CONSTRAINT materiale2 FOREIGN KEY (unitMaterialeId) REFERENCES materialer (id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;


DROP TABLE IF EXISTS volumematerialer;
CREATE TABLE volumematerialer
(
    volumeMaterialeId int,
    bredde            int NOT NULL,
    højde             int NOT NULL,
    længde            int NOT NULL,
    PRIMARY KEY (volumeMaterialeId),
    KEY materiale (volumeMaterialeId),
    CONSTRAINT materiale FOREIGN KEY (volumeMaterialeId) REFERENCES materialer (id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;


DROP TABLE IF EXISTS carport;
CREATE TABLE carport
(
    id       int AUTO_INCREMENT NOT NULL,
    bredde   int                NOT NULL,
    længde   int                NOT NULL,
    rejsning boolean            NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;


DROP TABLE IF EXISTS skur;
CREATE TABLE skur
(
    id     int AUTO_INCREMENT NOT NULL,
    bredde int                NOT NULL,
    længde int                NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

DROP TABLE IF EXISTS stykliste;

DROP TABLE IF EXISTS ordre;
CREATE TABLE ordre
(
    id            int AUTO_INCREMENT NOT NULL,
    tilbudsDato   timestamp          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ordreDato     timestamp          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    leveringsDato VARCHAR(255),
    kundeID       VARCHAR(255),
    sælgerID      int                NOT NULL,
    skurId        int                NOT NULL,
    carportId     int                NOT NULL,
    pris          int                NOT NULL,
    status        int                NOT NULL,
    PRIMARY KEY (id),
    KEY kunde (kundeID),
    KEY sælger (sælgerID),
    KEY skur (skurId),
    KEY carport (carportId),
    CONSTRAINT kunde FOREIGN KEY (kundeID) REFERENCES kunde (email) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT sælger FOREIGN KEY (sælgerID) REFERENCES sælger (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT skur FOREIGN KEY (skurId) REFERENCES skur (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT carport FOREIGN KEY (carportId) REFERENCES carport (id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;


-- ----------------------------
-- Table structure for properties
-- ----------------------------
DROP TABLE IF EXISTS `properties`;
CREATE TABLE `properties`
(
    name  varchar(255) NOT NULL,
    value varchar(255) NOT NULL,
    PRIMARY KEY (name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of properties
-- ----------------------------
BEGIN;
INSERT INTO `properties`
VALUES ('version', '1');
COMMIT;


