package de.flix29.notionApiClient.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class Bot extends User {
    private BotOwner owner;
    private String workspaceName;

    public Bot(User user) {
        super(user.getId(), user.getType(), user.getName(), user.getAvatarUrl());
    }

    public Bot owner(BotOwner owner) {
        this.owner = owner;
        return this;
    }

    public Bot workspaceName(String workspaceName) {
        this.workspaceName = workspaceName;
        return this;
    }
}
