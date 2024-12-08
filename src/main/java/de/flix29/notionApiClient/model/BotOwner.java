package de.flix29.notionApiClient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BotOwner {
    private BotOwnerType type;
    private boolean workspaces;

    public BotOwner type(BotOwnerType type) {
        this.type = type;
        return this;
    }

    public BotOwner workspaces(boolean workspaces) {
        this.workspaces = workspaces;
        return this;
    }
}
