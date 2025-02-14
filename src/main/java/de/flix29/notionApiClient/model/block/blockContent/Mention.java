package de.flix29.notionApiClient.model.block.blockContent;

import de.flix29.notionApiClient.model.MentionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public final class Mention implements BlockContent {
    private MentionType type;
    private String id;
}
