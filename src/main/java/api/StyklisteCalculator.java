package api;

import domain.items.DBException;
import domain.materials.*;

public class StyklisteCalculator {

    private static final String stolpe = "stolpe";
    private static final String spaer = "spær/rem";
    private static final String hulbaand = "hulbånd";
    private static final String rem = "spær/rem";
    private static final String understern = "understern";
    private static final String overstern = "overstern";
    private static final String braedt = "brædt";
    private static final String reglar = "reglar";
    private static final String fladtTag = "trapez";


    private final Fog api;

    public StyklisteCalculator(Fog api) {
        this.api = api;
    }

    // Stern calculations, Width: Front + back
    public StykListeLinje sternWidthCalc(int width) throws DBException {
        StykListeLinje sternWidth;
        int widthCalc = width;
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(understern, widthCalc);
        String description = "Understernbrædder til for og bag ende";
        int quantity = 2;
        sternWidth = new StykListeLinje(volumeMaterial, quantity, description);
        return sternWidth;
    }

    // Stern calculations, Length: 2, one on each side
    public StykListeLinje sternLengthCalc(int length) throws DBException {
        StykListeLinje sternLength;
        int lengthCalc = length;
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(understern, lengthCalc);
        String description = "Understernbrædder til siderne.";
        int quantity = 2;
        sternLength = new StykListeLinje(volumeMaterial, quantity, description);
        return sternLength;
    }

    // Overstern calculations. Length: 2, one on each side
    public StykListeLinje overSternLengthCalc(int length) throws DBException {
        StykListeLinje overSternLength;
        int lengthCalc = length;
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(overstern, lengthCalc);
        String description = "Oversternbrædder til siderne.";
        int quantity = 2;
        overSternLength = new StykListeLinje(volumeMaterial, quantity, description);
        return overSternLength;
    }

    // Overstern calculations. Width: One in front
    public StykListeLinje overSternWidthCalc(int width) throws DBException {
        StykListeLinje overSternWidth;
        int widthCalc = width;
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(overstern, widthCalc);
        String description = "Understernbrædt til front.";
        int quantity = 1;
        overSternWidth = new StykListeLinje(volumeMaterial, quantity, description);
        return overSternWidth;
    }

    // Rem calculations, Length: 2, one on each side
    public StykListeLinje remCalc(int length) {
        StykListeLinje remLength;
        int lengthCalc = length;
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(rem, lengthCalc);
        String description = "Rem i siderne";
        int quantity = 2;
        remLength = new StykListeLinje(volumeMaterial, quantity, description);
        return remLength;
    }

    public StykListeLinje spaerCalc(int width, int length) {
        StykListeLinje spaers;
        int spaerWidth = width;
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(spaer, spaerWidth);
        String description = "Spær: Monteres på rem";
        int quantity = (length / 600) - 1;
        spaers = new StykListeLinje(volumeMaterial, quantity, description);
        return spaers;
    }

    public StykListeLinje stolperCalc(int length) {
        StykListeLinje stolper;
        int carportLength = length;
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(stolpe, 3000);
        String description = "Stolper nedgraves 90cm. i jord";
        int tempQuantity = 0;
        if (carportLength >= 6000) {
            tempQuantity = 6;
        } else {
            tempQuantity = 4;
        }

        int quantity = tempQuantity;
        stolper = new StykListeLinje(volumeMaterial, quantity, description);
        return stolper;
    }

    public StykListeLinje hulbaandCalc() {
        StykListeLinje hulbaandRulle;
        UnitMaterial unitMaterial = api.findUnitMaterial(hulbaand);
        String description = "Hulbånd";
        int quantity = 1;
        hulbaandRulle = new StykListeLinje(unitMaterial, quantity, description);
        return hulbaandRulle;
    }

    public StykListeLinje tagFladtCalc(int width, int length) {
        StykListeLinje tagFladt;
        int tempLength = 0;

        if (length  < 3600) {
            tempLength = 3600;
        } else {
            tempLength = 6000;
        }

        int tagFladtLength = tempLength;
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(fladtTag, tagFladtLength);
        String description = "Tagplader monteres på spær";

        int quantity = (int) Math.ceil(width / 1000.0);

        tagFladt = new StykListeLinje(volumeMaterial, quantity, description);
        return tagFladt;
    }

