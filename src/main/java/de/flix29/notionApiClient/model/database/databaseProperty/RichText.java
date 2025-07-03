package de.flix29.notionApiClient.model.database.databaseProperty;

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
public final class RichText extends Property {
    private List<de.flix29.notionApiClient.model.RichText> content;

    @Override
    public PropertyType getType() {
        return PropertyType.RICH_TEXT;
    }
}
