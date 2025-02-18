package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;
import de.flix29.notionApiClient.model.File;
import de.flix29.notionApiClient.model.Icon;
import de.flix29.notionApiClient.testdata.IconTestdata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CustomIconDeserializerTest {

    private CustomIconDeserializer customIconDeserializer;

    @BeforeEach
    void setUp() {
        customIconDeserializer = new CustomIconDeserializer();
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
        var file = customIconDeserializer.deserialize(jsonElement, null, null);

        assertThat(file).isNull();
    }

    private static Stream<Arguments> map_isEmpty() {
        return Stream.of(
                Arguments.of("file_empty", IconTestdata.fileEmpty()),
                Arguments.of("emoji_empty", IconTestdata.emojiEmpty())
        );
    }

    @MethodSource
    @ParameterizedTest
    void map_isEmpty(String path, Icon expectedIcon) throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/icon/Icon_" + path + ".json"));
        var file = customIconDeserializer.deserialize(jsonElement, File.class, null);

        assertThat(file)
                .usingRecursiveAssertion()
                .isEqualTo(expectedIcon);
    }

    private static Stream<Arguments> map_isOk() {
        return Stream.of(
                Arguments.of("external_allSet", IconTestdata.fileExternalAllSet()),
                Arguments.of("file_allSet", IconTestdata.fileFileAllSet()),
                Arguments.of("emoji_allSet", IconTestdata.emojiAllSet())
        );
    }

    @MethodSource
    @ParameterizedTest
    void map_isOk(String path, Icon expectedFile) throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/icon/Icon_" + path + ".json"));
        var icon = customIconDeserializer.deserialize(jsonElement, Icon.class, null);

        assertThat(icon)
                .usingRecursiveAssertion()
                .isEqualTo(expectedFile);
    }
}