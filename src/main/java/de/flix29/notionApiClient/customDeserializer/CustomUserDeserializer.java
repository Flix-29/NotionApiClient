package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.*;
import de.flix29.notionApiClient.model.*;

import java.lang.reflect.Type;
import java.util.UUID;

import static de.flix29.notionApiClient.customDeserializer.CustomDeserializerUtils.getAsStringIfPresentAndNotNull;

public class CustomUserDeserializer implements JsonDeserializer<User> {
    @Override
    public User deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        var jsonObject = jsonElement.getAsJsonObject();
        var userType = UserType.fromString(getAsStringIfPresentAndNotNull(jsonObject, "type"));

        var user = new User()
                .id(UUID.fromString(jsonObject.get("id").getAsString()))
                .type(userType)
                .name(getAsStringIfPresentAndNotNull(jsonObject, "name"))
                .avatarUrl(getAsStringIfPresentAndNotNull(jsonObject, "avatar_url"));

        return switch (userType) {
            case PERSON -> buildPerson(user, jsonObject);
            case BOT -> buildBot(user, jsonObject);
            case INTEGRATION, UNKNOWN -> user;
        };
    }

    private People buildPerson(User user, JsonObject jsonObject) {
        return new People(user)
                .email(jsonObject.getAsJsonObject("person").get("email").getAsString());
    }

    private Bot buildBot(User user, JsonObject jsonObject) {
        if (jsonObject.getAsJsonObject("bot").isJsonNull() || jsonObject.getAsJsonObject("bot").isEmpty()) {
            return new Bot(user);
        }
        var bot = jsonObject.getAsJsonObject("bot");
        var owner = bot.getAsJsonObject("owner");
        return new Bot(user)
                .owner(new BotOwner()
                        .type(BotOwnerType.fromString(owner.get("type").getAsString()))
                        .workspaces(owner.get("workspace").getAsBoolean())
                )
                .workspaceName(bot.get("workspace_name").getAsString());
    }
}
