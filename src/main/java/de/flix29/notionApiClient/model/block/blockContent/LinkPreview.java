package de.flix29.notionApiClient.model.block.blockContent;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public final class LinkPreview implements BlockContent {
    private String url;

    public LinkPreview url(String url) {
        this.url = url;
        return this;
    }
}
