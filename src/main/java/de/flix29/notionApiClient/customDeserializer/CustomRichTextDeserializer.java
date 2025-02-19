package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import de.flix29.notionApiClient.model.*;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.util.List;

import static de.flix29.notionApiClient.customDeserializer.CustomDeserializerUtils.getAsStringIfPresentAndNotNull;

public class CustomRichTextDeserializer implements JsonDeserializer<List<RichText>> {
    @Override
    public List<RichText> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }

        var jsonArray = jsonElement.getAsJsonArray();
        if (jsonArray == null || jsonArray.isEmpty()) {
            return null;
        }

        return jsonArray.asList().stream()
                .filter(item -> item != null && !item.isJsonNull())
                .map(item -> {
                    var jsonObject = item.getAsJsonObject();
                    var textType = getAsStringIfPresentAndNotNull(jsonObject, "type");

                    var richText = new RichText()
                            .setPlainText(getAsStringIfPresentAndNotNull(jsonObject, "plain_text"))
                            .setHref(getAsStringIfPresentAndNotNull(jsonObject, "href"))
                            .setAnnotations(new CustomAnnotationsDeserializer().deserialize(jsonObject.get("annotations"), Annotations.class, jsonDeserializationContext));

                    if (textType == null) {
                        throw new JsonParseException("No text type found in rich text object");
                    }

                    return switch (textType) {
                        case "text" -> {
                            var text = item.getAsJsonObject().get("text").getAsJsonObject();
                            yield new Text(richText)
                                    .setContent(getAsStringIfPresentAndNotNull(text, "content"))
                                    .setLink(text.get("link").isJsonNull() ? null : getAsStringIfPresentAndNotNull(text.getAsJsonObject("link"), "url"));
                        }
                        case "mention" -> {
                            var mentionType = MentionType.fromString(jsonObject.getAsJsonObject("mention").get("type").getAsString());
                            var mention = jsonObject.getAsJsonObject("mention").getAsJsonObject(mentionType.getType());

                            yield switch (mentionType) {
                                case DATABASE -> new MentionDatabase(richText)
                                        .setId(mention.get("id").getAsString());
                                case DATE -> new MentionDate(richText)
                                        .setStart(new CustomOffsetDateTimeDeserializer().deserialize(mention.get("start"), OffsetDateTime.class, jsonDeserializationContext))
                                        .setEnd(new CustomOffsetDateTimeDeserializer().deserialize(mention.get("end"), OffsetDateTime.class, jsonDeserializationContext))
                                        .setTimeZone(getAsStringIfPresentAndNotNull(mention, "time_zone"));
                                case LINK_PREVIEW -> new MentionLinkPreview(richText)
                                        .setHref(getAsStringIfPresentAndNotNull(mention, "href"))
                                        .setTitle(getAsStringIfPresentAndNotNull(mention, "title"))
                                        .setIconUrl(getAsStringIfPresentAndNotNull(mention, "icon_url"))
                                        .setDescription(getAsStringIfPresentAndNotNull(mention, "description"))
                                        .setLinkProvider(getAsStringIfPresentAndNotNull(mention, "link_provider"))
                                        .setThumbnailUrl(getAsStringIfPresentAndNotNull(mention, "thumbnail_url"));
                                case PAGE -> new MentionPage(richText).
                                        setId(mention.get("id").getAsString());
                                case TEMPLATE_MENTION -> new MentionTemplate(richText)
                                        .setDate(mention.get("date").getAsString())
                                        .setUser(mention.get("user").getAsString());
                                case USER -> new MentionUser(richText)
                                        .setId(mention.get("id").getAsString());
                            };
                        }
                        case "equation" -> new Equation(richText)
                                .setEquation(getAsStringIfPresentAndNotNull(jsonObject.getAsJsonObject("equation"), "expression"));
                        default -> throw new IllegalStateException("Unexpected texttype value: " + textType);
                    };
                }).toList();
    }
}
