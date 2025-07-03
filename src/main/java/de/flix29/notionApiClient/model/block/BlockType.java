package de.flix29.notionApiClient.model.block;

import de.flix29.notionApiClient.model.block.blockContent.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum BlockType {

    BOOKMARK("bookmark", Bookmark.class),
    BREADCRUMB("breadcrumb", Breadcrumb.class),
    BULLETED_LIST_ITEM("bulleted_list_item", BulletListItem.class),
    CALLOUT("callout", Callout.class),
    CHILD_DATABASE("child_database", ChildDatabase.class),
    CHILD_PAGE("child_page", ChildPage.class),
    COLUMN("column", Column.class),
    COLUMN_LIST("column_list", ColumnList.class),
    DIVIDER("divider", Divider.class),
    EMBED("embed", Embed.class),
    EQUATION("equation", Equation.class),
    FILE("file", File.class),
    HEADING_1("heading_1", Heading.class),
    HEADING_2("heading_2", Heading.class),
    HEADING_3("heading_3", Heading.class),
    IMAGE("image", Image.class),
    LINK_PREVIEW("link_preview", LinkPreview.class),
    LINK_TO_PAGE("link_to_page", LinkPreview.class),
    NUMBERED_LIST_ITEM("numbered_list_item", NumberedListItem.class),
    PARAGRAPH("paragraph", Paragraph.class),
    PDF("pdf", Pdf.class),
    QUOTE("quote", Quote.class),
    SYNCED_BLOCK("synced_block", SyncedBlock.class),
    TABLE("table", Table.class),
    TABLE_OF_CONTENTS("table_of_contents", TableOfContents.class),
    TABLE_ROW("table_row", TableRow.class),
    TEMPLATE("template", Template.class),
    TO_DO("to_do", ToDo.class),
    TOGGLE("toggle", Toggle.class),
    UNSUPPORTED("unsupported", null),
    VIDEO("video", Video.class);

    private final String type;
    private final Class<? extends BlockContent> blockContentClass;

    public static BlockType fromString(String type) {
        return Arrays.stream(BlockType.values())
                .filter(blockType -> blockType.type.equals(type))
                .findFirst()
                .orElse(UNSUPPORTED);
    }

    public static BlockType fromBlockContentClass(Class<? extends BlockContent> contentClass) {
        return Arrays.stream(BlockType.values())
                .filter(blockType -> blockType.blockContentClass.equals(contentClass))
                .findFirst()
                .orElse(UNSUPPORTED);
    }
}
