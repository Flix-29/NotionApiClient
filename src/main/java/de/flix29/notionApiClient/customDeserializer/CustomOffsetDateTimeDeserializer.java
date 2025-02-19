package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;

public class CustomOffsetDateTimeDeserializer implements JsonDeserializer<OffsetDateTime> {
    @Override
    public OffsetDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null || jsonElement.isJsonNull() || jsonElement.getAsString() == null) {
            return null;
        }

        if (jsonElement.getAsString().matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]{3}($|([+\\-])[0-9]{2}:[0-9]{2}$)")) {
            return OffsetDateTime.parse(jsonElement.getAsString());
        } else if (jsonElement.getAsString().matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
            return OffsetDateTime.parse(jsonElement.getAsString() + "T00:00:00.000+00:00");
        }
        return null;
    }

}
