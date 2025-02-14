package de.flix29.notionApiClient.model.database.databaseProperty;

import de.flix29.notionApiClient.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public final class People extends Property {
    private List<User> people;

    @Override
    public PropertyType getType() {
        return PropertyType.PEOPLE;
    }
}
