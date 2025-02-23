package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.*;
import de.flix29.notionApiClient.model.Color;
import de.flix29.notionApiClient.model.database.databaseProperty.*;
import de.flix29.notionApiClient.model.database.databaseProperty.Number;
import de.flix29.notionApiClient.model.database.databaseProperty.NumberFormat;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static de.flix29.notionApiClient.customDeserializer.CustomDeserializerUtils.getAsStringIfPresentAndNotNull;

public class CustomDatabasePropertiesDeserializer implements JsonDeserializer<List<Property>> {
    @Override
    public List<Property> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }

        var output = new ArrayList<Property>();
        var entries = jsonElement.getAsJsonObject().entrySet();
        if (entries == null || entries.isEmpty()) {
            return output;
        }

        entries.stream()
                .filter(Objects::nonNull)
                .forEach(entry -> {
                    if (entry.getKey() == null || entry.getValue() == null || entry.getValue().isJsonNull()) {
                        return;
                    }
                    var jsonObject = entry.getValue().getAsJsonObject();
                    var propertyType = PropertyType.fromString(getAsStringIfPresentAndNotNull(jsonObject, "type"));
                    var id = getAsStringIfPresentAndNotNull(jsonObject, "id");
                    var name = getAsStringIfPresentAndNotNull(jsonObject, "name");
                    var description = getAsStringIfPresentAndNotNull(jsonObject, "description");

                    var property = switch (propertyType) {
                        case BUTTON -> new Button();
                        case CHECKBOX -> new Checkbox();
                        case CREATED_BY -> new CreatedBy();
                        case CREATED_TIME -> new CreatedTime();
                        case DATE -> new Date();
                        case EMAIL -> new Email();
                        case FILE -> new File();
                        case FORMULA ->
                                new Formula().setExpression(getAsStringIfPresentAndNotNull(jsonObject.getAsJsonObject("formula"), "expression"));
                        case LAST_EDITED_BY -> new LastEditedBy();
                        case LAST_EDITED_TIME -> new LastEditedTime();
                        case MULTI_SELECT ->
                                new MultiSelect().setOptions(extractSelectItems(jsonObject.getAsJsonObject("multi_select")));
                        case NUMBER -> {
                            var format = getAsStringIfPresentAndNotNull(jsonObject.getAsJsonObject("number"), "format");
                            yield new Number().setFormat(NumberFormat.fromString(format));
                        }
                        case PEOPLE -> new People();
                        case PHONE_NUMBER -> new PhoneNumber();
                        case RELATION -> {
                            var relationObject = jsonObject.getAsJsonObject("relation");
                            var relation = new Relation().setDatabaseId(getAsStringIfPresentAndNotNull(relationObject, "database_id"));
                            var relationType = getAsStringIfPresentAndNotNull(relationObject, "type");
                            if (relationType != null && relationType.equals("dual_property")) {
                                var dualProperty = relationObject.get("dual_property").getAsJsonObject();
                                relation
                                        .setSyncedPropertyName(getAsStringIfPresentAndNotNull(dualProperty, "synced_property_name"))
                                        .setSyncedPropertyId(getAsStringIfPresentAndNotNull(dualProperty, "synced_property_id"));
                            }
                            yield relation;
                        }
                        case RICH_TEXT -> new RichText();
                        case ROLLUP -> {
                            var rollup = jsonObject.getAsJsonObject("rollup");
                            yield new Rollup()
                                    .setRelationPropertyName(getAsStringIfPresentAndNotNull(rollup, "relation_property_name"))
                                    .setRollupPropertyName(getAsStringIfPresentAndNotNull(rollup, "rollup_property_name"))
                                    .setRelationPropertyId(getAsStringIfPresentAndNotNull(rollup, "relation_property_id"))
                                    .setRollupPropertyId(getAsStringIfPresentAndNotNull(rollup, "rollup_property_id"))
                                    .setFunction(RollupFunction.fromString(getAsStringIfPresentAndNotNull(rollup, "function")));
                        }
                        case SELECT -> new Select()
                                .setOptions(extractSelectItems(jsonObject.getAsJsonObject("select")));
                        case STATUS -> new Status()
                                .setOptions(extractStatusItems(jsonObject.getAsJsonObject("status"), "options", false))
                                .setGroups(extractStatusItems(jsonObject.getAsJsonObject("status"), "groups", true));
                        case TITLE -> new Title();
                        case URL -> new Url();
                    };
                    property = property.setId(id).setName(name).setDescription(description);
                    output.add(property);
                });

        return output;
    }

    private List<SelectItem> extractSelectItems(JsonObject jsonObject) {
        if (jsonObject == null || jsonObject.isJsonNull()) {
            return Collections.emptyList();
        }
        return jsonObject.get("options")
                .getAsJsonArray()
                .asList().stream()
                .filter(Objects::nonNull)
                .map(option -> {
                    var optionObject = option.getAsJsonObject();
                    return new SelectItem()
                            .setId(getAsStringIfPresentAndNotNull(optionObject, "id"))
                            .setName(getAsStringIfPresentAndNotNull(optionObject, "name"))
                            .setDescription(getAsStringIfPresentAndNotNull(optionObject, "description"))
                            .setColor(Color.fromString(getAsStringIfPresentAndNotNull(optionObject, "color")));
                }).toList();
    }

    private List<StatusItem> extractStatusItems(JsonObject jsonObject, String type, boolean isGroup) {
        if (jsonObject == null || jsonObject.isJsonNull() || jsonObject.get(type) == null || jsonObject.get(type).isJsonNull()) {
            return Collections.emptyList();
        }

        return jsonObject.get(type)
                .getAsJsonArray()
                .asList().stream()
                .filter(Objects::nonNull)
                .map(option -> {
                    var optionObject = option.getAsJsonObject();
                    return new StatusItem(isGroup)
                            .setId(getAsStringIfPresentAndNotNull(optionObject, "id"))
                            .setName(getAsStringIfPresentAndNotNull(optionObject, "name"))
                            .setDescription(getAsStringIfPresentAndNotNull(optionObject, "description"))
                            .setColor(Color.fromString(getAsStringIfPresentAndNotNull(optionObject, "color")))
                            .setOptionIds(isGroup ? getOptionIds(optionObject) : Collections.emptyList());
                }).toList();
    }

    private static List<String> getOptionIds(JsonObject optionObject) {
        if (optionObject == null || optionObject.isJsonNull() || optionObject.get("option_ids") == null || optionObject.get("option_ids").isJsonNull()) {
            return Collections.emptyList();
        }
        return optionObject
                .get("option_ids")
                .getAsJsonArray()
                .asList()
                .stream()
                .filter(Objects::nonNull)
                .map(JsonElement::getAsString)
                .toList();
    }
}
