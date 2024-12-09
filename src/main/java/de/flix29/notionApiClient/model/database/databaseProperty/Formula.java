package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class Formula extends Property {
    private String expression;
    private String stringValue;
    private Double numberValue;
    private Boolean booleanValue;
    private OffsetDateTime dateValue;

    public Formula expression(String expression) {
        this.expression = expression;
        return this;
    }

    public Formula stringValue(String stringValue) {
        this.stringValue = stringValue;
        return this;
    }

    public Formula numberValue(Double numberValue) {
        this.numberValue = numberValue;
        return this;
    }

    public Formula booleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
        return this;
    }

    public Formula dateValue(OffsetDateTime dateValue) {
        this.dateValue = dateValue;
        return this;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.FORMULA;
    }

}
