package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public final class Formula extends Property {
    private String expression;
    private FormulaType type;
    private String stringValue;
    private Double numberValue;
    private Boolean booleanValue;
    private OffsetDateTime dateValue;

    @Override
    public PropertyType getType() {
        return PropertyType.FORMULA;
    }

}
