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
public final class File extends Property {
    private List<de.flix29.notionApiClient.model.File> files;

    public File files(List<de.flix29.notionApiClient.model.File> files) {
        this.files = files;
        return this;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.FILE;
    }
}
