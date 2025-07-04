package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.*;
import de.flix29.notionApiClient.model.RichText;
import de.flix29.notionApiClient.model.block.BlockType;
import de.flix29.notionApiClient.model.block.blockContent.BlockContent;
import de.flix29.notionApiClient.model.block.blockContent.Paragraph;

import java.lang.reflect.Type;
import java.util.List;

public class CustomBlockContentListSerializer implements JsonSerializer<List<BlockContent>> {

    @Override
    public JsonElement serialize(List<BlockContent> blockContentList, Type typeOfSrc, JsonSerializationContext jsonSerializationContext) {
        if (blockContentList == null || blockContentList.isEmpty()) {
            return JsonNull.INSTANCE;
        }

        var output = new JsonArray();
        blockContentList.forEach(blockContent -> {
            JsonObject element = new JsonObject();
            element.addProperty("object", "block");
            switch (blockContent) {
//            case Bookmark bookmark -> serializeBookmark(bookmark, jsonSerializationContext);
//            case Breadcrumb breadcrumb -> serializeBreadcrumb(breadcrumb, jsonSerializationContext);
//            case BulletListItem bulletListItem -> serializeBulletListItem(bulletListItem, jsonSerializationContext);
//            case Callout callout -> serializeCallout(callout, jsonSerializationContext);
//            case ChildDatabase childDatabase -> serializeChildDatabase(childDatabase, jsonSerializationContext);
//            case ChildPage childPage -> serializeChildPage(childPage, jsonSerializationContext);
//            case Column column -> serializeColumn(column, jsonSerializationContext);
//            case ColumnList columnList -> serializeColumnList(columnList, jsonSerializationContext);
//            case Divider divider -> serializeDivider(divider, jsonSerializationContext);
//            case Embed embed -> serializeEmbed(embed, jsonSerializationContext);
//            case Equation equation -> serializeEquation(equation, jsonSerializationContext);
//            case File file -> serializeFile(file, jsonSerializationContext);
//            case Heading heading -> serializeHeading(heading, jsonSerializationContext);
//            case Image image -> serializeImage(image, jsonSerializationContext);
//            case LinkPreview linkPreview -> serializeLinkPreview(linkPreview, jsonSerializationContext);
//            case Template template -> serializeTemplate(template, jsonSerializationContext);
//            case NumberedListItem numberedListItem -> serializeNumberedListItem(numberedListItem, jsonSerializationContext);
                case Paragraph paragraph -> {
                    element.addProperty("type", paragraph.getClass().getSimpleName().toLowerCase());
                    element.add(BlockType.fromBlockContentClass(paragraph.getClass()).getType().toLowerCase(), new CustomRichTextDeserializer().serialize(paragraph.getContent(), RichText.class, jsonSerializationContext));
                }
//            case Pdf pdf -> serializePdf(pdf, jsonSerializationContext);
//            case Quote quote -> serializeQuote(quote, jsonSerializationContext);
//            case SyncedBlock syncedBlock -> serializeSyncedBlock(syncedBlock, jsonSerializationContext);
//            case Table table -> serializeTable(table, jsonSerializationContext);
//            case TableOfContents tableOfContents -> serializeTableOfContents(tableOfContents, jsonSerializationContext);
//            case TableRow tableRow -> null; //only accessible using child endpoint
//            case ToDo toDo -> serializeToDo(toDo, jsonSerializationContext);
//            case Toggle toggle -> serializeToggle(toggle, jsonSerializationContext);
//            case Video video -> serializeVideo(video, jsonSerializationContext);
                default -> {
                }
            }
            output.add(element);
        });
        return output;
    }
}
