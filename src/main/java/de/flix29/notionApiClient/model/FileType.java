package de.flix29.notionApiClient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileType {

    FILE("file"),
    EXTERNAL("external");

    private final String type;

    public static FileType fromString(String type) {
        for (FileType fileType : FileType.values()) {
            if (fileType.type.equalsIgnoreCase(type)) {
                return fileType;
            }
        }
        throw new IllegalArgumentException("Unknown file type: " + type);
    }
}
