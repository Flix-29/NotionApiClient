package de.flix29.notionApiClient.model.block.blockContent;

import de.flix29.notionApiClient.model.RichText;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public final class File implements BlockContent {
    private List<RichText> caption;
    private de.flix29.notionApiClient.model.File file;
    private String name;

    public File caption(List<RichText> caption) {
        this.caption = caption;
        return this;
    }

    public File file(de.flix29.notionApiClient.model.File file) {
        this.file = file;
        return this;
    }

    public File name(String name) {
        this.name = name;
        return this;
    }
}
