package de.flix29.notionApiClient.model.block;

import de.flix29.notionApiClient.model.Parent;
import de.flix29.notionApiClient.model.User;
import de.flix29.notionApiClient.model.block.blockContent.BlockContent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Block {
    private UUID id;
    private Parent parent;
    private OffsetDateTime createdTime;
    private OffsetDateTime lastEditedTime;
    private User createdBy;
    private User lastEditedBy;
    private boolean hasChildren;
    private boolean archived;
    private boolean deleted;
    private BlockType type;
    private BlockContent blockContent;
}
