package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;
import de.flix29.notionApiClient.model.database.Database;
import de.flix29.notionApiClient.testdata.DatabaseTestdata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CustomDatabaseDeserializerTest {

    private CustomDatabaseDeserializer customDatabaseDeserializer;

    @BeforeEach
    void setUp() {
        customDatabaseDeserializer = new CustomDatabaseDeserializer();
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
        var databaseProperties = customDatabaseDeserializer.deserialize(jsonElement, Database.class, null);

        assertThat(databaseProperties)
                .isNull();
    }

    @Test
    void map_isEmpty() throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/database/database_empty.json"));
        var databaseProperties = customDatabaseDeserializer.deserialize(jsonElement, Database.class, null);

        assertThat(databaseProperties)
                .usingRecursiveComparison()
                .isEqualTo(DatabaseTestdata.databaseEmpty());
    }

    @Test
    void map_isOk() throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/database/database_allSet.json"));
        var databaseProperties = customDatabaseDeserializer.deserialize(jsonElement, Database.class, null);

        assertThat(databaseProperties)
                .usingRecursiveComparison()
                .isEqualTo(DatabaseTestdata.databaseAllSet());
    }

}