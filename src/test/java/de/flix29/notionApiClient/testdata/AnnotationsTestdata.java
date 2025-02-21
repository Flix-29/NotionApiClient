package de.flix29.notionApiClient.testdata;

import de.flix29.notionApiClient.model.Annotations;
import de.flix29.notionApiClient.model.Color;

public class AnnotationsTestdata {

    public static Annotations annotationsEmpty() {
        return new Annotations()
                .setColor(Color.DEFAULT);
    }

    public static Annotations annotationsAllSet() {
        return new Annotations()
                .setBold(true)
                .setItalic(true)
                .setStrikethrough(true)
                .setUnderline(true)
                .setCode(true)
                .setColor(Color.RED);
    }
}
