package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class Number extends Property {
    private NumberFormat format;
    private double number;

    public Number format(NumberFormat format) {
        this.format = format;
        return this;
    }

    public Number number(double number) {
        this.number = number;
        return this;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.NUMBER;
    }
}
