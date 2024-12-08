package de.flix29.notionApiClient.model.block.blockContent;

import de.flix29.notionApiClient.model.RichText;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public final class Code implements BlockContent {
    private List<RichText> caption;
    private List<RichText> content;
    private ProgrammingLanguages language;
}
