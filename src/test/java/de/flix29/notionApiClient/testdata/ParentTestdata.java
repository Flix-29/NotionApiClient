package de.flix29.notionApiClient.testdata;

import de.flix29.notionApiClient.model.Parent;
import de.flix29.notionApiClient.model.ParentType;

import java.util.UUID;

public class ParentTestdata {

    public static Parent parentEmpty() {
        return new Parent()
                .setType(ParentType.PAGE);
    }

    public static Parent parentPageAllSet() {
        return new Parent()
                .setType(ParentType.PAGE)
                .setId(UUID.randomUUID());
    }

    public static Parent parentWorkspaceAllSet() {
        return new Parent()
                .setType(ParentType.WORKSPACE)
                .setWorkspace(true);
    }
}
