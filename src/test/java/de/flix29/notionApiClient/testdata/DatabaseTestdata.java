package de.flix29.notionApiClient.testdata;

import de.flix29.notionApiClient.model.database.Database;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class DatabaseTestdata {

    public static Database databaseEmpty() {
        return new Database()
                .setId(null)
                .setCreatedTime(null)
                .setCreatedBy(null)
                .setLastEditedTime(null)
                .setLastEditedBy(null)
                .setTitel(List.of(RichTextTestdata.richTextTextEmpty()))
                .setDescription(List.of(RichTextTestdata.richTextTextEmpty()))
                .setInline(false)
                .setIcon(IconTestdata.emojiEmpty())
                .setCover(IconTestdata.fileExternalEmpty())
                .setProperties(Collections.emptyList())
                .setParent(ParentTestdata.parentEmpty())
                .setUrl(null)
                .setArchived(false)
                .setDeleted(false)
                .setPublicUrl(null);
    }

    public static Database databaseAllSet() {
        var id = UUID.fromString("3363ffa1-12f6-4076-a75c-2d8e4da61591");
        var dateTime = OffsetDateTime.parse("2025-01-01T12:00:00.000Z");
        return new Database()
                .setId(id)
                .setCreatedTime(dateTime)
                .setCreatedBy(id)
                .setLastEditedTime(dateTime)
                .setLastEditedBy(id)
                .setTitel(List.of(RichTextTestdata.richTextTextAllSet()))
                .setDescription(List.of(RichTextTestdata.richTextTextAllSet()))
                .setInline(true)
                .setIcon(IconTestdata.emojiAllSet())
                .setCover(IconTestdata.fileExternalAllSet())
                .setProperties(Collections.emptyList())
                .setParent(ParentTestdata.parentPageAllSet())
                .setUrl("https://www.notion.so")
                .setArchived(true)
                .setDeleted(true)
                .setPublicUrl("https://www.notion.so");
    }
}
