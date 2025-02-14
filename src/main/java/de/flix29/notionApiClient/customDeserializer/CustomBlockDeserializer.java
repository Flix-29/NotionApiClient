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

import static de.flix29.notionApiClient.customDeserializer.CustomDeserializerUtils.*;

public class CustomBlockDeserializer implements JsonDeserializer<Block> {
    @Override
    public Block deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }

        var jsonObject = jsonElement.getAsJsonObject();
        var blockType = BlockType.fromString(getAsStringIfPresentAndNotNull(jsonObject, "type"));

        return new Block()
                .setId(getUUIDFromJsonElement(jsonObject, "id"))
                .setParent(new CustomParentDeserializer().deserialize(jsonObject.get("parent"), Parent.class, jsonDeserializationContext))
                .setCreatedTime(new CustomOffsetDateTimeDeserializer().deserialize(jsonObject.get("created_time"), OffsetDateTime.class, jsonDeserializationContext))
                .setLastEditedTime(new CustomOffsetDateTimeDeserializer().deserialize(jsonObject.get("last_edited_time"), OffsetDateTime.class, jsonDeserializationContext))
                .setCreatedBy(new CustomUserDeserializer().deserialize(jsonObject.get("created_by"), User.class, jsonDeserializationContext))
                .setLastEditedBy(new CustomUserDeserializer().deserialize(jsonObject.get("last_edited_by"), User.class, jsonDeserializationContext))
                .setHasChildren(getAsBooleanIfPresentAndNotNull(jsonObject, "has_children"))
                .setArchived(getAsBooleanIfPresentAndNotNull(jsonObject, "archived"))
                .setDeleted(getAsBooleanIfPresentAndNotNull(jsonObject, "in_trash"))
                .setType(blockType)
                .setBlockContent(
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
