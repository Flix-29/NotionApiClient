package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class RichText extends Property {
    private List<de.flix29.notionApiClient.model.RichText> content;

    public RichText content(List<de.flix29.notionApiClient.model.RichText> content) {
        this.content = content;
        return this;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.RICH_TEXT;
    }
}
