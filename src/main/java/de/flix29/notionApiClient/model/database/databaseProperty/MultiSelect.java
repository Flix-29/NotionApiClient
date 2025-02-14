package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public final class MultiSelect extends Property {
    private List<SelectItem> options;
    private List<SelectItem> selected;

    @Override
    public PropertyType getType() {
        return PropertyType.MULTI_SELECT;
    }
}
