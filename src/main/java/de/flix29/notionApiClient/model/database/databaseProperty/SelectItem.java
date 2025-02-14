package de.flix29.notionApiClient.model.database.databaseProperty;

import de.flix29.notionApiClient.model.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SelectItem {
    private String id;
    private String name;
    private Color color;
}
