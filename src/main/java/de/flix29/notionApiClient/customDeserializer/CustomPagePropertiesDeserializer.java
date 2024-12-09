package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.*;
import de.flix29.notionApiClient.model.Color;
import de.flix29.notionApiClient.model.User;
import de.flix29.notionApiClient.model.database.databaseProperty.*;
import de.flix29.notionApiClient.model.database.databaseProperty.Number;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomPagePropertiesDeserializer implements JsonDeserializer<List<Property>> {
    @Override
    public List<Property> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        var output = new ArrayList<Property>();
        var entries = jsonElement.getAsJsonObject().entrySet();
        entries.forEach(entry -> {
            var jsonObject = entry.getValue().getAsJsonObject();
            var propertyType = PropertyType.fromString(jsonObject.get("type").getAsString());
            var id = jsonObject.get("id").getAsString();
            var name = entry.getKey();

            var property = switch (propertyType) {
                case BUTTON -> new Button(); //TODO
                case CHECKBOX -> new Checkbox().checked(jsonObject.get("checkbox").getAsBoolean());
                case CREATED_BY -> new CreatedBy()
                        .user(new CustomUserDeserializer().deserialize(jsonObject.get("created_by"), User.class, jsonDeserializationContext));
                case CREATED_TIME -> new CreatedTime()
                        .createdTime(new CustomOffsetDateTimeDeserializer().deserialize(jsonObject.get("created_time"), OffsetDateTime.class, jsonDeserializationContext));
                case DATE -> new Date()
                        .start(new CustomOffsetDateTimeDeserializer().deserialize(jsonObject.get("date").getAsJsonObject().get("start"), OffsetDateTime.class, jsonDeserializationContext))
                        .end(new CustomOffsetDateTimeDeserializer().deserialize(jsonObject.get("date").getAsJsonObject().get("end"), OffsetDateTime.class, jsonDeserializationContext))
                        .timezone(jsonObject.get("date").getAsJsonObject().get("timezone").getAsString());
                case EMAIL -> new Email().email(jsonObject.get("email").getAsString());
                case FILE -> new File()
                        .files(jsonObject.getAsJsonArray("files")
                                .asList().stream()
                                .map(file -> new CustomFileDeserializer().deserialize(file, File.class, jsonDeserializationContext))
                                .toList()
                        );
                case FORMULA -> {
                    var formulaObject = jsonObject.getAsJsonObject("formula");
                    var valueType = formulaObject.get("type").getAsString();
                    yield switch (valueType) {
                        case "string" -> new Formula().stringValue(formulaObject.get("string").getAsString());
                        case "number" -> new Formula().numberValue(formulaObject.get("number").getAsDouble());
                        case "boolean" -> new Formula().booleanValue(formulaObject.get("boolean").getAsBoolean());
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
                case NUMBER -> new Number().number(jsonObject.get("number").getAsDouble());
                case PEOPLE -> new People()
                        .people(jsonObject.getAsJsonArray("people")
                                .asList().stream()
                                .map(person -> new CustomUserDeserializer().deserialize(person, User.class, jsonDeserializationContext))
                                .toList()
                        );
                case PHONE_NUMBER -> new PhoneNumber().phoneNumber(jsonObject.get("phone_number").getAsString());
                case RELATION -> new Relation()
                        .relatedPageIds(
                                jsonObject.getAsJsonArray("relation").asList().stream()
                                        .map(relation -> relation.getAsJsonObject().get("id").getAsString())
                                        .toList()
                        );
                case RICH_TEXT -> new RichText(); //TODO
                case ROLLUP -> new Rollup(); //TODO
                case SELECT -> new Select()
                        .selected(extractSelectItem(jsonObject.getAsJsonObject("select")));
                case STATUS -> new Status()
                        .selectedOption(extractStatusItem(jsonObject.getAsJsonObject("status")));
                case TITLE -> new Title(); //TODO
                case URL -> new Url().url(jsonObject.get("url").getAsString());
            };
            property = property.id(id).name(name);
            output.add(property);
        });

        return output;
    }

    private List<SelectItem> extractSelectItems(JsonObject jsonObject) {
        return jsonObject.getAsJsonArray("multi_select")
                .asList().stream()
                .map(this::extractSelectItem)
                .toList();
    }

    private SelectItem extractSelectItem(JsonElement jsonElement) {
        var jsonObject = jsonElement.getAsJsonObject();
        return new SelectItem()
                .id(jsonObject.get("id").getAsString())
                .name(jsonObject.get("name").getAsString())
                .color(Color.fromString(jsonObject.get("color").getAsString()));
    }

    private StatusItem extractStatusItem(JsonObject jsonObject) {
        return new StatusItem(false)
                .id(jsonObject.get("id").getAsString())
                .name(jsonObject.get("name").getAsString())
                .color(Color.fromString(jsonObject.get("color").getAsString()));
    }
}
