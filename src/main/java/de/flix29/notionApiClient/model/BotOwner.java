package de.flix29.notionApiClient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BotOwner {
    private BotOwnerType type;
    private boolean workspaces;
}
