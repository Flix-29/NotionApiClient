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
public final class People extends User {
    private String email;

    public People(User user) {
        super(user.getId(), user.getType(), user.getName(), user.getAvatarUrl());
    }
}
