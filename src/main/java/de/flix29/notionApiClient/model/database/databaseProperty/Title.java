package de.flix29.notionApiClient.model.database.databaseProperty;

import de.flix29.notionApiClient.model.RichText;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class Title extends Property {
    private List<RichText> title;

    public Title title(List<RichText> title) {
        this.title = title;
        return this;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.TITLE;
    }
}
