package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;
import de.flix29.notionApiClient.model.Annotations;
import de.flix29.notionApiClient.model.block.BlockType;
import de.flix29.notionApiClient.model.block.blockContent.BlockContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CustomBlockContentDeserializerTest {

    private CustomBlockContentDeserializer customBlockContentDeserializer;

    @BeforeEach
    void setUp() {
        customBlockContentDeserializer = new CustomBlockContentDeserializer();
    }

    private static Stream<Arguments> map_isNull() {
        return Stream.of(
                Arguments.of((Object) null),
                Arguments.of(JsonNull.INSTANCE)
        );
    }

    @MethodSource
    @ParameterizedTest
    void map_isNull(JsonElement jsonElement) {
        var annotations = customBlockContentDeserializer.deserialize(jsonElement, BlockContent.class, null, null);

        assertThat(annotations).isNull();
    }

    @Test
    void map_isEmpty() throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/blockContent/BlockContent_Empty.json"));
        var annotations = customBlockContentDeserializer.deserialize(jsonElement, Annotations.class, null, BlockType.EQUATION);

        assertThat(annotations)
                .isNotNull()
                .hasFieldOrPropertyWithValue("expression", null);
    }

    private static Stream<Arguments> map_isOk() {
        return Stream.of(
                Arguments.of("src/test/resources/testdataJson/blockContent/BlockContent_Equation_AllSet.json", BlockType.EQUATION)
        );
    }

    @MethodSource
    @ParameterizedTest
    void map_isOk(String url, BlockType blockType) throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader(url));
        var annotations = customBlockContentDeserializer.deserialize(jsonElement, BlockContent.class, null, blockType);

        assertThat(annotations)
                .isNotNull()
                .hasFieldOrPropertyWithValue("expression", "e = mc^2");
    }

}