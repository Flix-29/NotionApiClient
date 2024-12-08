package de.flix29.notionApiClient.model.block.blockContent;

import de.flix29.notionApiClient.model.Color;
import de.flix29.notionApiClient.model.RichText;
import de.flix29.notionApiClient.model.block.Block;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public final class NumberedListItem implements BlockContent {
    private List<RichText> content;
    private Color color;
    private List<Block> children;

    public NumberedListItem content(List<RichText> content) {
        this.content = content;
        return this;
    }

    public NumberedListItem color(Color color) {
        this.color = color;
        return this;
    }

    public NumberedListItem children(List<Block> children) {
        this.children = children;
        return this;
    }

    @Override
    public void setChildren(List<Block> children) {
        this.children = children;
    }
}
