package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.*;
import de.flix29.notionApiClient.model.User;

import java.lang.reflect.Type;
import java.util.List;

public class CustomUserListDeserializer implements JsonDeserializer<List<User>> {

    @Override
    public List<User> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }

        var users = jsonElement.getAsJsonObject().getAsJsonArray("results");

        if (users == null || users.isJsonNull()) {
            return null;
        }

        return users.asList().stream()
                .map(user -> new CustomUserDeserializer().deserialize(user, type, jsonDeserializationContext))
                .toList();
    }
}
