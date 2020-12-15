package api;

import domain.items.DBException;
import domain.materials.*;
import infrastructure.*;
import web.svg.SvgCarport;

public class Calc extends Stykliste {

    private static String spaer = "spær/rem";
    private static String rem = "spær/rem";
    private static String understern = "understern";
    private static String overstern = "overstern";
    private static String braedt = "brædt";
    private static String reglar = "reglar";


    public Calc() {
    }

    public static StykListeLinje sternWidthCalc(int width, int length) throws DBException {
        StykListeLinje sternWidth;
        int widthCalc = width * 10;
        Database db = new Database();
        Webapp api = new Webapp(new DBOrderRepository(db), new DBCustomerRepository(db), new DBCarportRepository(db), new DBSellerRepository(db),new DBVolumeMateialRepository(db),new DBUnitMaterialRepository(db),new DBStyklisteLinjeRepository(db),new DBStyklisteRepository(db));
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(understern, widthCalc);
        String description = "Stern: For og bag stern";
        int quantity = 2;
        sternWidth = new StykListeLinje(volumeMaterial, quantity, description);
        return sternWidth;
    }

    public static StykListeLinje remCalc(int width, int length) {
        StykListeLinje remLength;
        int lengthCalc = length * 10;
        Database db = new Database();
        Webapp api = new Webapp(new DBOrderRepository(db), new DBCustomerRepository(db), new DBCarportRepository(db), new DBSellerRepository(db),new DBVolumeMateialRepository(db),new DBUnitMaterialRepository(db),new DBStyklisteLinjeRepository(db),new DBStyklisteRepository(db));
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

    public static StykListeLinje spaerCalc(int width, int length) {
        StykListeLinje spaers;
        int spaerWidth = width * 10;
        Database db = new Database();
        Webapp api = new Webapp(new DBOrderRepository(db), new DBCustomerRepository(db), new DBCarportRepository(db), new DBSellerRepository(db),new DBVolumeMateialRepository(db),new DBUnitMaterialRepository(db),new DBStyklisteLinjeRepository(db),new DBStyklisteRepository(db));
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(spaer, spaerWidth);
        String description = "Spær: Monteres på rem";
        int quantity = length/60;

        spaers = new StykListeLinje(volumeMaterial, quantity, description);
        return spaers;
    }

    
    public static Stykliste generereStykliste(int width, int length) throws DBException {
        Stykliste stykliste = new Stykliste();

        // Add Stern
        stykliste.volumenListe.add(sternWidthCalc(width, length));
        // Add Rem
        stykliste.volumenListe.add(remCalc(width, length));
        // Add Spaers
        stykliste.volumenListe.add(spaerCalc(width, length));

        Database db = new Database();
        Webapp api = new Webapp(new DBOrderRepository(db), new DBCustomerRepository(db), new DBCarportRepository(db), new DBSellerRepository(db), new DBVolumeMateialRepository(db), new DBUnitMaterialRepository(db), new DBStyklisteLinjeRepository(db), new DBStyklisteRepository(db));
        api.commitStykliste(stykliste, 1);
        System.out.println("Stykliste " + stykliste);
        return stykliste;
    }

}
