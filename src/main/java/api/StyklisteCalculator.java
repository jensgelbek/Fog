package api;

import domain.items.Carport;
import domain.items.DBException;
import domain.materials.*;
import infrastructure.*;
import web.pages.Bestilling;

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


    private final Webapp api;

    public StyklisteCalculator(Webapp api) {
        this.api = api;
    }

    // Stern calculations, Width: Front + back
    public StykListeLinje sternWidthCalc(int width) throws DBException {
        StykListeLinje sternWidth;
        int widthCalc = width;
        System.out.println(widthCalc);
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(understern, widthCalc);
        String description = "Understernbrædder til for og bag ende";
        int quantity = 2;
        sternWidth = new StykListeLinje(volumeMaterial, quantity, description);
        // System.out.println("sternWidth: " + sternWidth);
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
        // System.out.println("sternLength: " + sternLength);
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
        // System.out.println("overSternLength: " + overSternLength);
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
        // System.out.println("overSternWidth: " + overSternWidth);
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
        // System.out.println("remLength: " + remLength);
        return remLength;
    }

    public StykListeLinje spaerCalc(int width, int length) {
        StykListeLinje spaers;
        int spaerWidth = width;
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(spaer, spaerWidth);
        String description = "Spær: Monteres på rem";
        int quantity = (length / 600) - 1;
        spaers = new StykListeLinje(volumeMaterial, quantity, description);
        System.out.println("spaers: " + spaers);
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
        // System.out.println("stolper: " + stolper);
        return stolper;
    }

    public StykListeLinje hulbaandCalc() {
        StykListeLinje hulbaandRulle;
        UnitMaterial unitMaterial = api.findUnitMaterial(hulbaand);
        String description = "Hulbånd";
        int quantity = 1;
        hulbaandRulle = new StykListeLinje(unitMaterial, quantity, description);
        // System.out.println("Hulbånd: " + hulbaandRulle);
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
        System.out.println("tempQuantityLength: " + tempQuantityLength);
        int tempQuantityWidth = ((shedWidth / 150) *2)*2;
        System.out.println("tempQuantityWidth: " + tempQuantityWidth);

        int quantity =  (tempQuantityLength + tempQuantityWidth);
        shedBoards = new StykListeLinje(volumeMaterial, quantity, description);
        //System.out.println("shedBoards: " + shedBoards);
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
        System.out.println(" zBackOfDoorCalc: " +  zBackOfDoorCalc);
        return zBackOfDoorCalc;
    }


    public StykListeLinje shedReglaerGavlCalc(int shedWidth) {
        StykListeLinje shedReglarGavl;

        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(reglar, shedWidth);
        String description = "løsholter til skur gavle";
        int quantity = 2;
        shedReglarGavl = new StykListeLinje(volumeMaterial, quantity, description);
        System.out.println("shedReglarGavl: " +  shedReglarGavl);
        return shedReglarGavl;
    }

    public StykListeLinje shedReglaerSideCalc(int shedLength) {
        StykListeLinje shedReglarSide;

        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(reglar, shedLength);
        String description = "løsholter til skur sider";
        int quantity = 2;
        shedReglarSide = new StykListeLinje(volumeMaterial, quantity, description);
        System.out.println("shedReglarSide: " +  shedReglarSide);
        return shedReglarSide;
    }

    public StykListeLinje waterBoardSidesCalc(int length) {
        StykListeLinje waterBoardSides;
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(braedt, length);
        String description = "vandbrædt på stern i sider";
        int quantity = 2;
        waterBoardSides = new StykListeLinje(volumeMaterial, quantity, description);
        System.out.println("waterBoardSides: " +  waterBoardSides);
        return waterBoardSides;
    }

    public StykListeLinje waterBoardEndCalc(int width) {
        StykListeLinje waterBoardEnd;
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(braedt, width);
        String description = "vandbrædt på stern i forende";
        int quantity = 1;
        waterBoardEnd = new StykListeLinje(volumeMaterial, quantity, description);
        System.out.println("waterBoardSides: " +  waterBoardEnd);
        return waterBoardEnd;
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
            stykliste.volumenListe.add(tagFladtResidueCalc(width, length));
        }
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
        // Add waterboard sides
        stykliste.volumenListe.add(waterBoardSidesCalc(length));
        // Add waterboard front
        stykliste.volumenListe.add(waterBoardEndCalc(width));




        return stykliste;
    }


}
