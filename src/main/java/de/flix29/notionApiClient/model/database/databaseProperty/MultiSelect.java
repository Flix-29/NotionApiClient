package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class MultiSelect extends Property {
    private List<SelectItem> options;
    private List<SelectItem> selected;

    public MultiSelect options(List<SelectItem> options) {
        this.options = options;
        return this;
    }

    public MultiSelect selected(List<SelectItem> selected) {
        this.selected = selected;
        return this;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.MULTI_SELECT;
    }
}
