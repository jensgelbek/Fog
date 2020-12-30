package api;

import api.StyklisteCalculator;
import api.Webapp;
import domain.items.DBException;
import domain.materials.StykListeLinje;
import infrastructure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StyklisteCalculatorTest {


    Database db = new Database();
    Webapp api = new Webapp(new DBOrderRepository(db), new DBCustomerRepository(db), new DBCarportRepository(db), new DBSellerRepository(db), new DBVolumeMateialRepository(db), new DBUnitMaterialRepository(db), new DBStyklisteLinjeRepository(db), new DBStyklisteRepository(db), new DBMaterialRepository(db));
    StyklisteCalculator styklisteCalculator = new StyklisteCalculator(api);



    @BeforeEach
    void setUp() {
    }

    @Test
    void sternWidthCalc() throws DBException {
        StykListeLinje stykListeLinje = styklisteCalculator.sternWidthCalc(6000);
        assertEquals(6000, stykListeLinje.getMateriale().getLength());
    }

    @Test
    void sternLengthCalc() throws DBException {
        StykListeLinje stykListeLinje = styklisteCalculator.sternLengthCalc(6000);
        assertEquals(6000, stykListeLinje.getMateriale().getLength());
    }

    @Test
    void overSternLengthCalc() throws DBException {
        StykListeLinje stykListeLinje = styklisteCalculator.overSternLengthCalc(6000);
        assertEquals(6000, stykListeLinje.getMateriale().getLength());
    }

    @Test
    void overSternWidthCalc() throws DBException {
        StykListeLinje stykListeLinje = styklisteCalculator.overSternWidthCalc(6000);
        assertEquals(6000, stykListeLinje.getMateriale().getLength());
    }

    @Test
    void remCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.remCalc(6000);
        assertEquals(6000, stykListeLinje.getMateriale().getLength());
    }

    @Test
    void spaerCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.remCalc(6000);
        assertEquals(6000, stykListeLinje.getMateriale().getLength());
    }

    @Test
    void stolperCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.stolperCalc(6000);
        assertEquals(3000, stykListeLinje.getMateriale().getLength());
    }

    @Test
    void hulbaandCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.hulbaandCalc();
        assertEquals(1, stykListeLinje.getQuantity());
    }

    @Test
    void tagFladtCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.tagFladtCalc(6000, 6000);
        assertEquals(6000, stykListeLinje.getMateriale().getLength());
    }

    @Test
    void tagFladtResidueCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.tagFladtResidueCalc(6000, 6000);
        assertEquals(6000, stykListeLinje.getMateriale().getLength());
    }

    @Test
    void shedBoards() {
        StykListeLinje stykListeLinje = styklisteCalculator.shedBoards(5000, 5000);
        assertEquals(2100, stykListeLinje.getMateriale().getLength());
    }

    @Test
    void shedStolperCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.shedStolperCalc(3000, 3000, 3000);
        assertEquals(3000, stykListeLinje.getMateriale().getLength());
    }

    @Test
    void zBackOfDoorCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.zBackOfDoorCalc();
        assertEquals(1, stykListeLinje.getQuantity());
    }

    @Test
    void shedReglaerGavlCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.shedReglaerGavlCalc(6000);
        assertEquals(6000, stykListeLinje.getMateriale().getLength());
    }

    @Test
    void shedReglaerSideCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.shedReglaerSideCalc(6000);
        assertEquals(6000, stykListeLinje.getMateriale().getLength());
    }

    @Test
    void waterBoardSidesCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.waterBoardSidesCalc(6000);
        assertEquals(6000, stykListeLinje.getMateriale().getLength());
    }

    @Test
    void waterBoardEndCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.waterBoardEndCalc(6000);
        assertEquals(6000, stykListeLinje.getMateriale().getLength());
    }

    @Test
    void bundskrueCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.bundskrueCalc(6000, 6000);
        assertEquals(3, stykListeLinje.getQuantity());
    }

    @Test
    void universalRigthCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.universalRigthCalc(6000);
        assertEquals(9, stykListeLinje.getQuantity());
    }

    @Test
    void universalLeftCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.universalLeftCalc(6000);
        assertEquals(9, stykListeLinje.getQuantity());
    }

    @Test
    void skruerCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.skruerCalc(6000);
        assertEquals(1, stykListeLinje.getQuantity());
    }

    @Test
    void beslagSkruerCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.beslagSkruerCalc(6000);
        assertEquals(1, stykListeLinje.getQuantity());
    }

    @Test
    void braeddeboltCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.braeddeboltCalc(6000, 3000);
        assertEquals(18, stykListeLinje.getQuantity());

    }

    @Test
    void firkantskiverCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.firkantskiverCalc(6000);
        assertEquals(12, stykListeLinje.getQuantity());
    }

    @Test
    void skruerOuterShedCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.skruerOuterShedCalc(3000, 3000);
        assertEquals(1, stykListeLinje.getQuantity());
    }

    @Test
    void skruerInnerShedCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.skruerInnerShedCalc(3000, 3000);
        assertEquals(1, stykListeLinje.getQuantity());
    }

    @Test
    void stalddoergrebCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.stalddoergrebCalc();
        assertEquals(1, stykListeLinje.getQuantity());
    }

    @Test
    void haengselCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.haengselCalc();
        assertEquals(2, stykListeLinje.getQuantity());
    }

    @Test
    void vinkelbeslagCalc() {
        StykListeLinje stykListeLinje = styklisteCalculator.vinkelbeslagCalc(3000, 3000);
        assertEquals(24, stykListeLinje.getQuantity());
    }

}