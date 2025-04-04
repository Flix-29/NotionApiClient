package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import de.flix29.notionApiClient.model.block.Block;

import java.lang.reflect.Type;
import java.util.List;

public class CustomBlockListDeserializer implements JsonDeserializer<List<Block>> {
    @Override
    public List<Block> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }

        var jsonArray = jsonElement.getAsJsonObject().getAsJsonArray("results");
        if (jsonArray == null || jsonArray.isJsonNull()) {
            return null;
        }

        return jsonArray.asList().stream()
                .filter(item -> item != null && !item.isJsonNull())
                .map(block -> new CustomBlockDeserializer().deserialize(block, Block.class, jsonDeserializationContext))
                .toList();
    }
}
