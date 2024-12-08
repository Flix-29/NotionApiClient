package de.flix29.notionApiClient.model.block.blockContent;

import de.flix29.notionApiClient.model.RichText;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public final class Bookmark implements BlockContent {
    private List<RichText> caption;
    private String url;

    public Bookmark caption(List<RichText> caption) {
        this.caption = caption;
        return this;
    }

    public Bookmark url(String url) {
        this.url = url;
        return this;
    }
}
