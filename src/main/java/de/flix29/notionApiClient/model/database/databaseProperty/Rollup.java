package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public final class Rollup extends Property {
    private String relationPropertyId;
    private String relationPropertyName;
    private String rollupPropertyId;
    private String rollupPropertyName;
    private RollupFunction function;

    @Override
    public PropertyType getType() {
        return PropertyType.ROLLUP;
    }
}
