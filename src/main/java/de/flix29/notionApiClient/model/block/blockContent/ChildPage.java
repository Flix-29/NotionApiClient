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
public final class ChildPage implements BlockContent {
    private String title;
    private List<Block> children;

    @Override
    public void setChildren(List<Block> children) {
        this.children = children;
    }
}
