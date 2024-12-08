package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import de.flix29.notionApiClient.model.*;
import de.flix29.notionApiClient.model.database.*;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.util.UUID;

import static de.flix29.notionApiClient.customDeserializer.CustomDeserializerUtils.getAsStringIfPresentAndNotNull;
import static de.flix29.notionApiClient.customDeserializer.CustomModelTypes.PROPERTY_LIST_TYPE;
import static de.flix29.notionApiClient.customDeserializer.CustomModelTypes.RICH_TEXT_LIST_TYPE;

public class CustomDatabaseDeserializer implements JsonDeserializer<Database> {
    @Override
    public Database deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        var jsonObject = jsonElement.getAsJsonObject();
        return new Database()
                .id(UUID.fromString(jsonObject.get("id").getAsString()))
                .createdTime(OffsetDateTime.parse(jsonObject.get("created_time").getAsString()))
                .createdBy(new CustomUserDeserializer().deserialize(jsonObject.get("created_by"), User.class, jsonDeserializationContext))
                .lastEditedTime(OffsetDateTime.parse(jsonObject.get("last_edited_time").getAsString()))
                .lastEditedBy(new CustomUserDeserializer().deserialize(jsonObject.get("last_edited_by"), User.class, jsonDeserializationContext))
                .titel(new CustomRichTextDeserializer().deserialize(jsonObject.get("title"), RICH_TEXT_LIST_TYPE, jsonDeserializationContext))
                .description(new CustomRichTextDeserializer().deserialize(jsonObject.get("description"), RICH_TEXT_LIST_TYPE, jsonDeserializationContext))
                .inline(jsonObject.get("is_inline").getAsBoolean())
                .icon(new CustomIconDeserializer().deserialize(jsonObject.get("icon"), Icon.class, jsonDeserializationContext))
                .cover(new CustomFileDeserializer().deserialize(jsonObject.get("cover"), File.class, jsonDeserializationContext))
                .properties(new CustomDatabasePropertiesDeserializer().deserialize(jsonObject.get("properties"), PROPERTY_LIST_TYPE, jsonDeserializationContext))
                .parent(new CustomParentDeserializer().deserialize(jsonObject.get("parent"), Parent.class, jsonDeserializationContext))
                .url(getAsStringIfPresentAndNotNull(jsonObject, "url"))
                .archived(jsonObject.get("archived").getAsBoolean())
                .deleted(jsonObject.get("in_trash").getAsBoolean())
                .publicUrl(getAsStringIfPresentAndNotNull(jsonObject, "public_url"));
    }
}
