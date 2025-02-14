package de.flix29.notionApiClient.model.database.databaseProperty;

import de.flix29.notionApiClient.model.User;
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
public final class CreatedBy extends Property {
    private User user;

    @Override
    public PropertyType getType() {
        return PropertyType.CREATED_BY;
    }
}
