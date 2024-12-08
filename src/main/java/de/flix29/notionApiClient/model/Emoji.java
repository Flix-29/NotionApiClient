package de.flix29.notionApiClient.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Emoji implements Icon {
    private String emoji;
}
