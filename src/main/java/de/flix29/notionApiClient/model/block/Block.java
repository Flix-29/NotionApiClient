package de.flix29.notionApiClient.model.block;

import de.flix29.notionApiClient.model.Parent;
import de.flix29.notionApiClient.model.User;
import de.flix29.notionApiClient.model.block.blockContent.BlockContent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Block id(UUID id) {
        this.id = id;
        return this;
    }

    public Block parent(Parent parent) {
        this.parent = parent;
        return this;
    }

    public Block createdTime(OffsetDateTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public Block lastEditedTime(OffsetDateTime lastEditedTime) {
        this.lastEditedTime = lastEditedTime;
        return this;
    }

    public Block createdBy(User createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Block lastEditedBy(User lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
        return this;
    }

    public Block hasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
        return this;
    }

    public Block archived(boolean archived) {
        this.archived = archived;
        return this;
    }

    public Block deleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public Block type(BlockType type) {
        this.type = type;
        return this;
    }

    public Block blockContent(BlockContent blockContent) {
        this.blockContent = blockContent;
        return this;
    }
}
