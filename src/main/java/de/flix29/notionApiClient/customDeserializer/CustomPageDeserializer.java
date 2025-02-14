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
                .setId(getAsStringIfPresentAndNotNull(jsonObject, "id"))
                .setCreatedTime(new CustomOffsetDateTimeDeserializer().deserialize(jsonObject.get("created_time"), OffsetDateTime.class, jsonDeserializationContext))
                .setLastEditedTime(new CustomOffsetDateTimeDeserializer().deserialize(jsonObject.get("last_edited_time"), OffsetDateTime.class, jsonDeserializationContext))
                .setCreatedBy(new CustomUserDeserializer().deserialize(jsonObject.get("created_by"), User.class, jsonDeserializationContext))
                .setLastEditedBy(new CustomUserDeserializer().deserialize(jsonObject.get("last_edited_by"), User.class, jsonDeserializationContext))
                .setArchived(getAsBooleanIfPresentAndNotNull(jsonObject, "archived"))
                .setDeleted(getAsBooleanIfPresentAndNotNull(jsonObject, "in_trash"))
                .setIcon(new CustomIconDeserializer().deserialize(jsonObject.get("icon"), Icon.class, jsonDeserializationContext))
                .setCover(new CustomFileDeserializer().deserialize(jsonObject.get("cover"), File.class, jsonDeserializationContext))
                .setProperties(new CustomPagePropertiesDeserializer().deserialize(jsonObject.get("properties"), PROPERTY_LIST_TYPE, jsonDeserializationContext))
                .setParent(new CustomParentDeserializer().deserialize(jsonObject.get("parent"), Parent.class, jsonDeserializationContext))
                .setUrl(getAsStringIfPresentAndNotNull(jsonObject, "url"))
                .setPublicUrl(getAsStringIfPresentAndNotNull(jsonObject, "public_url"));
    }
}
