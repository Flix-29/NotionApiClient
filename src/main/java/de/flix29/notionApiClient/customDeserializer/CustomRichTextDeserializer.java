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
                            .plainText(getAsStringIfPresentAndNotNull(jsonObject, "plain_text"))
                            .href(getAsStringIfPresentAndNotNull(jsonObject, "href"))
                            .annotations(new CustomAnnotationsDeserializer().deserialize(jsonObject.get("annotations"), Annotations.class, jsonDeserializationContext));

                    if (textType == null) {
                        throw new JsonParseException("No text type found in rich text object");
                    }

                    return switch (textType) {
                        case "text" -> {
                            var text = item.getAsJsonObject().get("text").getAsJsonObject();
                            yield new Text(richText)
                                    .content(getAsStringIfPresentAndNotNull(text, "content"))
                                    .link(text.get("link").isJsonNull() ? null : getAsStringIfPresentAndNotNull(text.getAsJsonObject("link"), "url"));
                        }
                        case "mention" -> {
                            var mentionType = MentionType.fromString(jsonObject.getAsJsonObject("mention").get("type").getAsString());
                            var mention = jsonObject.getAsJsonObject("mention").getAsJsonObject(mentionType.getType());

                            yield switch (mentionType) {
                                case DATABASE -> new MentionDatabase(richText)
                                        .id(mention.get("id").getAsString());
                                case DATE -> new MentionDate(richText)
                                        .start(OffsetDateTime.parse(mention.get("start").getAsString()))
                                        .end(OffsetDateTime.parse(mention.get("end").getAsString()))
                                        .timeZone(mention.get("time_zone").getAsString());
                                case LINK_PREVIEW -> new MentionLinkPreview(richText)
                                        .href(getAsStringIfPresentAndNotNull(mention, "href"))
                                        .title(getAsStringIfPresentAndNotNull(mention, "title"))
                                        .iconUrl(getAsStringIfPresentAndNotNull(mention, "icon_url"))
                                        .description(getAsStringIfPresentAndNotNull(mention, "description"))
                                        .linkProvider(getAsStringIfPresentAndNotNull(mention, "link_provider"))
                                        .thumbnailUrl(getAsStringIfPresentAndNotNull(mention, "thumbnail_url"));
                                case PAGE -> new MentionPage(richText).
                                        id(mention.get("id").getAsString());
                                case TEMPLATE_MENTION -> new MentionTemplate(richText)
                                        .date(mention.get("date").getAsString())
                                        .user(mention.get("user").getAsString());
                                case USER -> new MentionUser(richText)
                                        .id(mention.get("id").getAsString());
                            };
                        }
                        case "equation" -> new Equation(richText)
                                .equation(getAsStringIfPresentAndNotNull(jsonObject, "equation"));
                        default -> throw new IllegalStateException("Unexpected texttype value: " + textType);
                    };
                }).toList();
    }
}
