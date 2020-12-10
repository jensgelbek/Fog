INSERT INTO materialetype (name,details,pris) VALUES ('tagskruer','plastmo bundskruer 200 stk.',210);
INSERT INTO materialer (id,name) VALUES (200,'tagskruer');
INSERT INTO unitmaterialer (unitMaterialeId,unitType) VALUES (200,'pakke');

INSERT INTO materialetype (name,details,pris) VALUES ('hulbånd','hulbånd 1x20 mm. 10 mtr.',110);
INSERT INTO materialer (id,name) VALUES (201,'hulbånd');
INSERT INTO unitmaterialer (unitMaterialeId,unitType) VALUES (201,'rulle');

INSERT INTO materialetype (name,details,pris) VALUES ('universal højre','universal 190 mm højre',37);
INSERT INTO materialer (id,name) VALUES (202,'universal højre');
INSERT INTO unitmaterialer (unitMaterialeId,unitType) VALUES (202,'stk');

INSERT INTO materialetype (name,details,pris) VALUES ('universal venstre','universal 190 mm venstre',37);
INSERT INTO materialer (id,name) VALUES (203,'universal venstre');
INSERT INTO unitmaterialer (unitMaterialeId,unitType) VALUES (203,'stk');

INSERT INTO materialetype (name,details,pris) VALUES ('4,5*60 skruer','4,5 x 60 mm. skruer 200 stk.',178);
INSERT INTO materialer (id,name) VALUES (204,'4,5*60 skruer');
INSERT INTO unitmaterialer (unitMaterialeId,unitType) VALUES (204,'pakke');

INSERT INTO materialetype (name,details,pris) VALUES ('beslag skruer','4,0 x 50 mm. beslagskruer 250 stk.',208);
INSERT INTO materialer (id,name) VALUES (205,'beslag skruer');
INSERT INTO unitmaterialer (unitMaterialeId,unitType) VALUES (205,'pakke');

INSERT INTO materialetype (name,details,pris) VALUES ('bræddebolt','bræddebolt 10 x 120 mm.',23);
INSERT INTO materialer (id,name) VALUES (206,'bræddebolt');
INSERT INTO unitmaterialer (unitMaterialeId,unitType) VALUES (206,'stk');

INSERT INTO materialetype (name,details,pris) VALUES ('firkantskive','firkantskiver 40x40x11mm',23);
INSERT INTO materialer (id,name) VALUES (207,'firkantskive');
INSERT INTO unitmaterialer (unitMaterialeId,unitType) VALUES (207,'stk');

INSERT INTO materialetype (name,details,pris) VALUES ('4,5*70 skruer','4,5 x 70 mm. Skruer 400 stk.',377);
INSERT INTO materialer (id,name) VALUES (208,'4,5*70 skruer');
INSERT INTO unitmaterialer (unitMaterialeId,unitType) VALUES (208,'pakke');

INSERT INTO materialetype (name,details,pris) VALUES ('4,5*50 skruer','4,5 x 50 mm. Skruer 300 stk.',327);
INSERT INTO materialer (id,name) VALUES (209,'4,5*50 skruer');
INSERT INTO unitmaterialer (unitMaterialeId,unitType) VALUES (209,'pakke');

INSERT INTO materialetype (name,details,pris) VALUES ('dør greb','stalddørsgreb 50x75.',117);
INSERT INTO materialer (id,name) VALUES (210,'dør greb');
INSERT INTO unitmaterialer (unitMaterialeId,unitType) VALUES (210,'stk');

INSERT INTO materialetype (name,details,pris) VALUES ('t hængsel','t hængsel 390 mm',37);
INSERT INTO materialer (id,name) VALUES (211,'t hængsel');
INSERT INTO unitmaterialer (unitMaterialeId,unitType) VALUES (211,'stk');

INSERT INTO materialetype (name,details,pris) VALUES ('vinkelbeslag','vinkelbeslag 35',37);
INSERT INTO materialer (id,name) VALUES (212,'vinkelbeslag');
INSERT INTO unitmaterialer (unitMaterialeId,unitType) VALUES (212,'stk');

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
INSERT INTO `properties` VALUES ('version', '9');
COMMIT;

SET FOREIGN_KEY_CHECKS=1;

