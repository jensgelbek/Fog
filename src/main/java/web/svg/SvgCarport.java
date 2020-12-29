package web.svg;


import web.pages.Bestilling;
import domain.materials.*;
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


    // Stern SVG Draw,
    public static Tag sternDraw(int width, int length) {
        Tag sternDraw = new Rectangle(0.0, 0.0, length, width);
        sternDraw.withStyle("fill: none; stroke: red;");
        return sternDraw;
    }

    // Stern SVG Draw,
    public static Tag shedDraw(int length,int shedWidth, int shedLength) {
        Tag shedDraw = new Rectangle(length-(shedLength+25.0),35.0, shedLength, shedWidth );
        shedDraw.withStyle("fill: lightgrey; stroke: darkgreen; ");
        return shedDraw;
    }

    // Rem one side SVG Draw.
    public static Tag remOneDraw(int length) {
        Double remOneLength = Double.valueOf(length);
        Tag rem1 = new Rectangle(0.0, 35.0, remOneLength, 4.5);
        rem1.withStyle("fill: none; stroke: black;");
        return rem1;
    }

    // Rem other side SVG Draw.
    public static Tag remTwoDraw(int width, int length) {
        Double remTwoLength = Double.valueOf(length);
        Double remTwoY = Double.valueOf(width) - 45.0;
        Tag rem2 = new Rectangle(0.0, remTwoY, remTwoLength, 4.5);
        rem2.withStyle("fill: none; stroke: black;");
        return rem2;
    }

    // Spær SVG Draw.
    // 0.6m apart
    public static List<Tag> spaerDraw(int width, int length) {
        List<Tag> spaersDraw = new ArrayList<>();
        double x = 0.0;

        while (x < (length-60.0)) {
            x += 60.0;

            Tag spaer = new Rectangle(x, 0.0, 4.5, width);
            spaer.withStyle("fill: none; stroke: purple;");
            spaersDraw.add(spaer);
        }
        return spaersDraw;
    }

    // Stolper SVG Draw.
    // 1'st: 110, last( 35 from back ) if more than 6m + 1 ind between 1'st and last
    public static List<Tag> stolperDraw(int width, int length) {
        List<Tag> stolper = new ArrayList<>();

        Tag stolpeOneFront;
        Tag stolpeOneMid;
        Tag stolpeOneLast;

        Tag stolpeTwoFront;
        Tag stolpeTwoMid;
        Tag stolpeTwoLast;

        double stolpeFrontXPoint = 110.0;
        double measureFromBack = 35.0;
        double stolpeOneYPoint = 30.0;
        double stolpeTwoYPoint = Double.valueOf(width) - 45.0;

        if ( length > 0.0 ) {

            stolpeOneFront = new Rectangle(stolpeFrontXPoint, stolpeOneYPoint, 9.7, 9.7);
            stolpeOneLast = new Rectangle(Double.valueOf(length) - measureFromBack, stolpeOneYPoint, 9.7, 9.7);

            stolpeTwoFront = new Rectangle(stolpeFrontXPoint, stolpeTwoYPoint, 9.7, 9.7);
            stolpeTwoLast = new Rectangle(Double.valueOf(length) - measureFromBack, stolpeTwoYPoint, 9.7, 9.7);

            stolpeOneFront.withStyle("fill: none; stroke: #000000; black: 5 5;");
            stolpeOneLast.withStyle("fill: none; stroke: #000000; black: 5 5;");
            stolpeTwoFront.withStyle("fill: none; stroke: #000000; black: 5 5;");
            stolpeTwoLast.withStyle("fill: none; stroke: #000000; black: 5 5;");

            stolper.add(stolpeOneFront);
            stolper.add(stolpeOneLast);
            stolper.add(stolpeTwoFront);
            stolper.add(stolpeTwoLast);

            if (length >= 600.0) {

                // (stolpeFrontXPoint - ((Double.valueOf(length) - measureFromBack)/2) + stolpeFrontXPoint));

                double stolpeOneMidXPoint = ((Double.valueOf(length) - stolpeFrontXPoint - measureFromBack)/2) + stolpeFrontXPoint;
                stolpeOneMid = new Rectangle(stolpeOneMidXPoint, stolpeOneYPoint, 9.7, 9.7);

                double stolpeTwoMidXPoint = ((Double.valueOf(length) - stolpeFrontXPoint - measureFromBack)/2) + stolpeFrontXPoint;
                stolpeTwoMid = new Rectangle(stolpeOneMidXPoint, stolpeTwoYPoint, 9.7, 9.7);

                stolpeOneMid.withStyle("fill: none; stroke: #000000; black: 5 5;");
                stolper.add(stolpeOneMid);

                stolpeTwoMid.withStyle("fill: none; stroke: #000000; black: 5 5;");
                stolper.add(stolpeTwoMid);

            }
        }
        return stolper;
    }

    // Hulbånd SVG Draw.
    public static List<Tag> hulbaandDraw(int width, int length) {
        List<Tag> hulbaand = new ArrayList<>();

        if ( length > 0.0 ) {

            Tag krydsOne = new Line(60.0, 35.0, length - 60.0, width - 45.0);
            krydsOne.withStyle("fill: none; stroke: #000000; stroke-dasharray: 5 5;");

            Tag krydsTwo = new Line(60.0, width - 45.0, length - 60.0, 35.0);
            krydsTwo.withStyle("fill: none; stroke: #000000; stroke-dasharray: 5 5;");

            hulbaand.add(krydsOne);
            hulbaand.add(krydsTwo);

        }
        return hulbaand;
    }

    // Carport inner width SVG line Draw.
    public static Tag lineW(int width) {
        Double WD;
        if (width > 0 ) {
            WD = Double.valueOf(width) - 35.0;
        } else {
            WD = 49.5;
        }
        Tag line = new Line(30.0, 49.5, 30.0, WD);
        line.withStyle("fill: none; stroke: darkblue; darkblue: 5 5;");
        return line;
    }

    // Carport inner Length SVG line Draw.
    public static Tag lineL(int width, int length) {
        Double LD;
        if (width > 0) {
            LD = Double.valueOf(length);
        } else {
            LD = 0.0;
        }
        Tag line = new Line(75.0, width + 50.0, LD + 75.0, width + 50.0);
        line.withStyle("fill: none; stroke: darkblue; darkblue: 10 10;");

        return line;
    }

    public static Tag textL(int width, int length) {
        String textL = "Længde: " + Double.toString(Math.abs(length*10)) + " mm";
        Tag text = new Text(textL, (length/2)-10, width + 70.0);
        return text;
   }

    public static Tag textW(int width) {
        String textW = "Bredde: " + Double.toString(width-70) + " mm";
        Tag text = new Text(textW, -(width/2)-10, 15.0);
        text.withStyle("transform: rotate(-90deg)");
        return text;
    }

    // Draw outer viewbox
    public static Tag carport(int width, int length, int shedWidth, int shedLength) {

        int newWidth = width/10;
        width = newWidth;

        int newLength = length/10;
        length = newLength;

        int newShedWidth = shedWidth/10;
        shedWidth = newShedWidth;

        int newShedLength = shedLength/10;
        shedLength = newShedLength;

        SvgOuter ramme = new SvgOuter(800, 800, "0 0 855 750");
        ramme.add(lineW(width));
        ramme.add(lineL(width, length));
        ramme.add(textL(width, length));
        ramme.add(textW(width));

        ramme.add(carport2(width, length, shedWidth, shedLength));
        return ramme;
    }

    // Draw inner viewbox
    public static Tag carport2(int width, int length, int shedWidth, int shedLength) {

        SvgInner carport = new SvgInner(75.0, 10.0, 800, 750, "0 0 800 750");
        carport.add(sternDraw(width, length));
        carport.add(remOneDraw(length));
        carport.add(remTwoDraw(width, length));
        carport.add(shedDraw(length, shedWidth, shedLength));



        List spaers = spaerDraw(width, length);
        for (Object o : spaers) {
            carport.add((Tag) o);
        }

        List stolper = stolperDraw(width, length);
        for (Object o : stolper) {
            carport.add((Tag) o);
        }

        List hulbaand = hulbaandDraw(width, length);
        for (Object o : hulbaand) {
            carport.add((Tag) o);
        }
        return carport;
    }

}

