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
public final class Toggle implements BlockContent {
    private List<RichText> content;
    private Color color;
    private List<Block> children;

    public Toggle content(List<RichText> content) {
        this.content = content;
        return this;
    }

    public Toggle color(Color color) {
        this.color = color;
        return this;
    }

    public Toggle children(List<Block> children) {
        this.children = children;
        return this;
    }

    @Override
    public void setChildren(List<Block> children) {
        this.children = children;
    }
}