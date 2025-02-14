package de.flix29.notionApiClient.model.database.databaseProperty;

import de.flix29.notionApiClient.model.RichText;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public final class Title extends Property {
    private List<RichText> title;

    @Override
    public PropertyType getType() {
        return PropertyType.TITLE;
    }
}
