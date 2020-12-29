package web.svg;

import java.util.ArrayList;
import java.util.List;

public abstract class Tag {

    protected final List<Tag> subTags = new ArrayList<>();
    private final String name;
    private String style;
    private String clazz;
    private String content;

    protected Tag(String name) {
        this.name = name;
    }

    public void add(Tag tag) {
        subTags.add(tag);
    }

    public Tag withClass(String name) {
        clazz = name;
        return this;
    }

    public Tag withStyle(String style) {
        this.style = style;
        return this;
    }

    public void render(StringBuilder builder) {
        builder.append(String.format("<%s", name));
        if (this.clazz != null) {
            builder.append(String.format(" class=\"%s\"", this.clazz));
        }
        if (this.style != null) {
            builder.append(String.format(" style=\"%s\"", this.style));
        }
        String attributes = renderAttributes();
        if (!attributes.isEmpty()) {
            builder.append(" ");
            builder.append(attributes);
        }
        builder.append(">");
        if (this.content != null) {
            builder.append(this.content);
        }
        renderSubTags(builder);
        builder.append(String.format("</%s>", name));
    }

    protected abstract String renderAttributes();

    public void renderSubTags(StringBuilder builder) {
        for (Tag t : subTags) {
            t.render(builder);
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        render(builder);
        return builder.toString();
    }

    public void setContent(String content) {
        this.content = content;
    }
}
