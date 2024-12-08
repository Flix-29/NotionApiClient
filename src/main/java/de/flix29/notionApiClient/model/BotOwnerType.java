package de.flix29.notionApiClient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BotOwnerType {

    WORKSPACE("workspace"),
    USER("user");

    private final String ownerType;

    public static BotOwnerType fromString(String ownerType) {
        for (BotOwnerType type : BotOwnerType.values()) {
            if (type.ownerType.equalsIgnoreCase(ownerType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No BotOwnerType with ownerType " + ownerType + " found");
    }
}
