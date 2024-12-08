package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class Select extends Property {
    private List<SelectItem> options;
    private SelectItem selected;

    public Select options(List<SelectItem> options) {
        this.options = options;
        return this;
    }

    public Select selected(SelectItem selected) {
        this.selected = selected;
        return this;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.SELECT;
    }
}
