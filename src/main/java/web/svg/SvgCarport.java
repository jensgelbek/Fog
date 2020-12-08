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
                        + " length=\"%d\" width=\"%d\" viewBox=\"%s\"",
                height,
                width,
                viewBox
        );
    }

    public static Tag ramme(int width, int length) {
        Tag ramme = new Rectangle(0.0, 0.0, length, width);
        System.out.println("Length: "+ length);
        System.out.println("Width: " + width);
        ramme.withStyle("fill: none; stroke: red;");
        return ramme;
    }

    public static Tag rem1(int length) {
        Double WD1 = Double.valueOf(length);
        System.out.println("WD1 " + length);
        Tag rem1 = new Rectangle(0.0, 35.0, WD1, 4.5);
        rem1.withStyle("fill: none; stroke: black;");
        return rem1;
    }

    public static Tag rem2(int width, int length) {
        Double WD2 = Double.valueOf(length);
        Double yLen = Double.valueOf(width) - 35.0;
        System.out.println("WD2 " + length);
        System.out.println("yLen: " + yLen);
        Tag rem2 = new Rectangle(0.0, yLen, WD2, 4.5);
        rem2.withStyle("fill: none; stroke: black;");
        return rem2;
    }

    public static List<Tag> spaer(int width, int length) {
        List<Tag> spaers = new ArrayList<>();
        double x = 0.0;
        while (x < (length-55.0)) {
            x += 55.0;
            Tag spaer = new Rectangle(x, 0.0, 4.5, width);
            spaer.withStyle("fill: none; stroke: purple;");
            spaers.add(spaer);
        }
        return spaers;
    }

    // TODO Kryds ?

    // TODO Stolper


    public static List<Tag> stolpe1(int width, int length) {
        List<Tag> stolper = new ArrayList<>();
        double x1 = 110.0;
        double x = 0.0;
        while (x < (length-410.0)) {
            x += 310.0;
            Tag stolpe1 = new Rectangle(x1, 30.0, 9.7, 9.7);
            Tag stolpe2 = new Rectangle(x, 30.0, 9.7, 9.7);
            stolpe1.withStyle("fill: none; stroke: #000000; black: 5 5;");
            stolpe2.withStyle("fill: none; stroke: #000000; black: 5 5;");
            stolper.add(stolpe1);
            stolper.add(stolpe2);

        }
        return stolper;
    }

    public static List<Tag> stolpe2(int width, int length) {
        List<Tag> stolper2 = new ArrayList<>();
        double x1 = 110.0;;
        Double yLen = Double.valueOf(width) - 35.0;
        double x = 0.0;
        while (x < (length-410.0)) {
            x = (x+x1);
            x += 310.0;
            Tag stolpe1 = new Rectangle(x1, yLen, 9.7, 9.7);
            Tag stolpe2 = new Rectangle(x, yLen, 9.7, 9.7);
            stolpe1.withStyle("fill: none; stroke: #000000; black: 5 5;");
            stolpe2.withStyle("fill: none; stroke: #000000; black: 5 5;");
            stolper2.add(stolpe1);
            stolper2.add(stolpe2);
        }
        return stolper2;
    }

    public static Tag lineW(int length) {
        Double WD = Double.valueOf(length) - 35.0;
        Tag line = new Line(20.0, 45.0, 20.0, 575);
        line.withStyle("fill: none; stroke: darkblue; darkblue: 5 5;");
        return line;
    }

    public static Tag carport(int width, int length) {
        SvgOuter ramme = new SvgOuter(800, 750, "0 0 855 750");
        // ramme.add(lineW(width));
        ramme.add(carport2(width, length));
        return ramme;
    }

    public static Tag carport2(int width, int length) {
        SvgInner carport = new SvgInner(75.0, 10.0, 800, 750, "0 0 800 750");
        carport.add(ramme(width, length));
        carport.add(rem1(length));
        carport.add(rem2(width, length));

        List spaers = spaer(width, length);
        for (Object o : spaers) {
            carport.add((Tag) o);
        }

        List stolper = stolpe1(width, length);
        for (Object o : stolper) {
            carport.add((Tag) o);
        }

        List stolper2 = stolpe2(width, length);
        for (Object o : stolper2) {
            carport.add((Tag) o);
        }

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

