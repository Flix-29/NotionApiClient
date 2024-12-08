package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class Formula extends Property {
    private String expression;

    public Formula expression(String expression) {
        this.expression = expression;
        return this;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.FORMULA;
    }

}
