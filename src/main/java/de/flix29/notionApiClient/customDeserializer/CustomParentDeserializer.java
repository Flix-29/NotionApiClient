package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import de.flix29.notionApiClient.model.Parent;
import de.flix29.notionApiClient.model.ParentType;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.UUID;

import static de.flix29.notionApiClient.customDeserializer.CustomDeserializerUtils.getAsStringIfPresentAndNotNull;

public class CustomParentDeserializer implements JsonDeserializer<Parent> {
    @Override
    public Parent deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }

        var jsonObject = jsonElement.getAsJsonObject();
        var parentType = ParentType.fromString(getAsStringIfPresentAndNotNull(jsonObject, "type"));

        var parent = new Parent().type(parentType);

        return switch (parentType) {
            case DATABASE -> parent.id(UUID.fromString(Objects.requireNonNull(getAsStringIfPresentAndNotNull(jsonObject, "database_id"))));
            case PAGE -> parent.id(UUID.fromString(Objects.requireNonNull(getAsStringIfPresentAndNotNull(jsonObject, "page_id"))));
            case WORKSPACE -> parent.workspace(true);
            case BLOCK -> parent.id(UUID.fromString(Objects.requireNonNull(getAsStringIfPresentAndNotNull(jsonObject, "block_id"))));
        };
    }
}
