package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import de.flix29.notionApiClient.model.Parent;
import de.flix29.notionApiClient.model.User;
import de.flix29.notionApiClient.model.block.Block;
import de.flix29.notionApiClient.model.block.BlockType;
import de.flix29.notionApiClient.model.block.blockContent.BlockContent;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.util.UUID;

public class CustomBlockDeserializer implements JsonDeserializer<Block> {
    @Override
    public Block deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }

        var jsonObject = jsonElement.getAsJsonObject();
        var blockType = BlockType.fromString(jsonObject.get("type").getAsString());

        return new Block()
                .id(UUID.fromString(jsonObject.get("id").getAsString()))
                .parent(new CustomParentDeserializer().deserialize(jsonObject.get("parent"), Parent.class, jsonDeserializationContext))
                .createdTime(OffsetDateTime.parse(jsonObject.get("created_time").getAsString()))
                .lastEditedTime(OffsetDateTime.parse(jsonObject.get("last_edited_time").getAsString()))
                .createdBy(new CustomUserDeserializer().deserialize(jsonObject.get("created_by"), User.class, jsonDeserializationContext))
                .lastEditedBy(new CustomUserDeserializer().deserialize(jsonObject.get("last_edited_by"), User.class, jsonDeserializationContext))
                .hasChildren(jsonObject.get("has_children").getAsBoolean())
                .archived(jsonObject.get("archived").getAsBoolean())
                .deleted(jsonObject.get("in_trash").getAsBoolean())
                .type(blockType)
                .blockContent(
                        new CustomBlockContentDeserializer()
                                .deserialize(
                                        jsonObject.get(blockType.getType()),
                                        BlockContent.class,
                                        jsonDeserializationContext,
                                        blockType
                                )
                );
    }
}
