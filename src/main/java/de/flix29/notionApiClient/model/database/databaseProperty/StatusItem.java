package de.flix29.notionApiClient.model.database.databaseProperty;

import de.flix29.notionApiClient.model.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class StatusItem {
    private String id;
    private String name;
    private Color color;
    private List<String> optionIds;
    private final boolean isGroup;

    public StatusItem id(String id) {
        this.id = id;
        return this;
    }

    public StatusItem name(String name) {
        this.name = name;
        return this;
    }

    public StatusItem color(Color color) {
        this.color = color;
        return this;
    }

    public StatusItem optionIds(List<String> optionIds) {
        this.optionIds = optionIds;
        return this;
    }
}
