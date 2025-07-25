package de.flix29.notionApiClient.model.block;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BlockType {

    AUDIO("audio"),
    BOOKMARK("bookmark"),
    BREADCRUMB("breadcrumb"),
    BULLETED_LIST_ITEM("bulleted_list_item"),
    CALLOUT("callout"),
    CHILD_DATABASE("child_database"),
    CHILD_PAGE("child_page"),
    COLUMN("column"),
    COLUMN_LIST("column_list"),
    DIVIDER("divider"),
    EMBED("embed"),
    EQUATION("equation"),
    FILE("file"),
    HEADING_1("heading_1"),
    HEADING_2("heading_2"),
    HEADING_3("heading_3"),
    IMAGE("image"),
    LINK_PREVIEW("link_preview"),
    LINK_TO_PAGE("link_to_page"),
    NUMBERED_LIST_ITEM("numbered_list_item"),
    PARAGRAPH("paragraph"),
    PDF("pdf"),
    QUOTE("quote"),
    SYNCED_BLOCK("synced_block"),
    TABLE("table"),
    TABLE_OF_CONTENTS("table_of_contents"),
    TABLE_ROW("table_row"),
    TEMPLATE("template"),
    TO_DO("to_do"),
    TOGGLE("toggle"),
    UNSUPPORTED("unsupported"),
    VIDEO("video");

    private final String type;

    public static BlockType fromString(String type) {
        for (BlockType blockType : BlockType.values()) {
            if (blockType.type.equals(type)) {
                return blockType;
            }
        }
        return UNSUPPORTED;
    }
}
