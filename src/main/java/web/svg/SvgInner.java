package web.svg;

public class SvgInner extends Tag {
    private final double x;
    private final double y;
    private final int width;
    private final int height;
    private final String viewBox;

    public SvgInner(double x, double y, int width, int height, String viewBox) {
        super("svg");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.viewBox = viewBox;
    }

    public String renderAttributes() {
        return String.format(
                "xmlns=\"http://www.w3.org/2000/svg\""
                        + " x=\"%f\" y=\"%f\" height=\"%d\" width=\"%d\" viewBox=\"%s\" ",
                x,
                y,
                height,
                width,
                viewBox
        );
    }

    /*
    public static Tag chessfield(int r, int c) {
        Tag rect = new Rect(0.05 + r, c + 0.05, 0.9, 0.9);
        if ((r % 2 + c) % 2 == 0) {
            rect = rect.withStyle("fill: black;");
        } else {
            rect = rect.withStyle("fill: white;");
        }
        return rect;
    }


    public static Tag chessboard() {
        SvgInner checkboard = new SvgInner(1000, 1000, "1 1 8 8");
        for (int r = 1; r <= 8; r++) {
            for (int c = 1; c <= 8; c++) {
                checkboard.add(chessfield(r, c));
            }
        }
        return checkboard;
    }



    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("./src/main/java/web/svg/svgOutput/carport.svg")) {
          writer.write(chessboard().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     */
}
