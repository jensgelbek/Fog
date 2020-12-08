package web.svg;

import web.svg.CKL.Rect;

import java.io.FileWriter;
import java.io.IOException;

public class SvgOuter extends Tag {
    private final int width;
    private final int height;
    private final String viewBox;

    public SvgOuter(int width, int height, String viewBox) {
        super("svg");
        this.width = width;
        this.height = height;
        this.viewBox = viewBox;
    }

    public String renderAttributes() {
        return String.format(
                "xmlns=\"http://www.w3.org/2000/svg\""
                        + " height=\"%d\" width=\"%d\" viewBox=\"%s\"",
                height,
                width,
                viewBox
        );
    }

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
        SvgOuter checkboard = new SvgOuter(1000, 1000, "1 1 8 8");
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
}
