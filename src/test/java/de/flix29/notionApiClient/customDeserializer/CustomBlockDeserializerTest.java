package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;
import de.flix29.notionApiClient.model.block.Block;
import de.flix29.notionApiClient.testdata.BlockTestdata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CustomBlockDeserializerTest {

    private CustomBlockDeserializer customBlockDeserializer;

    @BeforeEach
    void setUp() {
        customBlockDeserializer = new CustomBlockDeserializer();
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
        var block = customBlockDeserializer.deserialize(jsonElement, Block.class, null);

        assertThat(block)
                .isNull();
    }

    @Test
    void map_isEmpty() throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/block/block_empty.json"));
        var block = customBlockDeserializer.deserialize(jsonElement, Block.class, null);

        assertThat(block)
                .isEqualTo(BlockTestdata.blockEmpty());
    }

    @Test
    void map_isOk() throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/block/block_allSet.json"));
        var block = customBlockDeserializer.deserialize(jsonElement, Block.class, null);

        assertThat(block)
                .isEqualTo(BlockTestdata.blockAllSet());
    }

}