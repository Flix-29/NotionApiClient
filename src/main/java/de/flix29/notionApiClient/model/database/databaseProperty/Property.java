package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract sealed class Property permits Button, Checkbox, CreatedBy, CreatedTime, Date, Email, File, Formula, LastEditedBy,
        LastEditedTime, MultiSelect, Number, People, PhoneNumber, Relation, RichText, Rollup, Select, Status, Title, Url {
    private String id;
    private String name;
    private String description;

    public Property id(String id) {
        this.id = id;
        return this;
    }

    public Property name(String name) {
        this.name = name;
        return this;
    }

    public Property description(String description) {
        this.description = description;
        return this;
    }

    abstract public PropertyType getType();
}
