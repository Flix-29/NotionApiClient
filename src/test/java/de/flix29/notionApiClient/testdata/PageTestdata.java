package de.flix29.notionApiClient.testdata;

import de.flix29.notionApiClient.model.page.Page;

import java.time.OffsetDateTime;

public class PageTestdata {

    public static Page pageAllSet() {
        return new Page()
                .setId("c6e92391-c829-42ea-9ac7-949106ed3916")
                .setCreatedTime(OffsetDateTime.parse("2025-01-01T12:00:00.000Z"))
                .setLastEditedTime(OffsetDateTime.parse("2025-01-01T12:00:00.000Z"))
                .setCreatedBy(UserTestdata.userJustID())
                .setLastEditedBy(UserTestdata.userJustID())
                .setArchived(true)
                .setDeleted(true)
                .setIcon(IconTestdata.emojiAllSet())
                .setCover(IconTestdata.fileExternalAllSet())
                .setProperties(null)
                .setParent(ParentTestdata.parentDatabaseAllSet())
                .setUrl("https://www.notion.so")
                .setPublicUrl("https://www.notion.so");
    }

    public static Page pageEmpty() {
        return new Page()
                .setCreatedBy(UserTestdata.userEmpty())
                .setLastEditedBy(UserTestdata.userEmpty())
                .setParent(ParentTestdata.parentDatabaseEmpty());
    }
}
