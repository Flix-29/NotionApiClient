package de.flix29.notionApiClient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Parent {
    private ParentType type;
    private UUID id;
    private boolean workspace;
}
