package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;
import de.flix29.notionApiClient.model.*;
import de.flix29.notionApiClient.testdata.UserTestdata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CustomUserDeserializerTest {

    private CustomUserDeserializer customUserDeserializer;

    @BeforeEach
    void setUp() {
        customUserDeserializer = new CustomUserDeserializer();
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
        var annotations = customUserDeserializer.deserialize(jsonElement, Annotations.class, null);

        assertThat(annotations).isNull();
    }

    private static Stream<Arguments> map_isEmpty() {
        return Stream.of(
                Arguments.of("src/test/resources/testdataJson/user/User_Person_Empty.json", UserTestdata.peopleEmpty()),
                Arguments.of("src/test/resources/testdataJson/user/User_Bot_Empty.json", UserTestdata.botEmpty())
        );
    }

    @MethodSource
    @ParameterizedTest
    void map_isEmpty(String pathToFile, User expectedUser) throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader(pathToFile));
        var user = customUserDeserializer.deserialize(jsonElement, Annotations.class, null);

        assertThat(user)
                .usingRecursiveAssertion()
                .isEqualTo(expectedUser);
    }

    private static Stream<Arguments> map_isOk() {
        return Stream.of(
                Arguments.of("src/test/resources/testdataJson/user/User_Person_AllSet.json", UserTestdata.peopleAllSet()),
                Arguments.of("src/test/resources/testdataJson/user/User_Bot_AllSet.json", UserTestdata.botAllSet())
        );
    }

    @MethodSource
    @ParameterizedTest
    void map_isOk(String pathToFile, User expectedUser) throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader(pathToFile));
        var uuid = UUID.fromString(jsonElement.getAsJsonObject().get("id").getAsString());
        expectedUser.setId(uuid);
        var user = customUserDeserializer.deserialize(jsonElement, Annotations.class, null);


        assertThat(user)
                .usingRecursiveAssertion()
                .isEqualTo(expectedUser);
    }

}