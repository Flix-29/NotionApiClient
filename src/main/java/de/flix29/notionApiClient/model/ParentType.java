package de.flix29.notionApiClient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParentType {
    DATABASE("database_id"),
    PAGE("page_id"),
    WORKSPACE("workspace"),
    BLOCK("block_id");

    private final String type;

    public static ParentType fromString(String type) {
        for (ParentType parentType : ParentType.values()) {
            if (parentType.type.equals(type)) {
                return parentType;
            }
        }
        throw new IllegalArgumentException("Unknown parent type: " + type);
    }
}
