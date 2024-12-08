package de.flix29.notionApiClient.model.database.databaseProperty;

import de.flix29.notionApiClient.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class CreatedBy extends Property {
    private User user;

    public CreatedBy user(User user) {
        this.user = user;
        return this;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.CREATED_BY;
    }
}
