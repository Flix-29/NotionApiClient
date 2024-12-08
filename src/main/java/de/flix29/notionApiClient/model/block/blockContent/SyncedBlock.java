package de.flix29.notionApiClient.model.block.blockContent;

import de.flix29.notionApiClient.model.block.Block;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public final class SyncedBlock implements BlockContent {
    private String syncedFrom;
    private List<Block> children;

    public SyncedBlock syncedFrom(String syncedFrom) {
        this.syncedFrom = syncedFrom;
        return this;
    }

    public SyncedBlock children(List<Block> children) {
        this.children = children;
        return this;
    }

    private boolean isParent() {
        return syncedFrom != null;
    }

    @Override
    public void setChildren(List<Block> children) {
        this.children = children;
    }
}
