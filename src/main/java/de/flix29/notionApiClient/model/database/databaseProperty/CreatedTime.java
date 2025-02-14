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
public final class CreatedTime extends Property {
    private OffsetDateTime createdTime;

    @Override
    public PropertyType getType() {
        return PropertyType.CREATED_TIME;
    }
}
