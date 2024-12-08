package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import de.flix29.notionApiClient.model.Parent;
import de.flix29.notionApiClient.model.ParentType;

import java.lang.reflect.Type;
import java.util.UUID;

public class CustomParentDeserializer implements JsonDeserializer<Parent> {
    @Override
    public Parent deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        var jsonObject = jsonElement.getAsJsonObject();
        var parentType = ParentType.fromString(jsonObject.get("type").getAsString());

        var parent = new Parent().type(parentType);

        return switch (parentType) {
            case DATABASE -> parent.id(UUID.fromString(jsonObject.get("database_id").getAsString()));
            case PAGE -> parent.id(UUID.fromString(jsonObject.get("page_id").getAsString()));
            case WORKSPACE -> parent.workspace(true);
            case BLOCK -> parent.id(UUID.fromString(jsonObject.get("block_id").getAsString()));
        };
    }
}
