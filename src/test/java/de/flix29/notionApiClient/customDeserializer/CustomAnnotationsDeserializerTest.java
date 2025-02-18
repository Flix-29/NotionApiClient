package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.*;
import de.flix29.notionApiClient.model.Annotations;
import de.flix29.notionApiClient.testdata.AnnotationsTestdata;
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
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/annotations/Annotations_Empty.json"));
        var expectedAnnotations = AnnotationsTestdata.annotationsEmpty();
        var annotations = customAnnotationsDeserializer.deserialize(jsonElement, Annotations.class, null);

        assertThat(annotations)
                .usingRecursiveAssertion()
                .isEqualTo(expectedAnnotations);
    }

    @Test
    void map_isOk() throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/annotations/Annotations_AllSet.json"));
        var expectedAnnotations = AnnotationsTestdata.annotationsAllSet();
        var annotations = customAnnotationsDeserializer.deserialize(jsonElement, Annotations.class, null);

        assertThat(annotations)
                .usingRecursiveAssertion()
                .isEqualTo(expectedAnnotations);
    }

}