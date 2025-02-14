package de.flix29.notionApiClient.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public final class Bot extends User {
    private BotOwner owner;
    private String workspaceName;

    public Bot(User user) {
        super(user.getId(), user.getType(), user.getName(), user.getAvatarUrl());
    }
}
