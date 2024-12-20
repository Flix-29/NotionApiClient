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
import java.util.Objects;
import java.util.UUID;

import static de.flix29.notionApiClient.customDeserializer.CustomDeserializerUtils.getAsBooleanIfPresentAndNotNull;
import static de.flix29.notionApiClient.customDeserializer.CustomDeserializerUtils.getAsStringIfPresentAndNotNull;

public class CustomBlockDeserializer implements JsonDeserializer<Block> {
    @Override
    public Block deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }

        var jsonObject = jsonElement.getAsJsonObject();
        var blockType = BlockType.fromString(getAsStringIfPresentAndNotNull(jsonObject, "type"));

        return new Block()
                .id(UUID.fromString(Objects.requireNonNull(getAsStringIfPresentAndNotNull(jsonObject, "id"))))
                .parent(new CustomParentDeserializer().deserialize(jsonObject.get("parent"), Parent.class, jsonDeserializationContext))
                .createdTime(new CustomOffsetDateTimeDeserializer().deserialize(jsonObject.get("created_time"), OffsetDateTime.class, jsonDeserializationContext))
                .lastEditedTime(new CustomOffsetDateTimeDeserializer().deserialize(jsonObject.get("last_edited_time"), OffsetDateTime.class, jsonDeserializationContext))
                .createdBy(new CustomUserDeserializer().deserialize(jsonObject.get("created_by"), User.class, jsonDeserializationContext))
                .lastEditedBy(new CustomUserDeserializer().deserialize(jsonObject.get("last_edited_by"), User.class, jsonDeserializationContext))
                .hasChildren(getAsBooleanIfPresentAndNotNull(jsonObject, "has_children"))
                .archived(getAsBooleanIfPresentAndNotNull(jsonObject, "archived"))
                .deleted(getAsBooleanIfPresentAndNotNull(jsonObject, "in_trash"))
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
