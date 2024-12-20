package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.*;
import de.flix29.notionApiClient.model.Color;
import de.flix29.notionApiClient.model.User;
import de.flix29.notionApiClient.model.database.databaseProperty.*;
import de.flix29.notionApiClient.model.database.databaseProperty.Number;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

import static de.flix29.notionApiClient.customDeserializer.CustomDeserializerUtils.*;

public class CustomPagePropertiesDeserializer implements JsonDeserializer<List<Property>> {
    @Override
    public List<Property> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }

        var entries = jsonElement.getAsJsonObject().entrySet();
        if (entries == null || entries.isEmpty()) {
            return null;
        }
        return entries.stream()
                .filter(Objects::nonNull)
                .map(entry -> {
                    if (entry.getValue() == null || entry.getKey() == null || entry.getValue().isJsonNull()) {
                        return null;
                    }

                    var jsonObject = entry.getValue().getAsJsonObject();
                    var propertyType = PropertyType.fromString(getAsStringIfPresentAndNotNull(jsonObject, "type"));
                    var id = getAsStringIfPresentAndNotNull(jsonObject, "id");
                    var name = entry.getKey();

                    var property = switch (propertyType) {
                        case BUTTON -> new Button(); //TODO
                        case CHECKBOX ->
                                new Checkbox().checked(getAsBooleanIfPresentAndNotNull(jsonObject, "checkbox"));
                        case CREATED_BY -> new CreatedBy()
                                .user(new CustomUserDeserializer().deserialize(jsonObject.get("created_by"), User.class, jsonDeserializationContext));
                        case CREATED_TIME -> new CreatedTime()
                                .createdTime(new CustomOffsetDateTimeDeserializer().deserialize(jsonObject.get("created_time"), OffsetDateTime.class, jsonDeserializationContext));
                        case DATE -> new Date()
                                .start(new CustomOffsetDateTimeDeserializer().deserialize(jsonObject.getAsJsonObject("date").get("start"), OffsetDateTime.class, jsonDeserializationContext))
                                .end(new CustomOffsetDateTimeDeserializer().deserialize(jsonObject.getAsJsonObject("date").get("end"), OffsetDateTime.class, jsonDeserializationContext))
                                .timezone(getAsStringIfPresentAndNotNull(jsonObject.getAsJsonObject("date"), "timezone"));
                        case EMAIL -> new Email().email(getAsStringIfPresentAndNotNull(jsonObject, "email"));
                        case FILE -> new File()
                                .files(jsonObject.getAsJsonArray("files")
                                        .asList().stream()
                                        .filter(Objects::nonNull)
                                        .map(file -> new CustomFileDeserializer().deserialize(file, File.class, jsonDeserializationContext))
                                        .toList()
                                );
                        case FORMULA -> {
                            var formulaObject = jsonObject.getAsJsonObject("formula");
                            var valueType = getAsStringIfPresentAndNotNull(formulaObject, "type");
                            if (valueType == null) {
                                yield null;
                            }
                            yield switch (valueType) {
                                case "string" ->
                                        new Formula().stringValue(getAsStringIfPresentAndNotNull(formulaObject, "string"));
                                case "number" ->
                                        new Formula().numberValue(getAsDoubleIfPresentAndNotNull(formulaObject, "number"));
                                case "boolean" ->
                                        new Formula().booleanValue(getAsBooleanIfPresentAndNotNull(formulaObject, "boolean"));
                                case "date" ->
                                        new Formula().dateValue(new CustomOffsetDateTimeDeserializer().deserialize(formulaObject.get("date"), OffsetDateTime.class, jsonDeserializationContext));
                                default -> throw new IllegalStateException("Unexpected value: " + valueType);
                            };
                        }
                        case LAST_EDITED_BY -> new LastEditedBy()
                                .user(new CustomUserDeserializer().deserialize(jsonObject.get("last_edited_by"), User.class, jsonDeserializationContext));
                        case LAST_EDITED_TIME -> new LastEditedTime()
                                .lastEditedTime(new CustomOffsetDateTimeDeserializer().deserialize(jsonObject.get("last_edited_time"), OffsetDateTime.class, jsonDeserializationContext));
                        case MULTI_SELECT -> new MultiSelect()
                                .selected(extractSelectItems(jsonObject));
                        case NUMBER -> new Number().number(getAsDoubleIfPresentAndNotNull(jsonObject, "number"));
                        case PEOPLE -> {
                            if (jsonObject.getAsJsonArray("people") == null || jsonObject.getAsJsonArray("people").isJsonNull()) {
                                yield null;
                            }
                            yield new People()
                                    .people(jsonObject.getAsJsonArray("people")
                                            .asList().stream()
                                            .map(person -> new CustomUserDeserializer().deserialize(person, User.class, jsonDeserializationContext))
                                            .toList()
                                    );
                        }
                        case PHONE_NUMBER ->
                                new PhoneNumber().phoneNumber(getAsStringIfPresentAndNotNull(jsonObject, "phone_number"));
                        case RELATION -> {
                            if (jsonObject.getAsJsonObject("relation") == null || jsonObject.getAsJsonObject("relation").isJsonNull()) {
                                yield null;
                            }
                            yield new Relation()
                                    .relatedPageIds(
                                            jsonObject.getAsJsonArray("relation").asList().stream()
                                                    .map(relation -> getAsStringIfPresentAndNotNull(relation.getAsJsonObject(), "id"))
                                                    .toList()
                                    );
                        }
                        case RICH_TEXT -> new RichText().content(
                                new CustomRichTextDeserializer()
                                        .deserialize(jsonObject.getAsJsonObject("results"), de.flix29.notionApiClient.model.RichText.class, jsonDeserializationContext)
                        );
                        case ROLLUP -> new Rollup(); //TODO
                        case SELECT -> new Select()
                                .selected(extractSelectItem(jsonObject.getAsJsonObject("select")));
                        case STATUS -> new Status()
                                .selectedOption(extractStatusItem(jsonObject.getAsJsonObject("status")));
                        case TITLE -> new Title().title(
                                new CustomRichTextDeserializer()
                                        .deserialize(jsonObject.getAsJsonArray(propertyType.getType()), de.flix29.notionApiClient.model.RichText.class, jsonDeserializationContext)
                        );
                        case URL -> new Url().url(getAsStringIfPresentAndNotNull(jsonObject, "url"));
                    };
                    if (property == null) {
                        return null;
                    }
                    return property.id(id).name(name);
                }).toList();
    }

    private List<SelectItem> extractSelectItems(JsonObject jsonObject) {
        if (jsonObject == null || jsonObject.isJsonNull() || !jsonObject.has("multi_select") || jsonObject.get("multi_select").isJsonNull()) {
            return null;
        }
        return jsonObject.getAsJsonArray("multi_select")
                .asList().stream()
                .filter(Objects::nonNull)
                .map(this::extractSelectItem)
                .toList();
    }

    private SelectItem extractSelectItem(JsonElement jsonElement) {
        var jsonObject = jsonElement.getAsJsonObject();
        return new SelectItem()
                .id(getAsStringIfPresentAndNotNull(jsonObject, "id"))
                .name(getAsStringIfPresentAndNotNull(jsonObject, "name"))
                .color(Color.fromString(getAsStringIfPresentAndNotNull(jsonObject, "color")));
    }

    private StatusItem extractStatusItem(JsonObject jsonObject) {
        return new StatusItem(false)
                .id(getAsStringIfPresentAndNotNull(jsonObject, "id"))
                .name(getAsStringIfPresentAndNotNull(jsonObject, "name"))
                .color(Color.fromString(getAsStringIfPresentAndNotNull(jsonObject, "color")));
    }
}
