package de.flix29.notionApiClient.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class Text extends RichText {

    private String content;
    private String link;

    public Text(RichText richText) {
        super(richText.getAnnotations(), richText.getPlainText(), richText.getHref());
    }

    public Text content(String content) {
        this.content = content;
        return this;
    }

    public Text link(String link) {
        this.link = link;
        return this;
    }
}
