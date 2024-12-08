package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonObject;

public class CustomDeserializerUtils {

    private CustomDeserializerUtils() {
    }

    protected static String getAsStringIfPresentAndNotNull(JsonObject jsonObject, String key) {
        return jsonObject.has(key) && !(jsonObject.get(key).isJsonNull()) ? jsonObject.get(key).getAsString() : null;
    }
}
