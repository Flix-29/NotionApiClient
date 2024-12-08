package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import de.flix29.notionApiClient.model.File;
import de.flix29.notionApiClient.model.FileType;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;

public class CustomFileDeserializer implements JsonDeserializer<File> {
    @Override
    public File deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }
        var jsonObject = jsonElement.getAsJsonObject();
        var fileType = jsonObject.get("type").getAsString();
        var fileObject = jsonObject.get(fileType).getAsJsonObject();
        return new File()
                .type(FileType.fromString(fileType))
                .url(fileObject.get("url").getAsString())
                .expirationTime(new CustomOffsetDateTimeDeserializer().deserialize(fileObject.get("expiry_time"), OffsetDateTime.class, jsonDeserializationContext));
    }
}
