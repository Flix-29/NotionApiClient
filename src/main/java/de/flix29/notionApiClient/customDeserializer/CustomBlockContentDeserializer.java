package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import de.flix29.notionApiClient.model.FileType;
import de.flix29.notionApiClient.model.Icon;
import de.flix29.notionApiClient.model.block.BlockType;
import de.flix29.notionApiClient.model.block.blockContent.*;

import java.lang.reflect.Type;

import static de.flix29.notionApiClient.customDeserializer.CustomDeserializerUtils.*;

public class CustomBlockContentDeserializer implements JsonDeserializer<BlockContent> {

    private BlockType blockType = BlockType.UNSUPPORTED;

    public BlockContent deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext, BlockType blockType) {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }

        this.blockType = blockType;
        return deserialize(jsonElement, type, jsonDeserializationContext);
    }

    @Override
    public BlockContent deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return switch (blockType) {
            case BOOKMARK -> new Bookmark()
                    .caption(getRichTextFromJsonElement(jsonElement, "caption", jsonDeserializationContext))
                    .url(getStringFromJsonElement(jsonElement, "url"));
            case BREADCRUMB -> new Breadcrumb();
            case BULLETED_LIST_ITEM -> new BulletListItem()
                    .content(getRichTextFromJsonElement(jsonElement, "rich_text", jsonDeserializationContext))
                    .color(getColorFromJsonElement(jsonElement));
            case CALLOUT -> new Callout()
                    .content(getRichTextFromJsonElement(jsonElement, "rich_text", jsonDeserializationContext))
                    .icon(new CustomIconDeserializer().deserialize(jsonElement.getAsJsonObject().get("icon"), Icon.class, jsonDeserializationContext))
                    .color(getColorFromJsonElement(jsonElement));
            case CHILD_DATABASE -> new ChildDatabase()
                    .title(getStringFromJsonElement(jsonElement, "title"));
            case CHILD_PAGE -> new ChildPage()
                    .title(getStringFromJsonElement(jsonElement, "title"));
            case COLUMN -> new Column(); //only accessible using child endpoint
            case COLUMN_LIST -> new ColumnList();
            case DIVIDER -> new Divider();
            case EMBED -> new Embed()
                    .caption(getRichTextFromJsonElement(jsonElement, "caption", jsonDeserializationContext))
                    .url(getStringFromJsonElement(jsonElement, "url"));
            case EQUATION -> new Equation()
                    .expression(getStringFromJsonElement(jsonElement, "expression"));
            case FILE -> new File()
                    .caption(getRichTextFromJsonElement(jsonElement, "caption", jsonDeserializationContext))
                    .file(getFileFromJsonElement(jsonElement, jsonDeserializationContext))
                    .name(getStringFromJsonElement(jsonElement, "name"));
            case HEADING_1 -> createHeading(jsonElement, 1, jsonDeserializationContext);
            case HEADING_2 -> createHeading(jsonElement, 2, jsonDeserializationContext);
            case HEADING_3 -> createHeading(jsonElement, 3, jsonDeserializationContext);
            case IMAGE -> new Image()
                    .caption(getRichTextFromJsonElement(jsonElement, "caption", jsonDeserializationContext))
                    .file(getFileFromJsonElement(jsonElement, jsonDeserializationContext));
            case LINK_PREVIEW -> new LinkPreview()
                    .url(getStringFromJsonElement(jsonElement, "url"));
            case LINK_TO_PAGE, TEMPLATE -> null; //not accessible using api
            case NUMBERED_LIST_ITEM -> new NumberedListItem()
                    .content(getRichTextFromJsonElement(jsonElement, "rich_text", jsonDeserializationContext))
                    .color(getColorFromJsonElement(jsonElement));
            case PARAGRAPH -> new Paragraph()
                    .content(getRichTextFromJsonElement(jsonElement, "rich_text", jsonDeserializationContext))
                    .color(getColorFromJsonElement(jsonElement));
            case PDF -> new Pdf()
                    .caption(getRichTextFromJsonElement(jsonElement, "caption", jsonDeserializationContext))
                    .type(FileType.fromString(getAsStringIfPresentAndNotNull(jsonElement.getAsJsonObject(), "type")))
                    .file(getFileFromJsonElement(jsonElement, jsonDeserializationContext));
            case QUOTE -> new Quote()
                    .content(getRichTextFromJsonElement(jsonElement, "rich_text", jsonDeserializationContext))
                    .color(getColorFromJsonElement(jsonElement));
            case SYNCED_BLOCK -> buildSyncedBlock(jsonElement);
            case TABLE -> new Table()
                    .tableWidth(getAsIntegerIfPresentAndNotNull(jsonElement.getAsJsonObject(), "table_width"))
                    .hasColumnHeader(getAsBooleanIfPresentAndNotNull(jsonElement.getAsJsonObject(), "has_column_header"))
                    .hasRowHeader(getAsBooleanIfPresentAndNotNull(jsonElement.getAsJsonObject(), "has_row_header"));
            case TABLE_OF_CONTENTS -> new TableOfContents()
                    .color(getColorFromJsonElement(jsonElement));
            case TABLE_ROW -> new TableRow(); //only accessible using child endpoint
            case TO_DO -> new ToDo()
                    .content(getRichTextFromJsonElement(jsonElement, "rich_text", jsonDeserializationContext))
                    .checked(getAsBooleanIfPresentAndNotNull(jsonElement.getAsJsonObject(), "checked"))
                    .color(getColorFromJsonElement(jsonElement));
            case TOGGLE -> new Toggle()
                    .content(getRichTextFromJsonElement(jsonElement, "rich_text", jsonDeserializationContext))
                    .color(getColorFromJsonElement(jsonElement));
            case UNSUPPORTED -> throw new UnsupportedOperationException("Unsupported block type");
            case VIDEO -> new Video()
                    .caption(getRichTextFromJsonElement(jsonElement, "caption", jsonDeserializationContext))
                    .type(FileType.fromString(getAsStringIfPresentAndNotNull(jsonElement.getAsJsonObject(), "type")))
                    .file(getFileFromJsonElement(jsonElement, jsonDeserializationContext));
        };
    }

    private Heading createHeading(JsonElement jsonElement, int level, JsonDeserializationContext jsonDeserializationContext) {
        return new Heading(level)
                .content(getRichTextFromJsonElement(jsonElement, "rich_text", jsonDeserializationContext))
                .color(getColorFromJsonElement(jsonElement))
                .isToggleable(getAsBooleanIfPresentAndNotNull(jsonElement.getAsJsonObject(), "is_toggleable"));
    }

    private SyncedBlock buildSyncedBlock(JsonElement jsonElement) {
        var syncedBlock = new SyncedBlock();
        var jsonObject = jsonElement.getAsJsonObject();
        if (jsonObject.get("synced_from") != null && !jsonObject.get("synced_from").isJsonNull()) {
            return syncedBlock.syncedFrom(jsonObject.get("synced_from").getAsJsonObject().get("block_id").getAsString());
        }

        return syncedBlock;
    }
}
