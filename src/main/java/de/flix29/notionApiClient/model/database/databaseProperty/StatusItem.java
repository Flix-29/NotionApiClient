package de.flix29.notionApiClient.model.database.databaseProperty;

import de.flix29.notionApiClient.model.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class StatusItem {
    private String id;
    private String name;
    private Color color;
    private List<String> optionIds;
    private final boolean isGroup;
}