    public StykListeLinje tagFladtResidueCalc(int width, int length) {
        StykListeLinje tagFladtResidue;
        int tempLength = 0;

        if (length < 3600) {
            tempLength = 3600;
        } else {
            tempLength = 6000;
        }

        int tagFladtLength = tempLength;

        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(fladtTag, tagFladtLength);
        String description = "Tagplader monteres på spær";

        int quantity = (int) Math.ceil(width / 1000.0);

        tagFladtResidue = new StykListeLinje(volumeMaterial, quantity, description);
        return tagFladtResidue;
    }

    public StykListeLinje shedBoards(int shedWidth, int shedLength) {
        StykListeLinje shedBoards;
        int length = shedLength;
        int width = shedWidth;

        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(braedt, 2100);
        String description = "Brædder til beklædning af skur 1 på 2";

        int tempQuantityLength = ((shedLength / 150) *2)*2;
        int tempQuantityWidth = ((shedWidth / 150) *2)*2;

        int quantity =  (tempQuantityLength + tempQuantityWidth);
        shedBoards = new StykListeLinje(volumeMaterial, quantity, description);
        return shedBoards;
    }


    public StykListeLinje shedStolperCalc(int width, int shedWidth, int shedLength) {
        StykListeLinje shedStolper;
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(stolpe, 3000);
        String description = "Stolper nedgraves 90cm. i jord";

        int tempQuantityWidth = 0;
        if (shedWidth > 3000) {
            tempQuantityWidth = 6;
        } else if (shedWidth == width ) {
            tempQuantityWidth = -1;
        } else {
            tempQuantityWidth = 4;
        }

        int tempQuantityLength = 0;
        if (shedLength > 3000) {
            tempQuantityLength = 6;
        } else {
            tempQuantityLength = 4;
        }
        // Korrigerring for dobbelberegning stolper i hjørner = -4
        int quantity = (tempQuantityLength + tempQuantityWidth) - 4;
        shedStolper = new StykListeLinje(volumeMaterial, quantity, description);
        return shedStolper;
    }


    public StykListeLinje zBackOfDoorCalc() {
        StykListeLinje zBackOfDoorCalc;
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght("lægte", 3600);
        String description = "til z på bagside af dør";
        int quantity = 1;
        zBackOfDoorCalc = new StykListeLinje(volumeMaterial, quantity, description);
        return zBackOfDoorCalc;
    }


    public StykListeLinje shedReglaerGavlCalc(int shedWidth) {
        StykListeLinje shedReglarGavl;

        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(reglar, shedWidth);
        String description = "løsholter til skur gavle";
        int quantity = 2;
        shedReglarGavl = new StykListeLinje(volumeMaterial, quantity, description);
        return shedReglarGavl;
    }

    public StykListeLinje shedReglaerSideCalc(int shedLength) {
        StykListeLinje shedReglarSide;

        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(reglar, shedLength);
        String description = "løsholter til skur sider";
        int quantity = 2;
        shedReglarSide = new StykListeLinje(volumeMaterial, quantity, description);
        return shedReglarSide;
    }

    public StykListeLinje waterBoardSidesCalc(int length) {
        StykListeLinje waterBoardSides;
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(braedt, length);
        String description = "vandbrædt på stern i sider";
        int quantity = 2;
        waterBoardSides = new StykListeLinje(volumeMaterial, quantity, description);
        return waterBoardSides;
    }

    public StykListeLinje waterBoardEndCalc(int width) {
        StykListeLinje waterBoardEnd;
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(braedt, width);
        String description = "vandbrædt på stern i forende";
        int quantity = 1;
        waterBoardEnd = new StykListeLinje(volumeMaterial, quantity, description);
        return waterBoardEnd;
    }

    public StykListeLinje bundskrueCalc(int width, int length) {
        StykListeLinje bundskruer;
        UnitMaterial unitMaterial = api.findUnitMaterial("tagskruer");
        String description = "Skruer til tagplader. plastmo bundskruer 200 stk.";

        int skruerPerPakke = 200;
        double m2 = (width/1000) * (length/1000);
        double skruerPerM2 = 12.0;
        double skruerTotalM2 = m2*skruerPerM2;

        int quantity = (int) Math.ceil(skruerTotalM2/skruerPerPakke);

        bundskruer = new StykListeLinje(unitMaterial, quantity, description);
        return bundskruer;
    }

    public StykListeLinje universalRigthCalc(int length) {
        StykListeLinje universalRigth;
        UnitMaterial unitMaterial = api.findUnitMaterial("universal højre");
        String description = "Universalbeslag 190mm højre. Til montering af spær på rem";

        // Number of Spærs
        int quantity = (length / 600) - 1;

        universalRigth = new StykListeLinje(unitMaterial, quantity, description);
        return universalRigth;
    }

