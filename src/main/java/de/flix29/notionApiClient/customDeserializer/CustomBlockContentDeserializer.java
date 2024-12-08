package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import de.flix29.notionApiClient.model.Color;
import de.flix29.notionApiClient.model.FileType;
import de.flix29.notionApiClient.model.Icon;
import de.flix29.notionApiClient.model.RichText;
import de.flix29.notionApiClient.model.block.BlockType;
import de.flix29.notionApiClient.model.block.blockContent.*;

import java.lang.reflect.Type;
import java.util.List;

import static de.flix29.notionApiClient.customDeserializer.CustomModelTypes.RICH_TEXT_LIST_TYPE;

public class CustomBlockContentDeserializer implements JsonDeserializer<BlockContent> {

    private BlockType blockType = BlockType.UNSUPPORTED;

    public BlockContent deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext, BlockType blockType) {
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
                    .type(FileType.fromString(jsonElement.getAsJsonObject().get("type").getAsString()))
                    .file(getFileFromJsonElement(jsonElement, jsonDeserializationContext));
            case QUOTE -> new Quote()
                    .content(getRichTextFromJsonElement(jsonElement, "rich_text", jsonDeserializationContext))
                    .color(getColorFromJsonElement(jsonElement));
            case SYNCED_BLOCK -> buildSyncedBlock(jsonElement);
            case TABLE -> new Table()
                    .tableWidth(jsonElement.getAsJsonObject().get("table_width").getAsInt())
                    .hasColumnHeader(jsonElement.getAsJsonObject().get("has_column_header").getAsBoolean())
                    .hasRowHeader(jsonElement.getAsJsonObject().get("has_row_header").getAsBoolean());
            case TABLE_OF_CONTENTS -> new TableOfContents()
                    .color(getColorFromJsonElement(jsonElement));
            case TABLE_ROW -> new TableRow(); //only accessible using child endpoint
            case TO_DO -> new ToDo()
                    .content(getRichTextFromJsonElement(jsonElement, "rich_text", jsonDeserializationContext))
                    .checked(jsonElement.getAsJsonObject().get("checked").getAsBoolean())
                    .color(getColorFromJsonElement(jsonElement));
            case TOGGLE -> new Toggle()
                    .content(getRichTextFromJsonElement(jsonElement, "rich_text", jsonDeserializationContext))
                    .color(getColorFromJsonElement(jsonElement));
            case UNSUPPORTED -> throw new UnsupportedOperationException("Unsupported block type");
            case VIDEO -> new Video()
                    .caption(getRichTextFromJsonElement(jsonElement, "caption", jsonDeserializationContext))
                    .type(FileType.fromString(jsonElement.getAsJsonObject().get("type").getAsString()))
                    .file(getFileFromJsonElement(jsonElement, jsonDeserializationContext));
        };
    }

    private List<RichText> getRichTextFromJsonElement(JsonElement jsonElement, String rich_text, JsonDeserializationContext jsonDeserializationContext) {
        if (jsonElement == null || rich_text == null || jsonElement.isJsonNull() || !jsonElement.getAsJsonObject().has(rich_text)) {
            return null;
        }
        return new CustomRichTextDeserializer().deserialize(jsonElement.getAsJsonObject().get(rich_text), RICH_TEXT_LIST_TYPE, jsonDeserializationContext);
    }

    private String getStringFromJsonElement(JsonElement jsonElement, String key) {
        if (jsonElement == null || key == null || jsonElement.isJsonNull() || !jsonElement.getAsJsonObject().has(key)) {
            return null;
        }
        return jsonElement.getAsJsonObject().get(key).getAsString();
    }

    private Color getColorFromJsonElement(JsonElement jsonElement) {
        if (jsonElement == null || jsonElement.isJsonNull() || !jsonElement.getAsJsonObject().has("color")) {
            return null;
        }
        return Color.fromString(jsonElement.getAsJsonObject().get("color").getAsString());
    }

    private de.flix29.notionApiClient.model.File getFileFromJsonElement(JsonElement jsonElement, JsonDeserializationContext jsonDeserializationContext) {
        if (jsonElement == null || jsonElement.isJsonNull() || !jsonElement.getAsJsonObject().has("file")) {
            return null;
        }
        return new CustomFileDeserializer().deserialize(jsonElement, de.flix29.notionApiClient.model.File.class, jsonDeserializationContext);
    }

    private Heading createHeading(JsonElement jsonElement, int level, JsonDeserializationContext jsonDeserializationContext) {
        return new Heading(level)
                .content(getRichTextFromJsonElement(jsonElement, "rich_text", jsonDeserializationContext))
                .color(getColorFromJsonElement(jsonElement))
                .isToggleable(jsonElement.getAsJsonObject().get("is_toggleable").getAsBoolean());
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
