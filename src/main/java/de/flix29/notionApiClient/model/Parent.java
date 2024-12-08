package de.flix29.notionApiClient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parent {
    private ParentType type;
    private UUID id;
    private boolean workspace;

    public Parent type(ParentType type) {
        this.type = type;
        return this;
    }

    public Parent id(UUID id) {
        this.id = id;
        return this;
    }

    public Parent workspace(boolean workspace) {
        this.workspace = workspace;
        return this;
    }
}
