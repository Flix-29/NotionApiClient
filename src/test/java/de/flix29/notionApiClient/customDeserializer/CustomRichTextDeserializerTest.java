package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;
import de.flix29.notionApiClient.model.RichText;
import de.flix29.notionApiClient.testdata.RichTextTestdata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Stream;

import static de.flix29.notionApiClient.customDeserializer.CustomModelTypes.RICH_TEXT_LIST_TYPE;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomRichTextDeserializerTest {

    private CustomRichTextDeserializer customRichTextDeserializer;

    @BeforeEach
    void setUp() {
        customRichTextDeserializer = new CustomRichTextDeserializer();
    }

    public static Stream<Arguments> map_isNull() {
        return Stream.of(
                Arguments.of((Object) null),
                Arguments.of(JsonNull.INSTANCE)
        );
    }

    @MethodSource
    @ParameterizedTest
    void map_isNull(JsonElement jsonElement) {
        var richText = customRichTextDeserializer.deserialize(jsonElement, RICH_TEXT_LIST_TYPE, null);

        assertNull(richText);
    }

    @Test
    void map_isEmpty() {
        var jsonElement = JsonParser.parseString("[]");
        var richText = customRichTextDeserializer.deserialize(jsonElement, RICH_TEXT_LIST_TYPE, null);

        assertNull(richText);
    }

    public static Stream<Arguments> map_isOk() {
        return Stream.of(
                Arguments.of("text_allSet", RichTextTestdata.richTextsTextAllSet()),
                Arguments.of("equation_allSet", RichTextTestdata.richTextEquationsAllSet()),
                Arguments.of("mentionDB_allSet", RichTextTestdata.richTextMentionDatabasesAllSet()),
                Arguments.of("mentionPage_allSet", RichTextTestdata.richTextMentionPagesAllSet()),
                Arguments.of("mentionUser_allSet", RichTextTestdata.richTextMentionUsersAllSet()),
                Arguments.of("mentionDate_allSet", RichTextTestdata.richTextMentionDatesAllSet()),
                Arguments.of("mentionLinkPreview_allSet", RichTextTestdata.richTextMentionLinkPreviewsAllSet())
        );
    }

    @MethodSource
    @ParameterizedTest
    void map_isOk(String path, List<RichText> expectedRichText) throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/richtext/richText_" + path + ".json"));
        var richText = customRichTextDeserializer.deserialize(jsonElement, RICH_TEXT_LIST_TYPE, null);

        assertThat(richText)
                .usingRecursiveComparison()
                .isEqualTo(expectedRichText);
    }
}