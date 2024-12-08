package de.flix29.notionApiClient.model.block.blockContent;

import de.flix29.notionApiClient.model.File;
import de.flix29.notionApiClient.model.FileType;
import de.flix29.notionApiClient.model.RichText;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public final class Video implements BlockContent {
    private List<RichText> caption;
    private FileType type;
    private File file;

    public Video caption(List<RichText> caption) {
        this.caption = caption;
        return this;
    }

    public Video type(FileType type) {
        this.type = type;
        return this;
    }

    public Video file(File file) {
        this.file = file;
        return this;
    }
}
