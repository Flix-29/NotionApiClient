package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;
import de.flix29.notionApiClient.model.block.BlockType;
import de.flix29.notionApiClient.model.block.blockContent.BlockContent;
import de.flix29.notionApiClient.testdata.BlockContentTestdata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class CustomBlockContentDeserializerTest {

    private CustomBlockContentDeserializer customBlockContentDeserializer;

    @BeforeEach
    void setUp() {
        customBlockContentDeserializer = new CustomBlockContentDeserializer();
    }

    private static Stream<Arguments> map_isNull() {
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of(null, BlockType.BOOKMARK),
                Arguments.of(JsonNull.INSTANCE, null),
                Arguments.of(JsonNull.INSTANCE, BlockType.BOOKMARK)
        );
    }

    @MethodSource
    @ParameterizedTest
    void map_isNull(JsonElement jsonElement, BlockType blockType) {
        var blockContent = customBlockContentDeserializer.deserialize(jsonElement, BlockContent.class, null, blockType);

        assertThat(blockContent)
                .isNull();
    }

    private static Stream<Arguments> map_isEmpty() {
        return Stream.of(
                Arguments.of("bookmark", BlockType.BOOKMARK, BlockContentTestdata.bookmarkEmpty()),
                Arguments.of("bulletList", BlockType.BULLETED_LIST_ITEM, BlockContentTestdata.bulletedListItemEmpty()),
                Arguments.of("callout", BlockType.CALLOUT, BlockContentTestdata.calloutEmpty()),
                Arguments.of("childDB", BlockType.CHILD_DATABASE, BlockContentTestdata.childDatabaseEmpty()),
                Arguments.of("childPage", BlockType.CHILD_PAGE, BlockContentTestdata.childPageEmpty()),
                Arguments.of("embed", BlockType.EMBED, BlockContentTestdata.embedEmpty()),
//                Arguments.of("equation", BlockType.EQUATION, BlockContentTestdata.equationEmpty()),
                Arguments.of("file", BlockType.FILE, BlockContentTestdata.fileEmpty()),
                Arguments.of("heading", BlockType.HEADING_1, BlockContentTestdata.headingEmpty(1)),
                Arguments.of("heading", BlockType.HEADING_2, BlockContentTestdata.headingEmpty(2)),
                Arguments.of("heading", BlockType.HEADING_3, BlockContentTestdata.headingEmpty(3)),
                Arguments.of("image", BlockType.IMAGE, BlockContentTestdata.imageEmpty()),
                Arguments.of("linkPreview", BlockType.LINK_PREVIEW, BlockContentTestdata.linkPreviewEmpty()),
                Arguments.of("numberedList", BlockType.NUMBERED_LIST_ITEM, BlockContentTestdata.numberedListItemEmpty()),
                Arguments.of("paragraph", BlockType.PARAGRAPH, BlockContentTestdata.paragraphEmpty()),
                Arguments.of("pdf", BlockType.PDF, BlockContentTestdata.pdfEmpty()),
                Arguments.of("quote", BlockType.QUOTE, BlockContentTestdata.quoteEmpty()),
                Arguments.of("syncedBlock", BlockType.SYNCED_BLOCK, BlockContentTestdata.syncedBlockEmpty()),
                Arguments.of("table", BlockType.TABLE, BlockContentTestdata.tableEmpty()),
                Arguments.of("tableOfContents", BlockType.TABLE_OF_CONTENTS, BlockContentTestdata.tableOfContentsEmpty()),
                Arguments.of("todo", BlockType.TO_DO, BlockContentTestdata.toDoEmpty()),
                Arguments.of("toggle", BlockType.TOGGLE, BlockContentTestdata.toggleEmpty()),
                Arguments.of("video", BlockType.VIDEO, BlockContentTestdata.videoEmpty())
        );
    }

    @MethodSource
    @ParameterizedTest
    void map_isEmpty(String path, BlockType blockType, BlockContent expectedBlockType) throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/blockContent/blockContent_" + path + "_empty.json"));
        var blockContent = customBlockContentDeserializer.deserialize(jsonElement, BlockContent.class, null, blockType);

        assertThat(blockContent)
                .isNotNull()
                .isEqualTo(expectedBlockType);
    }

    private static Stream<Arguments> myp_isOk() {
        return Stream.of(
                Arguments.of("bookmark", BlockType.BOOKMARK, BlockContentTestdata.bookmarkAllSet()),
                Arguments.of("bulletList", BlockType.BULLETED_LIST_ITEM, BlockContentTestdata.bulletedListItemAllSet()),
                Arguments.of("callout", BlockType.CALLOUT, BlockContentTestdata.calloutAllSet()),
                Arguments.of("childDB", BlockType.CHILD_DATABASE, BlockContentTestdata.childDatabaseAllSet()),
                Arguments.of("childPage", BlockType.CHILD_PAGE, BlockContentTestdata.childPageAllSet()),
                Arguments.of("embed", BlockType.EMBED, BlockContentTestdata.embedAllSet()),
//                Arguments.of("equation", BlockType.EQUATION, BlockContentTestdata.equationAllSet()), TODO
                Arguments.of("file", BlockType.FILE, BlockContentTestdata.fileAllSet()),
                Arguments.of("heading", BlockType.HEADING_1, BlockContentTestdata.headingAllSet(1)),
                Arguments.of("heading", BlockType.HEADING_2, BlockContentTestdata.headingAllSet(2)),
                Arguments.of("heading", BlockType.HEADING_3, BlockContentTestdata.headingAllSet(3)),
                Arguments.of("image", BlockType.IMAGE, BlockContentTestdata.imageAllSet()),
                Arguments.of("linkPreview", BlockType.LINK_PREVIEW, BlockContentTestdata.linkPreviewAllSet()),
                Arguments.of("numberedList", BlockType.NUMBERED_LIST_ITEM, BlockContentTestdata.numberedListItemAllSet()),
                Arguments.of("paragraph", BlockType.PARAGRAPH, BlockContentTestdata.paragraphAllSet()),
                Arguments.of("pdf", BlockType.PDF, BlockContentTestdata.pdfAllSet()),
                Arguments.of("quote", BlockType.QUOTE, BlockContentTestdata.quoteAllSet()),
                Arguments.of("syncedBlock", BlockType.SYNCED_BLOCK, BlockContentTestdata.syncedBlockAllSet()),
                Arguments.of("table", BlockType.TABLE, BlockContentTestdata.tableAllSet()),
                Arguments.of("tableOfContents", BlockType.TABLE_OF_CONTENTS, BlockContentTestdata.tableOfContentsAllSet()),
                Arguments.of("todo", BlockType.TO_DO, BlockContentTestdata.toDoAllSet()),
                Arguments.of("toggle", BlockType.TOGGLE, BlockContentTestdata.toggleAllSet()),
                Arguments.of("video", BlockType.VIDEO, BlockContentTestdata.videoAllSet())
        );
    }

    @MethodSource
    @ParameterizedTest
    void myp_isOk(String path, BlockType blockType, BlockContent expectedBlockType) throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/blockContent/blockContent_" + path + "_allSet.json"));
        var blockContent = customBlockContentDeserializer.deserialize(jsonElement, BlockContent.class, null, blockType);

        assertThat(blockContent)
                .isNotNull()
                .isEqualTo(expectedBlockType);
    }
}