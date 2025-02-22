package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;
import de.flix29.notionApiClient.testdata.DatabaseContentTestdata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Stream;

import static de.flix29.notionApiClient.customDeserializer.CustomModelTypes.PROPERTY_LIST_TYPE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CustomDatabasePropertiesDeserializerTest {

    private CustomDatabasePropertiesDeserializer customDatabasePropertiesDeserializer;

    @BeforeEach
    void setUp() {
        customDatabasePropertiesDeserializer = new CustomDatabasePropertiesDeserializer();
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
        var databaseProperties = customDatabasePropertiesDeserializer.deserialize(jsonElement, PROPERTY_LIST_TYPE, null);

        assertThat(databaseProperties)
                .isNull();
    }

    @Test
    void map_isEmpty() throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/databaseContent/allProperties_empty.json"));
        var databaseProperties = customDatabasePropertiesDeserializer.deserialize(jsonElement, PROPERTY_LIST_TYPE, null);

        assertThat(databaseProperties)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(DatabaseContentTestdata.emptyProperties());
    }

    @Test
    void map_isOk() throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/databaseContent/allProperties_allSet.json"));
        var databaseProperties = customDatabasePropertiesDeserializer.deserialize(jsonElement, PROPERTY_LIST_TYPE, null);

        assertThat(databaseProperties)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(DatabaseContentTestdata.allProperties());
    }

}