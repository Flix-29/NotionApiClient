package de.flix29.notionApiClient.model.block.blockContent;

import de.flix29.notionApiClient.model.RichText;
import de.flix29.notionApiClient.model.block.Block;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public final class Template implements BlockContent {
    private List<RichText> content;
    private List<Block> children;

    public Template content(List<RichText> content) {
        this.content = content;
        return this;
    }

    public Template children(List<Block> children) {
        this.children = children;
        return this;
    }

    @Override
    public void setChildren(List<Block> children) {
        this.children = children;
    }
}
