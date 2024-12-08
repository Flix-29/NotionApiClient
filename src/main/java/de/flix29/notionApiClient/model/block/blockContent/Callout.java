package de.flix29.notionApiClient.model.block.blockContent;

import de.flix29.notionApiClient.model.Color;
import de.flix29.notionApiClient.model.Icon;
import de.flix29.notionApiClient.model.RichText;
import de.flix29.notionApiClient.model.block.Block;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public final class Callout implements BlockContent {
    private List<RichText> content;
    private Icon icon;
    private Color color;
    private List<Block> children;

    public Callout content(List<RichText> content) {
        this.content = content;
        return this;
    }

    public Callout icon(Icon icon) {
        this.icon = icon;
        return this;
    }

    public Callout color(Color color) {
        this.color = color;
        return this;
    }

    public Callout children(List<Block> children) {
        this.children = children;
        return this;
    }

    @Override
    public void setChildren(List<Block> children) {
        this.children = children;
    }
}
