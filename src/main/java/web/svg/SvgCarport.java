package web.svg;

import domain.items.Carport;
import infrastructure.DBCarportRepository;
import infrastructure.Database;
import web.pages.Bestilling;
import web.svg.CKL.Svg;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

public class SvgCarport extends Tag {

    private final int width;
    private final int height;
    private final String viewBox;


    public SvgCarport(int width, int height, String viewBox) {
        super("svg");
        this.width = width;
        this.height = height;
        this.viewBox = viewBox;
    }



    @Override
    protected String renderAttributes() {
        return String.format(
                "xmlns=\"http://www.w3.org/2000/svg\""
                        + " height=\"%d\" width=\"%d\" viewBox=\"%s\"",
                height,
                width,
                viewBox
        );
    }

    public static Tag ramme() {
        Tag ramme = new Rectangle(0.0, 0.0, 780.0, 600.0);
        ramme.withStyle("fill: none; stroke: red;");
        return ramme;
    }

    public static Tag rem1() {
        Tag rem1 = new Rectangle(0.0, 35.0, 780.0, 4.5);
        rem1.withStyle("fill: none; stroke: black;");
        return rem1;
    }

    public static Tag rem2() {
        Tag rem2 = new Rectangle(0.0, 565.0, 780.0, 4.5);
        rem2.withStyle("fill: none; stroke: black;");
        return rem2;
    }

    public static List<Tag> spaer1(int width) {
        List<Tag> spaers = new ArrayList<>();
        double x = 0.0;
        while (x < width) {
            x += 55.0;
            Tag spaer = new Rectangle(x, 0.0, 4.5, 600);
            spaer.withStyle("fill: none; stroke: purple;");
            spaers.add(spaer);
        }
        return spaers;
    }

    /*
    public static Tag spaer2() {
        Tag carport = new Rectangle(775.5, 0.0, 4.5, 600);
        carport.withStyle("fill: none; stroke: cyan;");
        return carport;
    }
     */

    public static Tag kryds1() {
        Tag kryds1 = new Line(55.0, 35.0, 600.0, 569.5);
        kryds1.withStyle("fill: none; stroke: #000000; stroke-dasharray: 5 5;");
        return kryds1;
    }

    public static Tag kryds2() {
        Tag kryds2 = new Line(55.0, 569.5, 600, 35.0);
        kryds2.withStyle("fill: none; stroke: #000000; stroke-dasharray: 5 5;");
        return kryds2;
    }

    public static Tag stolpe1() {
        Tag stolpe = new Rectangle(110.0, 32.0, 9.7, 9.7);
        stolpe.withStyle("fill: none; stroke: #000000; black: 5 5;");
        return stolpe;
    }

    public static Tag stolpe2() {
        Tag stolpe = new Rectangle(420.0, 32.0, 9.7, 9.7);
        stolpe.withStyle("fill: none; stroke: #000000; black: 5 5;");
        return stolpe;
    }

    public static Tag stolpe3() {
        Tag stolpe = new Rectangle(730.0, 32.0, 9.7, 9.7);
        stolpe.withStyle("fill: none; stroke: #000000; black: 5 5;");
        return stolpe;
    }

    public static Tag stolpe4() {
        Tag stolpe = new Rectangle(110.0, 562.0, 9.7, 9.7);
        stolpe.withStyle("fill: none; stroke: #000000; black: 5 5;");
        return stolpe;
    }

    public static Tag stolpe5() {
        Tag stolpe = new Rectangle(420.0, 562.0, 9.7, 9.7);
        stolpe.withStyle("fill: none; stroke: #000000; black: 5 5;");
        return stolpe;
    }

    public static Tag stolpe6() {
        Tag stolpe = new Rectangle(730.0, 562.0, 9.7, 9.7);
        stolpe.withStyle("fill: none; stroke: #000000; black: 5 5;");
        return stolpe;
    }

    public static Tag lineW() {
        Tag line = new Line(20.0, 60.0, 20.0, 565.0);
        line.withStyle("fill: none; stroke: darkblue; darkblue: 5 5;");
        return line;
    }

    public static Tag carport(int width, int length) {
        Svg ramme = new Svg( 650, 500, "0 0 850 625");
        ramme.add(lineW());
        ramme.add(carport2(width, length));
        return ramme;
    }

    public static Tag carport2(int width, int length) {

        Svg carport = new Svg(800, 600, "-50 -25 850 625");
        carport.add(ramme());
        carport.add(rem1());
        carport.add(rem2());

        List spaers = spaer1(width);
        for (Object o : spaers) {
            carport.add((Tag) o);
        }

        // carport.add(spaer2());
        carport.add(kryds1());
        carport.add(kryds2());
        carport.add(stolpe1());
        carport.add(stolpe2());
        carport.add(stolpe3());
        carport.add(stolpe4());
        carport.add(stolpe5());
        carport.add(stolpe6());

        return carport;
    }



/*
    public static void main(String[] args) {

        try (FileWriter writer = new FileWriter("./src/main/java/web/svg/svgOutput/carport.svg"))
        {
            writer.write(carport(1,1).toString());
            String svg =  carport(1,1).toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

 */




    }

