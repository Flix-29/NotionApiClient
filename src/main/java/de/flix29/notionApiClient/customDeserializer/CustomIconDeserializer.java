package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import de.flix29.notionApiClient.model.Emoji;
import de.flix29.notionApiClient.model.File;
import de.flix29.notionApiClient.model.Icon;

import java.lang.reflect.Type;

public class CustomIconDeserializer implements JsonDeserializer<Icon> {
    @Override
    public Icon deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement.isJsonNull()) {
            return null;
        }
        var jsonObject = jsonElement.getAsJsonObject();
        var iconType = jsonObject.get("type").getAsString();
        return switch (iconType) {
            case "file", "external":
                yield new CustomFileDeserializer().deserialize(jsonElement, File.class, jsonDeserializationContext);
            case "emoji":
                yield new Emoji(jsonObject.get("emoji").getAsString());
            default:
                throw new JsonParseException("Unknown icon type: " + iconType);
        };
    }
}
