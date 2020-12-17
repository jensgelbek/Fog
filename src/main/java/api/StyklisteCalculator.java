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
        int widthCalc = width * 10;
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
        int lengthCalc = length * 10;
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
        int lengthCalc = length * 10;
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
        int widthCalc = width * 10;
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
        int lengthCalc = length * 10;
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(rem, lengthCalc);
        String description = "Rem i siderne";
        int quantity = 2;
        remLength = new StykListeLinje(volumeMaterial, quantity, description);
        // System.out.println("remLength: " + remLength);
        return remLength;
    }

    public StykListeLinje spaerCalc(int width, int length) {
        StykListeLinje spaers;
        int spaerWidth = width * 10;
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(spaer, spaerWidth);
        String description = "Spær: Monteres på rem";
        int quantity = (length / 60) - 1;
        spaers = new StykListeLinje(volumeMaterial, quantity, description);
        // System.out.println("spaers: " + spaers);
        return spaers;
    }

    public StykListeLinje stolperCalc(int length) {
        StykListeLinje stolper;
        int carportLength = length * 10;
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(stolpe, carportLength);
        String description = "Stolper";
        int tempQuantity = 0;
        if (carportLength >= 6000) {
            tempQuantity = 6;
        } else {
            tempQuantity = 4;
        }
        ;
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
        if (length * 10 < 3600) {
            tempLength = 3600;
        } else {
            tempLength = 6000;
        }
        int tagFladtLength = tempLength;
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(fladtTag, tagFladtLength);
        String description = "tagplader";

        int quantity = (int) Math.ceil(width / 100.0);

        tagFladt = new StykListeLinje(volumeMaterial, quantity, description);
        System.out.println("tagFladt: " + tagFladt);

        return tagFladt;
    }


    
    public Stykliste generereStykliste(int width, int length) throws DBException {
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

        return stykliste;
    }


}
