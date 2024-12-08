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
public final class CreatedTime extends Property {
    private OffsetDateTime createdTime;

    public CreatedTime createdTime(OffsetDateTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.CREATED_TIME;
    }
}