    public StykListeLinje universalLeftCalc(int length) {
        StykListeLinje universalLeft;
        UnitMaterial unitMaterial = api.findUnitMaterial("universal venstre");
        String description = "Universalbeslag 190mm venstre. Til montering af spær på rem";

        // Number of Spærs
        int quantity = (length / 600) - 1;

        universalLeft = new StykListeLinje(unitMaterial, quantity, description);
        return universalLeft;
    }

    public StykListeLinje skruerCalc(int length) {
        StykListeLinje skruer;
        UnitMaterial unitMaterial = api.findUnitMaterial("4,5*60 skruer");
        String description = "Til montering af stern & vandbrædt. 200 stk.";
        int quantity = 1;
        skruer = new StykListeLinje(unitMaterial, quantity, description);
        return skruer;
    }

    public StykListeLinje beslagSkruerCalc(int length) {
        StykListeLinje beslagSkruer;
        UnitMaterial unitMaterial = api.findUnitMaterial("beslag skruer");
        String description = "4,0 x 50 mm. Til montering af universalbeslag + hulbånd. 250 stk./pakke";

        int skruerPerPakke = 250;
        // Estimatet 40
        int skruerPerSpaer = 40;
        int spaer = (length / 600) - 1;

        int quantity = (int) Math.ceil((skruerPerSpaer*spaer)/skruerPerPakke);

        beslagSkruer = new StykListeLinje(unitMaterial, quantity, description);
        return beslagSkruer;
    }

    public StykListeLinje braeddeboltCalc(int length, int shedLength) {
        StykListeLinje braeddebolt;
        UnitMaterial unitMaterial = api.findUnitMaterial("bræddebolt");
        String description = "10 x 120 mm. Til montering af rem på stolper.";

        // Carport antal stolper
        int tempQuantity = 0;
        if (length >= 6000) {
            tempQuantity = 6;
        } else {
            tempQuantity = 4;
        }

        // Shed antal stolper længde ( - 1 fra Carport )
        int tempQuantityShed = 0;
        if (shedLength > 3000) {
            tempQuantityShed = 6-1;
        } else {
            tempQuantityShed = 4-1;
        }

        // 2 stk per stolpe
        int quantity = (tempQuantity + tempQuantityShed)*2;

        braeddebolt = new StykListeLinje(unitMaterial, quantity, description);
        return braeddebolt;
    }

    public StykListeLinje firkantskiverCalc(int length) {
        StykListeLinje firkantskiver;
        UnitMaterial unitMaterial = api.findUnitMaterial("firkantskive");
        String description = "40x40x11mm. Til montering af rem på stolper.";

        // Carport antal stolper
        int tempQuantity = 0;
        if (length >= 6000) {
            tempQuantity = 6;
        } else {
            tempQuantity = 4;
        }
        // 2 stk per stolpe
        int quantity = tempQuantity *2;

        firkantskiver = new StykListeLinje(unitMaterial, quantity, description);
        return firkantskiver;
    }

    public StykListeLinje skruerOuterShedCalc(int shedWidth, int shedLength) {
        StykListeLinje skruerOuterShed;
        UnitMaterial unitMaterial = api.findUnitMaterial("4,5*70 skruer");
        String description = "Til montering af yderste beklædning. 400 stk/pakke";

        int tempQuantityLength = ((shedLength / 150) *2)*2;
        int tempQuantityWidth = ((shedWidth / 150) *2)*2;

        // 4 stk per brædt 400 stk/pakke
        int quantity = (int) (Math.ceil((tempQuantityLength + tempQuantityWidth)*4)/400);

        skruerOuterShed = new StykListeLinje(unitMaterial, quantity, description);
        return skruerOuterShed;
    }

    public StykListeLinje skruerInnerShedCalc(int shedWidth, int shedLength) {
        StykListeLinje skruerInnerShed;
        UnitMaterial unitMaterial = api.findUnitMaterial("4,5*70 skruer");
        String description = "Til montering af inderste beklædning. 300 stk/pakke";

        int tempQuantityLength = ((shedLength / 150) *2)*2;
        int tempQuantityWidth = ((shedWidth / 150) *2)*2;

        // 3 stk per brædt
        int quantity = (int) (Math.ceil((tempQuantityLength + tempQuantityWidth)*3)/300);

        skruerInnerShed = new StykListeLinje(unitMaterial, quantity, description);
        return skruerInnerShed;
    }

