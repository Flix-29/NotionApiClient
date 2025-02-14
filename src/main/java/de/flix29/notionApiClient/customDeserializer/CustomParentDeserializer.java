package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import de.flix29.notionApiClient.model.Parent;
import de.flix29.notionApiClient.model.ParentType;

import java.lang.reflect.Type;

import static de.flix29.notionApiClient.customDeserializer.CustomDeserializerUtils.getAsStringIfPresentAndNotNull;
import static de.flix29.notionApiClient.customDeserializer.CustomDeserializerUtils.getUUIDFromJsonElement;

public class CustomParentDeserializer implements JsonDeserializer<Parent> {
    @Override
    public Parent deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }

        var jsonObject = jsonElement.getAsJsonObject();
        var parentType = ParentType.fromString(getAsStringIfPresentAndNotNull(jsonObject, "type"));

        var parent = new Parent().setType(parentType);

        return switch (parentType) {
            case DATABASE -> parent.setId(getUUIDFromJsonElement(jsonObject, "database_id"));
            case PAGE -> parent.setId(getUUIDFromJsonElement(jsonObject, "page_id"));
            case WORKSPACE -> parent.setWorkspace(true);
            case BLOCK -> parent.setId(getUUIDFromJsonElement(jsonObject, "block_id"));
        };
    }
}
