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

public class CustomDatabasePropertiesDeserializer implements JsonDeserializer<List<Property>> {
    @Override
    public List<Property> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        //TODO: check description
        var output = new ArrayList<Property>();
        var entries = jsonElement.getAsJsonObject().entrySet();
        entries.forEach(entry -> {
            var jsonObject = entry.getValue().getAsJsonObject();
            var propertyType = PropertyType.fromString(jsonObject.get("type").getAsString());
            var id = jsonObject.get("id").getAsString();
            var name = jsonObject.get("name").getAsString();

            var property = switch (propertyType) {
                case BUTTON -> new Button();
                case CHECKBOX -> new Checkbox();
                case CREATED_BY -> new CreatedBy();
                case CREATED_TIME -> new CreatedTime();
                case DATE -> new Date();
                case EMAIL -> new Email();
                case FILE -> new File();
                case FORMULA ->
                        new Formula().expression(jsonObject.get("formula").getAsJsonObject().get("expression").getAsString());
                case LAST_EDITED_BY -> new LastEditedBy();
                case LAST_EDITED_TIME -> new LastEditedTime();
                case MULTI_SELECT ->
                        new MultiSelect().options(extractSelectItems(jsonObject.get("multi_select").getAsJsonObject()));
                case NUMBER -> {
                    var format = jsonObject.get("number").getAsJsonObject().get("format").getAsString();
                    yield new Number().format(NumberFormat.valueOf(format));
                }
                case PEOPLE -> new People();
                case PHONE_NUMBER -> new PhoneNumber();
                case RELATION -> {
                    var relationObject = jsonObject.get("relation").getAsJsonObject();
                    var relation = new Relation()
                            .databaseId(relationObject.get("database_id").getAsString());
                    if (relationObject.get("type").getAsString().equals("dual_property")) {
                        var dualProperty = relationObject.get("dual_property").getAsJsonObject();
                        relation
                                .syncedPropertyName(dualProperty.get("synced_property_name").getAsString())
                                .syncedPropertyId(dualProperty.get("synced_property_id").getAsString());
                    }
                    yield relation;
                }
                case RICH_TEXT -> new RichText();
                case ROLLUP -> {
                    var rollup = jsonObject.get("rollup").getAsJsonObject();
                    yield new Rollup()
                            .relationPropertyName(rollup.get("relation_property_name").getAsString())
                            .rollupPropertyName(rollup.get("rollup_property_name").getAsString())
                            .relationPropertyId(rollup.get("relation_property_id").getAsString())
                            .rollupPropertyId(rollup.get("rollup_property_id").getAsString())
                            .function(RollupFunction.valueOf(rollup.get("function").getAsString()));
                }
                case SELECT -> new Select()
                        .options(extractSelectItems(jsonObject.get("select").getAsJsonObject()));
                case STATUS -> new Status()
                        .options(extractStatusItems(jsonObject.get("status").getAsJsonObject(), "options", false))
                        .groups(extractStatusItems(jsonObject.get("status").getAsJsonObject(), "groups", true));
                case TITLE -> new Title();
                case URL -> new Url();
            };
            property = property.id(id).name(name).description(entry.getKey());
            output.add(property);
        });

        return output;
    }

    private List<SelectItem> extractSelectItems(JsonObject jsonObject) {
        return jsonObject.get("options")
                .getAsJsonArray()
                .asList().stream()
                .map(option -> {
                    var optionObject = option.getAsJsonObject();
                    return new SelectItem()
                            .id(optionObject.get("id").getAsString())
                            .name(optionObject.get("name").getAsString())
                            .color(Color.fromString(optionObject.get("color").getAsString()));
                }).toList();
    }

    private List<StatusItem> extractStatusItems(JsonObject jsonObject, String type, boolean isGroup) {
        return jsonObject.get(type)
                .getAsJsonArray()
                .asList().stream()
                .map(option -> {
                    var optionObject = option.getAsJsonObject();
                    return new StatusItem(isGroup)
                            .id(optionObject.get("id").getAsString())
                            .name(optionObject.get("name").getAsString())
                            .color(Color.fromString(optionObject.get("color").getAsString()))
                            .optionIds(isGroup ? getOptionIds(optionObject) : Collections.emptyList());
                }).toList();
    }

    private static List<String> getOptionIds(JsonObject optionObject) {
        return optionObject
                .get("option_ids")
                .getAsJsonArray()
                .asList()
                .stream()
                .map(JsonElement::getAsString)
                .toList();
    }
}
