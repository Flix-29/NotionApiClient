package de.flix29.notionApiClient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {

    PERSON("person"),
    BOT("bot"),
    INTEGRATION("integration"),
    UNKNOWN("unknown");

    private final String type;

    public static UserType fromString(String type) {
        for (UserType userType : UserType.values()) {
            if (userType.type.equals(type)) {
                return userType;
            }
        }
        return UNKNOWN;
    }
}
