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
public final class Status extends Property {
    private List<StatusItem> options;
    private List<StatusItem> groups;
    private StatusItem selectedOption;

    @Override
    public PropertyType getType() {
        return PropertyType.STATUS;
    }
}
