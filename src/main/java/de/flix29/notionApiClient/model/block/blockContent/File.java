package de.flix29.notionApiClient.model.block.blockContent;

import de.flix29.notionApiClient.model.RichText;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public final class File implements BlockContent {
    private List<RichText> caption;
    private de.flix29.notionApiClient.model.File file;
    private String name;
}
