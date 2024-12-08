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
public final class LastEditedTime extends Property {
    private OffsetDateTime lastEditedTime;

    public LastEditedTime lastEditedTime(OffsetDateTime lastEditedTime) {
        this.lastEditedTime = lastEditedTime;
        return this;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.LAST_EDITED_TIME;
    }
}
