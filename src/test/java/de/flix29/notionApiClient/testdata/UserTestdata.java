package de.flix29.notionApiClient.testdata;

import de.flix29.notionApiClient.model.*;

import java.util.UUID;

public class UserTestdata {

    public static People peopleEmpty() {
        return new People(userEmpty(UserType.PERSON));
    }

    public static Bot botEmpty() {
        return new Bot(userEmpty(UserType.BOT))
                .setOwner(new BotOwner()
                        .setType(BotOwnerType.WORKSPACE)
                        .setWorkspaces(true));
    }

    public static People peopleAllSet() {
        return new People(userAllSet(UserType.PERSON))
                .setEmail("user.name@mail.com");
    }

    public static Bot botAllSet() {
        return new Bot(userAllSet(UserType.BOT))
                .setOwner(new BotOwner()
                        .setType(BotOwnerType.WORKSPACE)
                        .setWorkspaces(true))
                .setWorkspaceName("Test Notion");
    }

    private static User userEmpty(UserType userType) {
        return new User()
                .setType(userType);
    }

    private static User userAllSet(UserType userType) {
        return new User()
                .setType(userType)
                .setId(UUID.randomUUID())
                .setName("User name")
                .setAvatarUrl("https://url-to-image.com/image");
    }
}
