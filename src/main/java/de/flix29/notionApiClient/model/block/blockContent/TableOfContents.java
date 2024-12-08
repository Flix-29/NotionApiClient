package de.flix29.notionApiClient.model.block.blockContent;

import de.flix29.notionApiClient.model.Color;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public final class TableOfContents implements BlockContent {
    private Color color;

    public TableOfContents color(Color color) {
        this.color = color;
        return this;
    }
}
