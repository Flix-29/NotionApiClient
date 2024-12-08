package de.flix29.notionApiClient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MentionType {

    DATABASE("database"),
    DATE("date"),
    LINK_PREVIEW("link_mention"),
    PAGE("page"),
    TEMPLATE_MENTION("template"),
    USER("user");

    private final String type;

    public static MentionType fromString(String type) {
        for (var mentionType : MentionType.values()) {
            if (mentionType.type.equals(type)) {
                return mentionType;
            }
        }
        throw new IllegalArgumentException("Unknown MentionType: " + type);
    }
}
