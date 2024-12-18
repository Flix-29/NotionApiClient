package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import de.flix29.notionApiClient.model.Annotations;

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
                .bold(getAsBooleanIfPresentAndNotNull(jsonObject, "bold"))
                .italic(getAsBooleanIfPresentAndNotNull(jsonObject, "italic"))
                .strikethrough(getAsBooleanIfPresentAndNotNull(jsonObject, "strikethrough"))
                .underline(getAsBooleanIfPresentAndNotNull(jsonObject, "underline"))
                .code(getAsBooleanIfPresentAndNotNull(jsonObject, "code"))
                .color(getAsStringIfPresentAndNotNull(jsonObject, "color"));
    }
}
