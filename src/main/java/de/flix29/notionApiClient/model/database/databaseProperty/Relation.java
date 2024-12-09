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
public final class Relation extends Property {
    private String databaseId;
    private String syncedPropertyId;
    private String syncedPropertyName;
    private List<String> relatedPageIds;

    public Relation databaseId(String databaseId) {
        this.databaseId = databaseId;
        return this;
    }

    public Relation syncedPropertyId(String relationPropertyId) {
        this.syncedPropertyId = relationPropertyId;
        return this;
    }

    public Relation syncedPropertyName(String relationPropertyName) {
        this.syncedPropertyName = relationPropertyName;
        return this;
    }

    public Relation relatedPageIds(List<String> relatedPageIds) {
        this.relatedPageIds = relatedPageIds;
        return this;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.RELATION;
    }
}
