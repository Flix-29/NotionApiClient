package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RollupType {

    ARRAY("array"),
    DATE("date"),
    INCOMPLETE("incomplete"),
    NUMBER("number"),
    UNSUPPORTED("unsupported");

    private final String type;

    public static RollupType fromString(String type) {
        for (RollupType rollupType : RollupType.values()) {
            if (rollupType.type.equals(type)) {
                return rollupType;
            }
        }
        return UNSUPPORTED;
    }
}
