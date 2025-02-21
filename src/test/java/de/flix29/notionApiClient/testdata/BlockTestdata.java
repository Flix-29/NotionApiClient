package de.flix29.notionApiClient.testdata;

import de.flix29.notionApiClient.model.block.Block;
import de.flix29.notionApiClient.model.block.BlockType;

import java.time.OffsetDateTime;
import java.util.UUID;

public class BlockTestdata {

    private static final UUID ID = UUID.fromString("84ad6909-7176-43eb-ba32-3f8b77ba7526");
    private static final OffsetDateTime time = OffsetDateTime.parse("2025-01-01T00:00:00Z");

    public static Block blockAllSet() {
        return new Block()
                .setId(ID)
                .setParent(ParentTestdata.parentPageAllSet().setId(ID))
                .setCreatedTime(time)
                .setLastEditedTime(time)
                .setCreatedBy(ID)
                .setLastEditedBy(ID)
                .setHasChildren(true)
                .setArchived(true)
                .setDeleted(true)
                .setType(BlockType.UNSUPPORTED)
                .setBlockContent(null);
    }

    public static Block blockEmpty() {
        return new Block()
                .setId(null)
                .setParent(ParentTestdata.parentEmpty())
                .setCreatedTime(null)
                .setLastEditedTime(null)
                .setCreatedBy(null)
                .setLastEditedBy(null)
                .setHasChildren(false)
                .setArchived(false)
                .setDeleted(false)
                .setType(BlockType.UNSUPPORTED)
                .setBlockContent(null);
    }
}
