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

    public static Tag ramme(int length, int width) {
        Tag ramme = new Rectangle(0.0, 0.0, width, length);
        ramme.withStyle("fill: none; stroke: red;");
        return ramme;
    }

    public static Tag rem1(int width) {
        Double WD = Double.valueOf(width);
        System.out.println(WD);
        Tag rem1 = new Rectangle(0.0, 35.0, WD, 4.5);
        rem1.withStyle("fill: none; stroke: black;");
        return rem1;
    }

    public static Tag rem2(int length) {
        Double WD = Double.valueOf(length);
        Tag rem2 = new Rectangle(0.0, 565.0, WD, 4.5);
        rem2.withStyle("fill: none; stroke: black;");
        return rem2;
    }

    // TODO Spaer

    // TODO Kryds

    // TODO Stolper


    public static Tag lineW(int length) {
        Double WD = Double.valueOf(length) - 35.0;
        Tag line = new Line(20.0, 45.0, 20.0, 575);
        line.withStyle("fill: none; stroke: darkblue; darkblue: 5 5;");
        return line;
    }

    public static Tag carport(int width, int length) {
        SvgOuter ramme = new SvgOuter(800, 600, "0 0 855 700");
        ramme.add(lineW(width));
        ramme.add(carport2(width, length));
        return ramme;
    }

    public static Tag carport2(int width, int length) {
        SvgInner carport = new SvgInner(75.0, 10.0, 800, 600, "0 0 780 600");
        carport.add(ramme(width, length));
        carport.add(rem1(width));
        carport.add(rem2(width));


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

