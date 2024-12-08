package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class Rollup extends Property {
    private String relationPropertyId;
    private String relationPropertyName;
    private String rollupPropertyId;
    private String rollupPropertyName;
    private RollupFunction function;

    public Rollup relationPropertyId(String relationPropertyId) {
        this.relationPropertyId = relationPropertyId;
        return this;
    }

    public Rollup relationPropertyName(String relationPropertyName) {
        this.relationPropertyName = relationPropertyName;
        return this;
    }

    public Rollup rollupPropertyId(String rollupPropertyId) {
        this.rollupPropertyId = rollupPropertyId;
        return this;
    }

    public Rollup rollupPropertyName(String rollupPropertyName) {
        this.rollupPropertyName = rollupPropertyName;
        return this;
    }

    public Rollup function(RollupFunction function) {
        this.function = function;
        return this;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.ROLLUP;
    }
}
