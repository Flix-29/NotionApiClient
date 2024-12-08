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
public final class Pdf implements BlockContent {
    private List<RichText> caption;
    private FileType type;
    private File file;

    public Pdf caption(List<RichText> caption) {
        this.caption = caption;
        return this;
    }

    public Pdf type(FileType type) {
        this.type = type;
        return this;
    }

    public Pdf file(File file) {
        this.file = file;
        return this;
    }
}
