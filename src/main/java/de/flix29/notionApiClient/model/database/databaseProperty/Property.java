package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public abstract sealed class Property permits Button, Checkbox, CreatedBy, CreatedTime, Date, Email, File, Formula, LastEditedBy,
        LastEditedTime, MultiSelect, Number, People, PhoneNumber, Relation, RichText, Rollup, Select, Status, Title, Url {
    private String id;
    private String name;
    private String description;

    abstract public PropertyType getType();
}
