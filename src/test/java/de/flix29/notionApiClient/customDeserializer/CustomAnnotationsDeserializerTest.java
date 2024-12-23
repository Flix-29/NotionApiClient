package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.*;
import de.flix29.notionApiClient.model.Annotations;
import de.flix29.notionApiClient.model.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CustomAnnotationsDeserializerTest {

    private CustomAnnotationsDeserializer customAnnotationsDeserializer;

    @BeforeEach
    void setUp() {
        customAnnotationsDeserializer = new CustomAnnotationsDeserializer();
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
        var annotations = customAnnotationsDeserializer.deserialize(jsonElement, Annotations.class, null);

        assertThat(annotations).isNull();
    }

    @Test
    void map_isEmpty() throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/Annotations_Empty.json"));
        var annotations = customAnnotationsDeserializer.deserialize(jsonElement, Annotations.class, null);

        assertThat(annotations)
                .isNotNull()
                .hasFieldOrPropertyWithValue("bold", false)
                .hasFieldOrPropertyWithValue("italic", false)
                .hasFieldOrPropertyWithValue("strikethrough", false)
                .hasFieldOrPropertyWithValue("underline", false)
                .hasFieldOrPropertyWithValue("code", false)
                .hasFieldOrPropertyWithValue("color", Color.DEFAULT);
    }

    @Test
    void map_isOk() throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/Annotations_AllSet.json"));
        var annotations = customAnnotationsDeserializer.deserialize(jsonElement, Annotations.class, null);

        assertThat(annotations)
                .isNotNull()
                .hasFieldOrPropertyWithValue("bold", true)
                .hasFieldOrPropertyWithValue("italic", true)
                .hasFieldOrPropertyWithValue("strikethrough", true)
                .hasFieldOrPropertyWithValue("underline", true)
                .hasFieldOrPropertyWithValue("code", true)
                .hasFieldOrPropertyWithValue("color", Color.RED);
    }

}