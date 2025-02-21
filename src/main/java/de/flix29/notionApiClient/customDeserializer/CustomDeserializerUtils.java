package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.flix29.notionApiClient.model.Color;
import de.flix29.notionApiClient.model.File;
import de.flix29.notionApiClient.model.RichText;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

import static de.flix29.notionApiClient.customDeserializer.CustomModelTypes.RICH_TEXT_LIST_TYPE;

public class CustomDeserializerUtils {

    private CustomDeserializerUtils() {
    }

    protected static boolean getAsBooleanIfPresentAndNotNull(@NotNull JsonObject jsonObject, @NotNull String key) {
        return jsonObject.has(key) && !(jsonObject.get(key).isJsonNull()) && jsonObject.get(key).getAsBoolean();
    }

    protected static int getAsIntegerIfPresentAndNotNull(@NotNull JsonObject jsonObject, @NotNull String key) {
        return jsonObject.has(key) && !(jsonObject.get(key).isJsonNull()) ? jsonObject.get(key).getAsInt() : 0;
    }

    protected static double getAsDoubleIfPresentAndNotNull(@NotNull JsonObject jsonObject, @NotNull String key) {
        return jsonObject.has(key) && !(jsonObject.get(key).isJsonNull()) ? jsonObject.get(key).getAsDouble() : 0.0;
    }

    protected static String getAsStringIfPresentAndNotNull(@NotNull JsonObject jsonObject, @NotNull String key) {
        return jsonObject.has(key) && !(jsonObject.get(key).isJsonNull()) ? jsonObject.get(key).getAsString() : null;
    }

    protected static String getStringFromJsonElement(@NotNull JsonElement jsonElement, @NotNull String key) {
        if (jsonElement.isJsonNull() || !jsonElement.getAsJsonObject().has(key) ||
                jsonElement.getAsJsonObject().get(key) == null || jsonElement.getAsJsonObject().get(key).isJsonNull()) {
            return null;
        }
        return jsonElement.getAsJsonObject().get(key).getAsString();
    }

    protected static UUID getUUIDFromJsonElement(@NotNull JsonElement jsonElement, @NotNull String key) {
        if (jsonElement.isJsonNull() || !jsonElement.getAsJsonObject().has(key) ||
                jsonElement.getAsJsonObject().get(key) == null || jsonElement.getAsJsonObject().get(key).isJsonNull()) {
            return null;
        }
        return UUID.fromString(jsonElement.getAsJsonObject().get(key).getAsString());
    }

    protected static Color getColorFromJsonElement(@NotNull JsonElement jsonElement) {
        if (jsonElement.isJsonNull() || !jsonElement.getAsJsonObject().has("color")) {
            return null;
        }
        return Color.fromString(jsonElement.getAsJsonObject().get("color").getAsString());
    }

    protected static List<RichText> getRichTextFromJsonElement(@NotNull JsonElement jsonElement, @NotNull String rich_text, @NotNull JsonDeserializationContext jsonDeserializationContext) {
        if (jsonElement.isJsonNull() || !jsonElement.getAsJsonObject().has(rich_text)) {
            return null;
        }
        return new CustomRichTextDeserializer().deserialize(jsonElement.getAsJsonObject().get(rich_text), RICH_TEXT_LIST_TYPE, jsonDeserializationContext);
    }

    protected static File getFileFromJsonElement(@NotNull JsonElement jsonElement, @NotNull JsonDeserializationContext jsonDeserializationContext) {
        if (jsonElement.isJsonNull() || (!jsonElement.getAsJsonObject().has("file") && !jsonElement.getAsJsonObject().has("external"))) {
            return null;
        }
        return new CustomFileDeserializer().deserialize(jsonElement, File.class, jsonDeserializationContext);
    }
}
