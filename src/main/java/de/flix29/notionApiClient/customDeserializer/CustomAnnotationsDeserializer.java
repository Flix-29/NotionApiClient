package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import de.flix29.notionApiClient.model.Annotations;

import java.lang.reflect.Type;

public class CustomAnnotationsDeserializer implements JsonDeserializer<Annotations> {
    @Override
    public Annotations deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        var jsonObject = jsonElement.getAsJsonObject();
        return new Annotations()
                .bold(jsonObject.get("bold").getAsBoolean())
                .italic(jsonObject.get("italic").getAsBoolean())
                .strikethrough(jsonObject.get("strikethrough").getAsBoolean())
                .underline(jsonObject.get("underline").getAsBoolean())
                .code(jsonObject.get("code").getAsBoolean())
                .color(jsonObject.get("color").getAsString());
    }
}
