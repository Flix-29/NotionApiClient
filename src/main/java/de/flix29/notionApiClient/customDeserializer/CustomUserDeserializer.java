package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.*;
import de.flix29.notionApiClient.model.*;

import java.lang.reflect.Type;

import static de.flix29.notionApiClient.customDeserializer.CustomDeserializerUtils.*;

public class CustomUserDeserializer implements JsonDeserializer<User> {
    @Override
    public User deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }

        var jsonObject = jsonElement.getAsJsonObject();

        if (jsonObject == null || jsonObject.isJsonNull() || jsonObject.isJsonPrimitive()) {
            return null;
        }
        var userType = UserType.fromString(getAsStringIfPresentAndNotNull(jsonObject, "type"));

        var user = new User()
                .setId(getUUIDFromJsonElement(jsonObject, "id"))
                .setType(userType)
                .setName(getAsStringIfPresentAndNotNull(jsonObject, "name"))
                .setAvatarUrl(getAsStringIfPresentAndNotNull(jsonObject, "avatar_url"));

        return switch (userType) {
            case PERSON -> buildPerson(user, jsonObject);
            case BOT -> buildBot(user, jsonObject);
            case INTEGRATION, UNKNOWN -> user;
        };
    }

    private People buildPerson(User user, JsonObject jsonObject) {
        return new People(user)
                .setEmail(getAsStringIfPresentAndNotNull(jsonObject.getAsJsonObject("person"), "email"));
    }

    private Bot buildBot(User user, JsonObject jsonObject) {
        if (jsonObject.getAsJsonObject("bot").isJsonNull() || jsonObject.getAsJsonObject("bot").isEmpty()) {
            return new Bot(user);
        }
        var bot = jsonObject.getAsJsonObject("bot");
        var owner = bot.getAsJsonObject("owner");
        return new Bot(user)
                .setOwner(new BotOwner()
                        .setType(BotOwnerType.fromString(getAsStringIfPresentAndNotNull(owner, "type")))
                        .setWorkspaces(getAsBooleanIfPresentAndNotNull(owner, "workspace"))
                )
                .setWorkspaceName(getAsStringIfPresentAndNotNull(bot, "workspace_name"));
    }
}
