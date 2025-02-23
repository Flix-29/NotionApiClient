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
                .setId(UUID.fromString("3363ffa1-12f6-4076-a75c-2d8e4da61591"));
    }

    public static Parent parentWorkspaceAllSet() {
        return new Parent()
                .setType(ParentType.WORKSPACE)
                .setWorkspace(true);
    }

    protected static Parent parentDatabaseAllSet() {
        return new Parent()
                .setType(ParentType.DATABASE)
                .setId(UUID.fromString("c6e92391-c829-42ea-9ac7-949106ed3916"));
    }

    protected static Parent parentDatabaseEmpty() {
        return new Parent()
                .setType(ParentType.DATABASE);
    }
}
