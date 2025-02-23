package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;
import de.flix29.notionApiClient.model.database.databaseProperty.Property;
import de.flix29.notionApiClient.testdata.PagePropertiesTestdata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CustomPagePropertiesDeserializerTest {

    private CustomPagePropertiesDeserializer customPagePropertiesDeserializer;

    @BeforeEach
    void setUp() {
        customPagePropertiesDeserializer = new CustomPagePropertiesDeserializer();
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
        var databaseProperties = customPagePropertiesDeserializer.deserialize(jsonElement, Property.class, null);

        assertThat(databaseProperties)
                .isNull();
    }

    @Test
    void map_isEmpty() throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/pageProperties/pageProperties_empty.json"));
        var databaseProperties = customPagePropertiesDeserializer.deserialize(jsonElement, Property.class, null);

        assertThat(databaseProperties)
                .usingRecursiveComparison()
                .isEqualTo(PagePropertiesTestdata.emptyProperties());
    }

    @Test
    void map_isOk() throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/pageProperties/pageProperties_allSet.json"));
        var databaseProperties = customPagePropertiesDeserializer.deserialize(jsonElement, Property.class, null);

        assertThat(databaseProperties)
                .usingRecursiveComparison()
                .isEqualTo(PagePropertiesTestdata.allProperties());
    }

}