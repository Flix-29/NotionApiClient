package de.flix29.notionApiClient.testdata;

import de.flix29.notionApiClient.model.*;

import java.util.List;
import java.util.UUID;

public class UserTestdata {

    protected static People peopleEmpty() {
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

    protected static User userJustID() {
        return new User()
                .setType(UserType.UNKNOWN)
                .setId(UUID.fromString("c6e92391-c829-42ea-9ac7-949106ed3916"));
    }

    protected static User userEmpty() {
        return new User()
                .setType(UserType.UNKNOWN);
    }

    private static User userAllSet(UserType userType) {
        return new User()
                .setType(userType)
                .setId(UUID.fromString("c6e92391-c829-42ea-9ac7-949106ed3916"))
                .setName("User name")
                .setAvatarUrl("https://url-to-image.com/image");
    }

    protected static List<User> userListAllSet() {
        return List.of(peopleAllSet(), botAllSet());
    }
}
