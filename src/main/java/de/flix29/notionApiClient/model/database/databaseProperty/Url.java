package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class Url extends Property {
    private String url;

    public Url url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.URL;
    }
}
