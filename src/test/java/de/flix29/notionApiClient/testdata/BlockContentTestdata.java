package de.flix29.notionApiClient.testdata;

import de.flix29.notionApiClient.model.*;
import de.flix29.notionApiClient.model.block.blockContent.File;
import de.flix29.notionApiClient.model.block.blockContent.*;
import de.flix29.notionApiClient.model.block.blockContent.Equation;

import java.time.OffsetDateTime;
import java.util.List;

public class BlockContentTestdata {

    public static Video videoAllSet() {
        return new Video()
                .setCaption(richTextTextAllSet())
                .setType(FileType.EXTERNAL)
                .setFile(new de.flix29.notionApiClient.model.File()
                        .setType(FileType.EXTERNAL)
                        .setUrl("https://link-to-video.com"));
    }

    public static Video videoEmpty() {
        return new Video()
                .setCaption(richTextTextEmpty())
                .setType(FileType.EXTERNAL)
                .setFile(new de.flix29.notionApiClient.model.File()
                        .setType(FileType.EXTERNAL)
                        .setUrl(null));
    }

    public static Toggle toggleAllSet() {
        return new Toggle()
                .setContent(richTextTextAllSet())
                .setColor(Color.RED);
    }

    public static Toggle toggleEmpty() {
        return new Toggle()
                .setContent(richTextTextEmpty())
                .setColor(Color.DEFAULT);
    }

    public static ToDo toDoAllSet() {
        return new ToDo()
                .setContent(richTextTextAllSet())
                .setColor(Color.RED)
                .setChecked(true);
    }

    public static ToDo toDoEmpty() {
        return new ToDo()
                .setContent(richTextTextEmpty())
                .setColor(Color.DEFAULT)
                .setChecked(false);
    }

    public static TableOfContents tableOfContentsAllSet() {
        return new TableOfContents()
                .setColor(Color.GRAY);
    }

    public static TableOfContents tableOfContentsEmpty() {
        return new TableOfContents()
                .setColor(Color.DEFAULT);
    }

    public static Table tableAllSet() {
        return new Table()
                .setTableWidth(2)
                .setHasRowHeader(true)
                .setHasColumnHeader(true);
    }

    public static Table tableEmpty() {
        return new Table()
                .setTableWidth(0)
                .setHasRowHeader(false)
                .setHasColumnHeader(false);
    }

    public static SyncedBlock syncedBlockAllSet() {
        return new SyncedBlock()
                .setSyncedFrom("1a159500-2da8-809c-b85b-fb61b65c0817");
    }

    public static SyncedBlock syncedBlockEmpty() {
        return new SyncedBlock()
                .setSyncedFrom(null);
    }

    public static Quote quoteAllSet() {
        return new Quote()
                .setContent(richTextTextAllSet())
                .setColor(Color.RED);
    }

    public static Quote quoteEmpty() {
        return new Quote()
                .setContent(richTextTextEmpty())
                .setColor(Color.DEFAULT);
    }

    public static Pdf pdfAllSet() {
        return new Pdf()
                .setCaption(richTextTextAllSet())
                .setType(FileType.FILE)
                .setFile(modelFileAllSet());
    }

    public static Pdf pdfEmpty() {
        return new Pdf()
                .setCaption(richTextTextEmpty())
                .setType(FileType.FILE)
                .setFile(modelFileEmpty());
    }

    public static Paragraph paragraphAllSet() {
        return new Paragraph()
                .setContent(richTextTextAllSet())
                .setColor(Color.RED);
    }

    public static Paragraph paragraphEmpty() {
        return new Paragraph()
                .setContent(richTextTextEmpty())
                .setColor(Color.DEFAULT);
    }

    public static NumberedListItem numberedListItemAllSet() {
        return new NumberedListItem()
                .setContent(richTextTextAllSet())
                .setColor(Color.RED);
    }

    public static NumberedListItem numberedListItemEmpty() {
        return new NumberedListItem()
                .setContent(richTextTextEmpty())
                .setColor(Color.DEFAULT);
    }

    public static LinkPreview linkPreviewAllSet() {
        return new LinkPreview()
                .setUrl("https://github.com/Flix-29/NotionApiClient");
    }

    public static LinkPreview linkPreviewEmpty() {
        return new LinkPreview()
                .setUrl(null);
    }

