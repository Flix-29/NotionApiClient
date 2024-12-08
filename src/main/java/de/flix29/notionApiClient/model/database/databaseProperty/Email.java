package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class Email extends Property {
    private String email;

    public Email email(String email) {
        this.email = email;
        return this;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.EMAIL;
    }
}
