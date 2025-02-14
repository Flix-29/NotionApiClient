package de.flix29.notionApiClient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public sealed class User permits Bot, People {
    private UUID id;
    private UserType type;
    private String name;
    private String avatarUrl;
}
