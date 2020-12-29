package web.svg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagTest {

    @Test
    public void renderWithoutAttributes() {
        Tag tag = new TestTag("test");
        assertEquals("<test></test>", tag.toString());
    }

    @Test
    public void renderWithSubtags() {
        Tag tag = new TestTag("test");
        tag.add(new TestTag("a"));
        assertEquals("<test><a></a></test>", tag.toString());

        tag = new TestTag("test");
        tag.add(new TestTag("a"));
        tag.add(new TestTag("b"));
        assertEquals("<test><a></a><b></b></test>", tag.toString());
    }

    @Test
    public void renderWithContent() {
        Tag tag = new TestTag("test");
        tag.setContent("Hello, World!");
        assertEquals("<test>Hello, World!</test>", tag.toString());
    }

    @Test
    public void renderWithContentAndWithSubtags() {
        Tag tag = new TestTag("test");
        tag.setContent("Hello, World");
        tag.add(new TestTag("a"));
        assertEquals("<test>Hello, World!<a></a></test>", tag.toString());
    }

    public static class TestTag extends Tag {

        public TestTag(String name) {
            super(name);
        }

        @Override
        protected String renderAttributes() {
            return "";
        }
    }
}