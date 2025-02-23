package de.flix29.notionApiClient.testdata;

import de.flix29.notionApiClient.model.*;

import java.time.OffsetDateTime;
import java.util.List;

public class RichTextTestdata {

    public static List<Text> richTextsTextAllSet() {
        return List.of(richTextTextAllSet());
    }

    public static List<Equation> richTextEquationsAllSet() {
        return List.of(richTextEquationAllSet());
    }

    public static List<MentionDatabase> richTextMentionDatabasesAllSet() {
        return List.of(richTextMentionDatabaseAllSet());
    }

    public static List<MentionPage> richTextMentionPagesAllSet() {
        return List.of(richTextMentionPageAllSet());
    }

    public static List<MentionUser> richTextMentionUsersAllSet() {
        return List.of(richTextMentionUserAllSet());
    }

    public static List<MentionDate> richTextMentionDatesAllSet() {
        return List.of(richTextMentionDateAllSet());
    }

    public static List<MentionLinkPreview> richTextMentionLinkPreviewsAllSet() {
        return List.of(richTextMentionLinkPreviewAllSet());
    }

    protected static Text richTextTextAllSet() {
        return new Text(richTextAllSet())
                .setContent("test")
                .setLink("https://www.notion.so");
    }

    protected static Text richTextTextEmpty() {
        return new Text(richTextEmpty())
                .setContent(null)
                .setLink(null);
    }

    private static Equation richTextEquationAllSet() {
        var richText = richTextAllSet()
                .setPlainText("e=mc^2")
                .setHref(null);
        return new Equation(richText)
                .setEquation("e=mc^2");
    }

    private static MentionDatabase richTextMentionDatabaseAllSet() {
        var richText = richTextAllSet()
                .setPlainText("TestDB")
                .setHref("https://www.notion.so/fddb0a7a1531437ba4d48fde89028508");
        return new MentionDatabase(richText)
                .setId("fddb0a7a-1531-437b-a4d4-8fde89028508");
    }

    private static MentionPage richTextMentionPageAllSet() {
        var richText = richTextAllSet()
                .setPlainText("TestPage")
                .setHref("https://www.notion.so/fddb0a7a1531437ba4d48fde89028508");
        return new MentionPage(richText)
                .setId("fddb0a7a-1531-437b-a4d4-8fde89028508");
    }

    private static MentionUser richTextMentionUserAllSet() {
        var richText = richTextAllSet()
                .setPlainText("@Name")
                .setHref(null);
        return new MentionUser(richText)
                .setId("fddb0a7a-1531-437b-a4d4-8fde89028508");
    }

    private static MentionDate richTextMentionDateAllSet() {
        var richText = richTextAllSet()
                .setPlainText("2025-01-01T00:00:00.000-05:00 → 2025-01-02T00:00:00.000-05:00")
                .setHref(null);
        return new MentionDate(richText)
                .setStart(OffsetDateTime.parse("2025-01-01T00:00:00.000-05:00"))
                .setEnd(OffsetDateTime.parse("2025-01-02T00:00:00.000-05:00"))
                .setTimeZone(null);
    }

    private static MentionLinkPreview richTextMentionLinkPreviewAllSet() {
        var richText = richTextAllSet()
                .setPlainText("https://www.notion.so")
                .setHref("https://www.notion.so");
        return new MentionLinkPreview(richText)
                .setHref("https://www.notion.so")
                .setTitle("Your connected workspace for wiki, docs & projects | Notion")
                .setIconUrl("https://www.notion.com/front-static/favicon.ico")
                .setDescription("A new tool that blends your everyday work apps into one. It's the all-in-one workspace for you and your team.")
                .setLinkProvider("Notion")
                .setThumbnailUrl("https://www.notion.com/front-static/meta/default.png");
    }

    protected static RichText richTextAllSet() {
        return new RichText()
                .setPlainText("test")
                .setAnnotations(annotationsAllSet())
                .setHref("https://www.notion.so");
    }

    private static RichText richTextEmpty() {
        return new RichText()
                .setPlainText(null)
                .setAnnotations(annotationsEmpty())
                .setHref(null);
    }

    private static Annotations annotationsAllSet() {
        return new Annotations()
                .setBold(true)
                .setItalic(true)
                .setStrikethrough(true)
                .setUnderline(true)
                .setCode(true)
                .setColor(Color.RED);
    }

    private static Annotations annotationsEmpty() {
        return new Annotations()
                .setBold(false)
                .setItalic(false)
                .setStrikethrough(false)
                .setUnderline(false)
                .setCode(false)
                .setColor(Color.DEFAULT);
    }
}
