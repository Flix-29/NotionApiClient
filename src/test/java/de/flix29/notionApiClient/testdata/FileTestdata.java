package de.flix29.notionApiClient.testdata;

import de.flix29.notionApiClient.model.File;
import de.flix29.notionApiClient.model.FileType;

import java.time.OffsetDateTime;

public class FileTestdata {

    public static File fileEmpty() {
        return new File()
                .setType(FileType.FILE);
    }

    public static File fileExternalAllSet() {
        return new File()
                .setType(FileType.EXTERNAL)
                .setUrl("https://www.notion.so/images/page-cover/woodcuts_2.jpg");
    }

    public static File fileFileAllSet() {
        return new File()
                .setType(FileType.FILE)
                .setUrl("https://www.notion.so/images/page-cover/woodcuts_2.jpg")
                .setExpirationTime(OffsetDateTime.parse("2025-01-01T12:00:00+00:00"));
    }
}