    public StykListeLinje stalddoergrebCalc() {
        StykListeLinje stalddoergreb;
        UnitMaterial unitMaterial = api.findUnitMaterial("dør greb");
        String description = "Stalddørsgreb 50x75. Til lås på dør til skur";

        int quantity = 1;

        stalddoergreb = new StykListeLinje(unitMaterial, quantity, description);
        return stalddoergreb;
    }

    public StykListeLinje haengselCalc() {
        StykListeLinje haengsel;
        UnitMaterial unitMaterial = api.findUnitMaterial("t hængsel");
        String description = "390mm. Til skurdør";

        int quantity = 2;

        haengsel = new StykListeLinje(unitMaterial, quantity, description);
        return haengsel;
    }

    public StykListeLinje vinkelbeslagCalc(int shedWidth, int shedLangth) {
        StykListeLinje vinkelbeslag;
        UnitMaterial unitMaterial = api.findUnitMaterial("vinkelbeslag");
        String description = "vinkelbeslag 35. Til montering af løsholter i skur";

        // Et beslag per 50 cm.
        int tempQuantityWidth = (shedWidth/500)*2;
        int tempQuantityLength = (shedWidth/500)*2;

        int quantity = tempQuantityWidth + tempQuantityLength;

        vinkelbeslag = new StykListeLinje(unitMaterial, quantity, description);
        return vinkelbeslag;
    }

    
    public Stykliste generereStykliste(int width, int length, int shedWidth, int shedLength) throws DBException {
        Stykliste stykliste = new Stykliste();

        // Add Stern width
        stykliste.volumenListe.add(sternWidthCalc(width));
        // Add Stern length
        stykliste.volumenListe.add(sternLengthCalc(length));
        // Add Overstern length
        stykliste.volumenListe.add(overSternLengthCalc(length));
        // Add Overstern width
        stykliste.volumenListe.add(overSternWidthCalc(width));
        // Add Rem
        stykliste.volumenListe.add(remCalc(length));
        // Add Spaers
        stykliste.volumenListe.add(spaerCalc(width, length));
        // Add Stolper
        stykliste.volumenListe.add(stolperCalc(length));
        // Add Hulbånd
        stykliste.unitListe.add(hulbaandCalc());
        // Add Tag
        stykliste.volumenListe.add(tagFladtCalc(width, length));
        // Add Tag
        if (length - 6000 > 0) {
            length = length - 6000;
            stykliste.volumenListe.add(tagFladtResidueCalc(width, length-6000));
        }

        if (shedLength > 0 ) {
            // Add Skurbrædder
            stykliste.volumenListe.add(shedBoards(shedWidth, shedLength));
            // Add Shed stolper
            stykliste.volumenListe.add(shedStolperCalc(width, shedWidth, shedLength));
            // Add z back of the door
            stykliste.volumenListe.add(zBackOfDoorCalc());
            // Add Reglar gavl
            stykliste.volumenListe.add(shedReglaerGavlCalc(shedWidth));
            // Add Reglar sides
            stykliste.volumenListe.add(shedReglaerSideCalc(shedLength));
            // Add Skruer til yderbeklædning
            stykliste.unitListe.add(skruerOuterShedCalc(shedWidth, shedLength));
            // Add Skruer til inderbeklædning
            stykliste.unitListe.add(skruerInnerShedCalc(shedWidth, shedLength));
            // Add Staldørsgreb
            stykliste.unitListe.add(stalddoergrebCalc());
            // Add Hængsler
            stykliste.unitListe.add(haengselCalc());
            // Add Vinkelbeslag
            stykliste.unitListe.add(vinkelbeslagCalc(shedWidth, shedLength));
        }

        // Add waterboard sides
        stykliste.volumenListe.add(waterBoardSidesCalc(length));
        // Add waterboard front
        stykliste.volumenListe.add(waterBoardEndCalc(width));
        // Add tag bundskruer
        stykliste.unitListe.add(bundskrueCalc(width, length));
        // Add universal right
        stykliste.unitListe.add(universalRigthCalc(length));
        // Add universal left
        stykliste.unitListe.add(universalLeftCalc(length));
        // Add Skruer til Stern og vandbrædt
        stykliste.unitListe.add(skruerCalc(length));
        // Add Beslagskruer
        stykliste.unitListe.add(beslagSkruerCalc(length));
        // Add Breddebolt
        stykliste.unitListe.add(braeddeboltCalc(length, shedLength));
        // Add Firkantskiver
        stykliste.unitListe.add(firkantskiverCalc(length));


        return stykliste;
    }


}
