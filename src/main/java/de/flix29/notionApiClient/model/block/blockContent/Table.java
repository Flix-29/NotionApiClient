package de.flix29.notionApiClient.model.block.blockContent;

import de.flix29.notionApiClient.model.block.Block;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public final class Table implements BlockContent {
    private int tableWidth;
    private boolean hasColumnHeader;
    private boolean hasRowHeader;
    private List<Block> children;

    @Override
    public Table setChildren(List<Block> children) {
        this.children = children;
        return this;
    }
}
