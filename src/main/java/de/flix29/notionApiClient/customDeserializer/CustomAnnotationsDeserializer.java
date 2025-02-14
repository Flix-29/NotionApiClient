package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import de.flix29.notionApiClient.model.Annotations;
import de.flix29.notionApiClient.model.Color;

import java.lang.reflect.Type;

import static de.flix29.notionApiClient.customDeserializer.CustomDeserializerUtils.getAsBooleanIfPresentAndNotNull;
import static de.flix29.notionApiClient.customDeserializer.CustomDeserializerUtils.getAsStringIfPresentAndNotNull;

public class CustomAnnotationsDeserializer implements JsonDeserializer<Annotations> {
    @Override
    public Annotations deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }

        var jsonObject = jsonElement.getAsJsonObject();
        return new Annotations()
                .setBold(getAsBooleanIfPresentAndNotNull(jsonObject, "bold"))
                .setItalic(getAsBooleanIfPresentAndNotNull(jsonObject, "italic"))
                .setStrikethrough(getAsBooleanIfPresentAndNotNull(jsonObject, "strikethrough"))
                .setUnderline(getAsBooleanIfPresentAndNotNull(jsonObject, "underline"))
                .setCode(getAsBooleanIfPresentAndNotNull(jsonObject, "code"))
                .setColor(Color.fromString(getAsStringIfPresentAndNotNull(jsonObject, "color")))
                .setBackgroundColor(Color.fromString(getAsStringIfPresentAndNotNull(jsonObject, "color")));
    }
}
