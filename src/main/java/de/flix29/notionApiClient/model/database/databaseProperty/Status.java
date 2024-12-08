package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class Status extends Property {
    private List<StatusItem> options;
    private List<StatusItem> groups;

    public Status options(List<StatusItem> options) {
        this.options = options;
        return this;
    }

    public Status groups(List<StatusItem> groups) {
        this.groups = groups;
        return this;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.STATUS;
    }
}
