package de.flix29.notionApiClient.model.block.blockContent;

import de.flix29.notionApiClient.model.File;
import de.flix29.notionApiClient.model.RichText;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public final class Image implements BlockContent {
    private List<RichText> caption;
    private File file;

    public Image caption(List<RichText> caption) {
        this.caption = caption;
        return this;
    }

    public Image file(File file) {
        this.file = file;
        return this;
    }
}
