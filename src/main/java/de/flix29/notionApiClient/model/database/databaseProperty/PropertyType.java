package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PropertyType {

    BUTTON("button"),
    CHECKBOX("checkbox"),
    CREATED_BY("created_by"),
    CREATED_TIME("created_time"),
    DATE("date"),
    EMAIL("email"),
    FILE("files"),
    FORMULA("formula"),
    LAST_EDITED_BY("last_edited_by"),
    LAST_EDITED_TIME("last_edited_time"),
    MULTI_SELECT("multi_select"),
    NUMBER("number"),
    PEOPLE("people"),
    PHONE_NUMBER("phone_number"),
    RELATION("relation"),
    RICH_TEXT("rich_text"),
    ROLLUP("rollup"),
    SELECT("select"),
    STATUS("status"),
    TITLE("title"),
    URL("url");

    private final String type;

    public static PropertyType fromString(String type) {
        for (PropertyType propertyType : PropertyType.values()) {
            if (propertyType.type.equalsIgnoreCase(type)) {
                return propertyType;
            }
        }
        throw new IllegalArgumentException("Unknown property type: " + type);
    }
}
