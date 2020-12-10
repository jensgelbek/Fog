package api;

import web.pages.Bestilling;
import web.svg.StykListeLinje;

public class Calc {








    public static StykListeLinje sternWidthCalc(int width, int length) {
        StykListeLinje sternWidth;
        String name = "Stern: For og bag stern";
        Double doubleWidth = Double.valueOf(width);
        int unit = 2;
        int price = 50;
        int sum = (int) ((doubleWidth/100) * price * unit);
        sternWidth = new StykListeLinje(name, doubleWidth, unit, price, sum);
        System.out.println(sternWidth);
        return sternWidth;
    }


    // req.setAttribute("sternWidthCalc", Bestilling.CarportDTO.fromSession(req.getSession()).sternWidthCalc());
    // req.setAttribute("sternLengthCalc", Bestilling.CarportDTO.fromSession(req.getSession()).sternLengthCalc());
    // req.setAttribute("spaerCalc", Bestilling.CarportDTO.fromSession(req.getSession()).spaerCalc());




}
