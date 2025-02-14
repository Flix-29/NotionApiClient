package de.flix29.notionApiClient.model.block.blockContent;

import de.flix29.notionApiClient.model.Color;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public final class TableOfContents implements BlockContent {
    private Color color;
}
