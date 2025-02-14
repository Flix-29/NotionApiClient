package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public final class Button extends Property {

    @Override
    public PropertyType getType() {
        return PropertyType.BUTTON;
    }
}
