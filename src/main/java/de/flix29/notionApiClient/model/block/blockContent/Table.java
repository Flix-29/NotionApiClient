package de.flix29.notionApiClient.model.block.blockContent;

import de.flix29.notionApiClient.model.block.Block;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public final class Table implements BlockContent {
    private int tableWidth;
    private boolean hasColumnHeader;
    private boolean hasRowHeader;
    private List<Block> children;

    public Table tableWidth(int tableWidth) {
        this.tableWidth = tableWidth;
        return this;
    }

    public Table hasColumnHeader(boolean hasColumnHeader) {
        this.hasColumnHeader = hasColumnHeader;
        return this;
    }

    public Table hasRowHeader(boolean hasRowHeader) {
        this.hasRowHeader = hasRowHeader;
        return this;
    }

    public Table children(List<Block> children) {
        this.children = children;
        return this;
    }

    @Override
    public void setChildren(List<Block> children) {
        this.children = children;
    }
}
