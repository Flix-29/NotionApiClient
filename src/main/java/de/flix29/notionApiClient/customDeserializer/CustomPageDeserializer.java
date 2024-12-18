package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import de.flix29.notionApiClient.model.File;
import de.flix29.notionApiClient.model.Icon;
import de.flix29.notionApiClient.model.Parent;
import de.flix29.notionApiClient.model.User;
import de.flix29.notionApiClient.model.page.Page;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;

import static de.flix29.notionApiClient.customDeserializer.CustomDeserializerUtils.getAsBooleanIfPresentAndNotNull;
import static de.flix29.notionApiClient.customDeserializer.CustomDeserializerUtils.getAsStringIfPresentAndNotNull;
import static de.flix29.notionApiClient.customDeserializer.CustomModelTypes.PROPERTY_LIST_TYPE;

public class CustomPageDeserializer implements JsonDeserializer<Page> {
    @Override
    public Page deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }

        var jsonObject = jsonElement.getAsJsonObject();
        if (jsonObject == null || jsonObject.isJsonNull()) {
            return null;
        }
        return new Page()
                .id(getAsStringIfPresentAndNotNull(jsonObject, "id"))
                .createdTime(new CustomOffsetDateTimeDeserializer().deserialize(jsonObject.get("created_time"), OffsetDateTime.class, jsonDeserializationContext))
                .lastEditedTime(new CustomOffsetDateTimeDeserializer().deserialize(jsonObject.get("last_edited_time"), OffsetDateTime.class, jsonDeserializationContext))
                .createdBy(new CustomUserDeserializer().deserialize(jsonObject.get("created_by"), User.class, jsonDeserializationContext))
                .lastEditedBy(new CustomUserDeserializer().deserialize(jsonObject.get("last_edited_by"), User.class, jsonDeserializationContext))
                .archived(getAsBooleanIfPresentAndNotNull(jsonObject, "archived"))
                .deleted(getAsBooleanIfPresentAndNotNull(jsonObject, "in_trash"))
                .icon(new CustomIconDeserializer().deserialize(jsonObject.get("icon"), Icon.class, jsonDeserializationContext))
                .cover(new CustomFileDeserializer().deserialize(jsonObject.get("cover"), File.class, jsonDeserializationContext))
                .properties(new CustomPagePropertiesDeserializer().deserialize(jsonObject.get("properties"), PROPERTY_LIST_TYPE, jsonDeserializationContext))
                .parent(new CustomParentDeserializer().deserialize(jsonObject.get("parent"), Parent.class, jsonDeserializationContext))
                .url(getAsStringIfPresentAndNotNull(jsonObject, "url"))
                .publicUrl(getAsStringIfPresentAndNotNull(jsonObject, "public_url"));
    }
}
