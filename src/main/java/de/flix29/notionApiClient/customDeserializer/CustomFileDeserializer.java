package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import de.flix29.notionApiClient.model.File;
import de.flix29.notionApiClient.model.FileType;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;

import static de.flix29.notionApiClient.customDeserializer.CustomDeserializerUtils.getAsStringIfPresentAndNotNull;

public class CustomFileDeserializer implements JsonDeserializer<File> {
    @Override
    public File deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }

        var jsonObject = jsonElement.getAsJsonObject();
        var fileType = getAsStringIfPresentAndNotNull(jsonObject, "type");
        var fileObject = jsonObject.getAsJsonObject(fileType);
        return new File()
                .setType(FileType.fromString(fileType))
                .setUrl(getAsStringIfPresentAndNotNull(fileObject, "url"))
                .setExpirationTime(new CustomOffsetDateTimeDeserializer().deserialize(fileObject.get("expiry_time"), OffsetDateTime.class, jsonDeserializationContext));
    }
}