    public static Image imageAllSet() {
        return new Image()
                .setCaption(richTextTextAllSet())
                .setFile(modelFileAllSet());
    }

    public static Image imageEmpty() {
        return new Image()
                .setCaption(richTextTextEmpty())
                .setFile(modelFileEmpty());
    }

    public static Heading headingAllSet(int level) {
        return new Heading(level)
                .setContent(richTextTextAllSet())
                .setColor(Color.RED)
                .setToggleable(true);
    }

    public static Heading headingEmpty(int level) {
        return new Heading(level)
                .setContent(richTextTextEmpty())
                .setColor(Color.DEFAULT);
    }

    public static File fileAllSet() {
        return new File()
                .setCaption(richTextTextAllSet())
                .setFile(modelFileAllSet())
                .setName("Notion");
    }

    public static File fileEmpty() {
        return new File()
                .setCaption(richTextTextEmpty())
                .setFile(modelFileEmpty());
    }

    public static Equation equationAllSet() {
        return new Equation()
                .setExpression("e = mc^2");
    }

    public static Equation equationEmpty() {
        return new Equation()
                .setExpression(null);
    }

    public static Embed embedAllSet() {
        return new Embed()
                .setCaption(richTextTextAllSet())
                .setUrl("https://www.notion.so");
    }

    public static Embed embedEmpty() {
        return new Embed()
                .setCaption(richTextTextEmpty())
                .setUrl(null);
    }

    public static ChildPage childPageAllSet() {
        return new ChildPage()
                .setTitle("Notion");
    }

    public static ChildPage childPageEmpty() {
        return new ChildPage()
                .setTitle(null);
    }

    public static ChildDatabase childDatabaseAllSet() {
        return new ChildDatabase()
                .setTitle("Notion");
    }

    public static ChildDatabase childDatabaseEmpty() {
        return new ChildDatabase()
                .setTitle(null);
    }

    public static Callout calloutAllSet() {
        return new Callout()
                .setContent(richTextTextAllSet())
                .setIcon(iconAllSet())
                .setColor(Color.GRAY_BACKGROUND);
    }

    public static Callout calloutEmpty() {
        return new Callout()
                .setContent(richTextTextEmpty())
                .setIcon(iconEmpty())
                .setColor(Color.DEFAULT);
    }

    public static BulletListItem bulletedListItemAllSet() {
        return new BulletListItem()
                .setContent(richTextTextAllSet())
                .setColor(Color.BLUE);
    }

    public static BulletListItem bulletedListItemEmpty() {
        return new BulletListItem()
                .setContent(richTextTextEmpty())
                .setColor(Color.DEFAULT);
    }

    public static Bookmark bookmarkAllSet() {
        return new Bookmark()
                .setCaption(richTextTextAllSet())
                .setUrl("https://notion.so");
    }

    public static Bookmark bookmarkEmpty() {
        return new Bookmark()
                .setCaption(richTextTextEmpty());
    }

    private static List<RichText> richTextTextAllSet() {
        var text = RichTextTestdata.richTextTextAllSet()
                .setContent("Notion")
                .setLink(null)
                .setPlainText("Notion")
                .setHref(null);

        return List.of(text);
    }

    private static List<RichText> richTextTextEmpty() {
        var richText = new RichText()
                .setAnnotations(new Annotations()
                        .setColor(Color.DEFAULT)
                );
        return List.of(new Text(richText));
    }

    private static Icon iconAllSet() {
        return new de.flix29.notionApiClient.model.File()
                .setType(FileType.EXTERNAL)
                .setUrl("https://www.notion.so/icons/exclamation-mark_red.svg");
    }

    private static Icon iconEmpty() {
        return new de.flix29.notionApiClient.model.File()
                .setType(FileType.EXTERNAL)
                .setUrl(null);
    }

    private static de.flix29.notionApiClient.model.File modelFileAllSet() {
        return new de.flix29.notionApiClient.model.File()
                .setType(FileType.FILE)
                .setUrl("https://link-to-file.com")
                .setExpirationTime(OffsetDateTime.parse("2025-01-01T12:00:00Z"));
    }

    private static de.flix29.notionApiClient.model.File modelFileEmpty() {
        return new de.flix29.notionApiClient.model.File()
                .setType(FileType.FILE)
                .setUrl(null)
                .setExpirationTime(null);
    }
}
