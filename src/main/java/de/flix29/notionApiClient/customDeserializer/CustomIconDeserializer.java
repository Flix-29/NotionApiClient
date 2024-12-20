package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import de.flix29.notionApiClient.model.Emoji;
import de.flix29.notionApiClient.model.File;
import de.flix29.notionApiClient.model.Icon;

import java.lang.reflect.Type;

import static de.flix29.notionApiClient.customDeserializer.CustomDeserializerUtils.getAsStringIfPresentAndNotNull;
import static de.flix29.notionApiClient.customDeserializer.CustomDeserializerUtils.getStringFromJsonElement;

public class CustomIconDeserializer implements JsonDeserializer<Icon> {
    @Override
    public Icon deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }

        var jsonObject = jsonElement.getAsJsonObject();
        var iconType = getAsStringIfPresentAndNotNull(jsonObject, "type");

        if (iconType == null) {
            throw new JsonParseException("Icon type is missing");
        }

        return switch (iconType) {
            case "file", "external":
                yield new CustomFileDeserializer().deserialize(jsonElement, File.class, jsonDeserializationContext);
            case "emoji":
                yield new Emoji(getStringFromJsonElement(jsonElement, "emoji"));
            default:
                throw new JsonParseException("Unknown icon type: " + iconType);
        };
    }
}
