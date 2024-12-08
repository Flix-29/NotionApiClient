package de.flix29.notionApiClient.model.database.databaseProperty;

import de.flix29.notionApiClient.model.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectItem {
    private String id;
    private String name;
    private Color color;

    public SelectItem id(String id) {
        this.id = id;
        return this;
    }

    public SelectItem name(String name) {
        this.name = name;
        return this;
    }

    public SelectItem color(Color color) {
        this.color = color;
        return this;
    }
}
