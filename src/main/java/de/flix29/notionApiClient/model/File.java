package de.flix29.notionApiClient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public final class File implements Icon {
    private FileType type;
    private String url;
    private OffsetDateTime expirationTime;
}
