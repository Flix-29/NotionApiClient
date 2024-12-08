package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class Checkbox extends Property {
    private boolean checked;

    public Checkbox checked(boolean checked) {
        this.checked = checked;
        return this;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.CHECKBOX;
    }
}
