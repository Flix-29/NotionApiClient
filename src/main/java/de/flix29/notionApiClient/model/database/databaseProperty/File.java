package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public final class File extends Property {
    private List<de.flix29.notionApiClient.model.File> files;

    @Override
    public PropertyType getType() {
        return PropertyType.FILE;
    }
}
