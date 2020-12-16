package api;

import domain.items.DBException;
import domain.materials.*;
import infrastructure.*;

public class StyklisteCalculator {

    private static final String spaer = "spær/rem";
    private static final String rem = "spær/rem";
    private static final String understern = "understern";
    private static final String overstern = "overstern";
    private static final String braedt = "brædt";
    private static final String reglar = "reglar";

    private final Webapp api;

    public StyklisteCalculator(Webapp api) {
        this.api = api;
    }

    // Stern calculations, Width: Front + back

    public StykListeLinje sternWidthCalc(int width, int length) throws DBException {
        StykListeLinje sternWidth;
        int widthCalc = width;
        System.out.println("widthCalc " + widthCalc);
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(understern, widthCalc);
        String description = "Stern: For og bag stern";
        int quantity = 2;
        sternWidth = new StykListeLinje(volumeMaterial, quantity, description);
        return sternWidth;
    }

    public StykListeLinje remCalc(int width, int length) {
        StykListeLinje remLength;
        int lengthCalc = length * 10;
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(rem, lengthCalc);
        String description = "Rem i siderne";
        int quantity = 2;

        /*
        if (length <= 480 ) {
            unit = 2;
        } else if (length <= 600 ) {
            unit = 2;
        } else {
            unit = 3;
        }

        int price = 50;
        int sum = (int) (remLength * price * unit);
         */
        remLength = new StykListeLinje(volumeMaterial, quantity, description);
        return remLength;
    }

    public StykListeLinje spaerCalc(int width, int length) {
        StykListeLinje spaers;
        int spaerWidth = width * 10;
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(spaer, spaerWidth);
        String description = "Spær: Monteres på rem";
        int quantity = length/60;

        spaers = new StykListeLinje(volumeMaterial, quantity, description);
        return spaers;
    }

    
    public Stykliste generereStykliste(int width, int length) throws DBException {
        Stykliste stykliste = new Stykliste();

        // Add Stern
        stykliste.volumenListe.add(sternWidthCalc(width, length));
        // Add Rem
        stykliste.volumenListe.add(remCalc(width, length));
        // Add Spaers
        stykliste.volumenListe.add(spaerCalc(width, length));

        System.out.println("Calc: " + stykliste);

        return stykliste;
    }

}
