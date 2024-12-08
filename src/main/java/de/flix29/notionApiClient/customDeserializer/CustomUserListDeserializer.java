package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.*;
import de.flix29.notionApiClient.model.User;

import java.lang.reflect.Type;
import java.util.List;

public class CustomUserListDeserializer implements JsonDeserializer<List<User>> {

    @Override
    public List<User> deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        var users = jsonObject.getAsJsonArray("results");
        return users.asList().stream()
                .map(user -> new CustomUserDeserializer().deserialize(user, type, jsonDeserializationContext))
                .toList();
    }
}
