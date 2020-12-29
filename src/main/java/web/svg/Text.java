package web.svg;

import java.util.Locale;

public class Text extends Tag {
    private final double x;
    private final double y;

    public Text(String content, double x, double y) {
        super("text");
        setContent(content);
        this.x = x;
        this.y = y;
    }

    @Override
    protected String renderAttributes() {
        return String.format(Locale.US, "x=\"%f\" y=\"%f\"",
                 x, y);

    }
}
