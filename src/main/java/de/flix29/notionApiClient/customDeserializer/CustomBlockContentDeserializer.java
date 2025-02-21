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
        if (jsonElement == null || jsonElement.isJsonNull() || blockType == null) {
            return null;
        }

        this.blockType = blockType;
        return deserialize(jsonElement, type, jsonDeserializationContext);
    }

    @Override
    public BlockContent deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return switch (blockType) {
            case BOOKMARK -> new Bookmark()
                    .setCaption(getRichTextFromJsonElement(jsonElement, "caption", jsonDeserializationContext))
                    .setUrl(getStringFromJsonElement(jsonElement, "url"));
            case BREADCRUMB -> new Breadcrumb();
            case BULLETED_LIST_ITEM -> new BulletListItem()
                    .setContent(getRichTextFromJsonElement(jsonElement, "rich_text", jsonDeserializationContext))
                    .setColor(getColorFromJsonElement(jsonElement));
            case CALLOUT -> new Callout()
                    .setContent(getRichTextFromJsonElement(jsonElement, "rich_text", jsonDeserializationContext))
                    .setIcon(new CustomIconDeserializer().deserialize(jsonElement.getAsJsonObject().get("icon"), Icon.class, jsonDeserializationContext))
                    .setColor(getColorFromJsonElement(jsonElement));
            case CHILD_DATABASE -> new ChildDatabase()
                    .setTitle(getStringFromJsonElement(jsonElement, "title"));
            case CHILD_PAGE -> new ChildPage()
                    .setTitle(getStringFromJsonElement(jsonElement, "title"));
            case COLUMN -> new Column(); //only accessible using child endpoint
            case COLUMN_LIST -> new ColumnList();
            case DIVIDER -> new Divider();
            case EMBED -> new Embed()
                    .setCaption(getRichTextFromJsonElement(jsonElement, "caption", jsonDeserializationContext))
                    .setUrl(getStringFromJsonElement(jsonElement, "url"));
            case EQUATION -> new Equation()
                    .setExpression(getStringFromJsonElement(jsonElement, "expression"));
            case FILE -> new File()
                    .setCaption(getRichTextFromJsonElement(jsonElement, "caption", jsonDeserializationContext))
                    .setFile(getFileFromJsonElement(jsonElement, jsonDeserializationContext))
                    .setName(getStringFromJsonElement(jsonElement, "name"));
            case HEADING_1 -> createHeading(jsonElement, 1, jsonDeserializationContext);
            case HEADING_2 -> createHeading(jsonElement, 2, jsonDeserializationContext);
            case HEADING_3 -> createHeading(jsonElement, 3, jsonDeserializationContext);
            case IMAGE -> new Image()
                    .setCaption(getRichTextFromJsonElement(jsonElement, "caption", jsonDeserializationContext))
                    .setFile(getFileFromJsonElement(jsonElement, jsonDeserializationContext));
            case LINK_PREVIEW -> new LinkPreview()
                    .setUrl(getStringFromJsonElement(jsonElement, "url"));
            case LINK_TO_PAGE, TEMPLATE -> null; //not accessible using api
            case NUMBERED_LIST_ITEM -> new NumberedListItem()
                    .setContent(getRichTextFromJsonElement(jsonElement, "rich_text", jsonDeserializationContext))
                    .setColor(getColorFromJsonElement(jsonElement));
            case PARAGRAPH -> new Paragraph()
                    .setContent(getRichTextFromJsonElement(jsonElement, "rich_text", jsonDeserializationContext))
                    .setColor(getColorFromJsonElement(jsonElement));
            case PDF -> new Pdf()
                    .setCaption(getRichTextFromJsonElement(jsonElement, "caption", jsonDeserializationContext))
                    .setType(FileType.fromString(getAsStringIfPresentAndNotNull(jsonElement.getAsJsonObject(), "type")))
                    .setFile(getFileFromJsonElement(jsonElement, jsonDeserializationContext));
            case QUOTE -> new Quote()
                    .setContent(getRichTextFromJsonElement(jsonElement, "rich_text", jsonDeserializationContext))
                    .setColor(getColorFromJsonElement(jsonElement));
            case SYNCED_BLOCK -> buildSyncedBlock(jsonElement);
            case TABLE -> new Table()
                    .setTableWidth(getAsIntegerIfPresentAndNotNull(jsonElement.getAsJsonObject(), "table_width"))
                    .setHasColumnHeader(getAsBooleanIfPresentAndNotNull(jsonElement.getAsJsonObject(), "has_column_header"))
                    .setHasRowHeader(getAsBooleanIfPresentAndNotNull(jsonElement.getAsJsonObject(), "has_row_header"));
            case TABLE_OF_CONTENTS -> new TableOfContents()
                    .setColor(getColorFromJsonElement(jsonElement));
            case TABLE_ROW -> new TableRow(); //only accessible using child endpoint
            case TO_DO -> new ToDo()
                    .setContent(getRichTextFromJsonElement(jsonElement, "rich_text", jsonDeserializationContext))
                    .setChecked(getAsBooleanIfPresentAndNotNull(jsonElement.getAsJsonObject(), "checked"))
                    .setColor(getColorFromJsonElement(jsonElement));
            case TOGGLE -> new Toggle()
                    .setContent(getRichTextFromJsonElement(jsonElement, "rich_text", jsonDeserializationContext))
                    .setColor(getColorFromJsonElement(jsonElement));
            case UNSUPPORTED -> null;
            case VIDEO -> new Video()
                    .setCaption(getRichTextFromJsonElement(jsonElement, "caption", jsonDeserializationContext))
                    .setType(FileType.fromString(getAsStringIfPresentAndNotNull(jsonElement.getAsJsonObject(), "type")))
                    .setFile(getFileFromJsonElement(jsonElement, jsonDeserializationContext));
        };
    }

    private Heading createHeading(JsonElement jsonElement, int level, JsonDeserializationContext jsonDeserializationContext) {
        return new Heading(level)
                .setContent(getRichTextFromJsonElement(jsonElement, "rich_text", jsonDeserializationContext))
                .setColor(getColorFromJsonElement(jsonElement))
                .setToggleable(getAsBooleanIfPresentAndNotNull(jsonElement.getAsJsonObject(), "is_toggleable"));
    }

    private SyncedBlock buildSyncedBlock(JsonElement jsonElement) {
        var syncedBlock = new SyncedBlock();
        var jsonObject = jsonElement.getAsJsonObject();
        if (jsonObject.get("synced_from") != null && !jsonObject.get("synced_from").isJsonNull()) {
            return syncedBlock.setSyncedFrom(jsonObject.get("synced_from").getAsJsonObject().get("block_id").getAsString());
        }

        return syncedBlock;
    }
}
