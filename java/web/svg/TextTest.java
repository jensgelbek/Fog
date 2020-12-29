package web.svg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class TextTest {

    @Test
    public void textRendersCorrectly() {
        Text t = new Text("actual text", 100, 100);
        assertEquals("<text x=\"100.000000\" y=\"100.000000\">actual text</text>", t.toString());
    }

}