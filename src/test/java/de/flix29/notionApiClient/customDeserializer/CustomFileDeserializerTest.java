package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;
import de.flix29.notionApiClient.model.File;
import de.flix29.notionApiClient.testdata.FileTestdata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CustomFileDeserializerTest {

    private CustomFileDeserializer customFileDeserializer;

    @BeforeEach
    void setUp() {
        customFileDeserializer = new CustomFileDeserializer();
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
        var file = customFileDeserializer.deserialize(jsonElement, null, null);

        assertThat(file).isNull();
    }

    @Test
    void map_isEmpty() throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/file/File_empty.json"));
        var expectedFile = FileTestdata.fileEmpty();
        var file = customFileDeserializer.deserialize(jsonElement, File.class, null);

        assertThat(file)
                .usingRecursiveAssertion()
                .isEqualTo(expectedFile);
    }

    private static Stream<Arguments> map_isOk() {
        return Stream.of(
                Arguments.of("external_allSet", FileTestdata.fileExternalAllSet()),
                Arguments.of("file_allSet", FileTestdata.fileFileAllSet())
        );
    }

    @MethodSource
    @ParameterizedTest
    void map_isOk(String path, File expectedFile) throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/file/File_" + path + ".json"));
        var file = customFileDeserializer.deserialize(jsonElement, File.class, null);

        assertThat(file)
                .usingRecursiveAssertion()
                .isEqualTo(expectedFile);
    }
}