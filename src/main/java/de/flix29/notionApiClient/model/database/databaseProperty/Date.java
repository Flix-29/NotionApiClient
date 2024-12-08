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
public final class Date extends Property {
    private OffsetDateTime start;
    private OffsetDateTime end;
    private String timezone;

    public Date start(OffsetDateTime start) {
        this.start = start;
        return this;
    }

    public Date end(OffsetDateTime end) {
        this.end = end;
        return this;
    }

    public Date timezone(String timezone) {
        this.timezone = timezone;
        return this;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.DATE;
    }
}
