package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class Title extends Property {

    @Override
    public PropertyType getType() {
        return PropertyType.TITLE;
    }
}
