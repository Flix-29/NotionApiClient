package de.flix29.notionApiClient.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public final class Text extends RichText {
    private String content;
    private String link;

    public Text(RichText richText) {
        super(richText.getAnnotations(), richText.getPlainText(), richText.getHref());
    }
}
