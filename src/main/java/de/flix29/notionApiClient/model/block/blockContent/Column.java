package de.flix29.notionApiClient.model.block.blockContent;

import de.flix29.notionApiClient.model.block.Block;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public final class Column implements BlockContent {
    private List<Block> children;

    public Column children(List<Block> children) {
        this.children = children;
        return this;
    }

    @Override
    public void setChildren(List<Block> children) {
        this.children = children;
    }
}
