package de.flix29.notionApiClient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class File implements Icon {
    private FileType type;
    private String url;
    private OffsetDateTime expirationTime;

    public File type(FileType type) {
        this.type = type;
        return this;
    }

    public File url(String url) {
        this.url = url;
        return this;
    }

    public File expirationTime(OffsetDateTime expirationTime) {
        this.expirationTime = expirationTime;
        return this;
    }
}
