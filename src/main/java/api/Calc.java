package api;

import domain.items.DBException;
import domain.materials.*;
import infrastructure.*;

public class Calc {

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
        Webapp api = new Webapp(new DBOrderRepository(db), new DBCustomerRepository(db), new DBCarportRepository(db), new DBSellerRepository(db),new DBVolumeMateialRepository(db),new DBUnitMaterialRepository(db));
        VolumeMaterial volumeMaterial = api.findVolumeMaterialNameLenght(understern, widthCalc);
        String description = "Stern: For og bag stern";
        int quantity = 2;
        sternWidth = new StykListeLinje(volumeMaterial, quantity, description);
        return sternWidth;
    }


    public static StykListeLinje remCalc(int width, int length) {
        StykListeLinje rem;
        String name = "Rem i siderne";
        Double remLength = Double.valueOf(length);
        int unit;
        if (length <= 480 ) {
            unit = 2;
        } else if (length <= 600 ) {
            unit = 2;
        } else {
            unit = 3;
        }
        int price = 50;
        int sum = (int) (remLength * price * unit);
        // rem = new StykListeLinje();
        // System.out.println(rem);
        return null;
    }

    public static StykListeLinje spaerCalc(int width, int length) {
        StykListeLinje spaer;
        String name = "Spær: Monteres på rem";
        Double spaerLength = Double.valueOf(length);
        int unit = (int) (length / 60.0);
        int price = 50;
        int sum = (int) ((spaerLength/100) * price * unit);
        // spaer = new StykListeLinje(name, spaerLength, unit, price, sum);
        // System.out.println(spaer);
        return null;
    }


    public static Stykliste generereStykliste(int width, int length) throws DBException {
        Stykliste stykliste = new Stykliste();
        stykliste.volumenListe.add(sternWidthCalc(width, length));
        System.out.println(stykliste);
        return stykliste;
    }






}
