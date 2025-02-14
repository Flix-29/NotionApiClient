package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import de.flix29.notionApiClient.model.*;
import de.flix29.notionApiClient.model.database.*;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;

import static de.flix29.notionApiClient.customDeserializer.CustomDeserializerUtils.*;
import static de.flix29.notionApiClient.customDeserializer.CustomModelTypes.PROPERTY_LIST_TYPE;
import static de.flix29.notionApiClient.customDeserializer.CustomModelTypes.RICH_TEXT_LIST_TYPE;

public class CustomDatabaseDeserializer implements JsonDeserializer<Database> {
    @Override
    public Database deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }

        var jsonObject = jsonElement.getAsJsonObject();
        if (jsonObject == null || jsonObject.isJsonNull()) {
            return null;
        }
        return new Database()
                .setId(getUUIDFromJsonElement(jsonObject, "id"))
                .setCreatedTime(new CustomOffsetDateTimeDeserializer().deserialize(jsonObject.get("created_time"), OffsetDateTime.class, jsonDeserializationContext))
                .setCreatedBy(new CustomUserDeserializer().deserialize(jsonObject.get("created_by"), User.class, jsonDeserializationContext))
                .setLastEditedTime(new CustomOffsetDateTimeDeserializer().deserialize(jsonObject.get("last_edited_time"), OffsetDateTime.class, jsonDeserializationContext))
                .setLastEditedBy(new CustomUserDeserializer().deserialize(jsonObject.get("last_edited_by"), User.class, jsonDeserializationContext))
                .setTitel(new CustomRichTextDeserializer().deserialize(jsonObject.get("title"), RICH_TEXT_LIST_TYPE, jsonDeserializationContext))
                .setDescription(new CustomRichTextDeserializer().deserialize(jsonObject.get("description"), RICH_TEXT_LIST_TYPE, jsonDeserializationContext))
                .setInline(getAsBooleanIfPresentAndNotNull(jsonObject, "is_inline"))
                .setIcon(new CustomIconDeserializer().deserialize(jsonObject.get("icon"), Icon.class, jsonDeserializationContext))
                .setCover(new CustomFileDeserializer().deserialize(jsonObject.get("cover"), File.class, jsonDeserializationContext))
                .setProperties(new CustomDatabasePropertiesDeserializer().deserialize(jsonObject.get("properties"), PROPERTY_LIST_TYPE, jsonDeserializationContext))
                .setParent(new CustomParentDeserializer().deserialize(jsonObject.get("parent"), Parent.class, jsonDeserializationContext))
                .setUrl(getAsStringIfPresentAndNotNull(jsonObject, "url"))
                .setArchived(getAsBooleanIfPresentAndNotNull(jsonObject, "archived"))
                .setDeleted(getAsBooleanIfPresentAndNotNull(jsonObject, "in_trash"))
                .setPublicUrl(getAsStringIfPresentAndNotNull(jsonObject, "public_url"));
    }
}
