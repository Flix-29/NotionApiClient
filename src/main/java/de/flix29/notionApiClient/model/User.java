package de.flix29.notionApiClient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public sealed class User permits Bot, People {
    private UUID id;
    private UserType type;
    private String name;
    private String avatarUrl;

    public User id(UUID id) {
        this.id = id;
        return this;
    }

    public User type(UserType type) {
        this.type = type;
        return this;
    }

    public User name(String name) {
        this.name = name;
        return this;
    }

    public User avatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }
}
