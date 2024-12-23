package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;
import de.flix29.notionApiClient.model.*;
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
                Arguments.of("src/test/resources/testdataJson/User_Person_Empty.json", UserType.PERSON),
                Arguments.of("src/test/resources/testdataJson/User_Bot_Empty.json", UserType.BOT)
        );
    }

    @MethodSource
    @ParameterizedTest
    void map_isEmpty(String pathToFile, UserType userType) throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader(pathToFile));
        var user = customUserDeserializer.deserialize(jsonElement, Annotations.class, null);

        assertThat(user)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", null)
                .hasFieldOrPropertyWithValue("type", userType)
                .hasFieldOrPropertyWithValue("name", null)
                .hasFieldOrPropertyWithValue("avatarUrl", null);

        if (user instanceof People) {
            assertThat(user)
                    .hasFieldOrPropertyWithValue("email", null);
        } else {
            assertThat(user)
                    .extracting(bot -> ((Bot) bot).getOwner())
                    .hasFieldOrPropertyWithValue("type", BotOwnerType.WORKSPACE)
                    .hasFieldOrPropertyWithValue("workspaces", true);
        }
    }

    private static Stream<Arguments> map_isOk() {
        return Stream.of(
                Arguments.of("src/test/resources/testdataJson/User_Person_AllSet.json", UserType.PERSON),
                Arguments.of("src/test/resources/testdataJson/User_Bot_AllSet.json", UserType.BOT)
        );
    }

    @MethodSource
    @ParameterizedTest
    void map_isOk(String pathToFile, UserType userType) throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader(pathToFile));
        var user = customUserDeserializer.deserialize(jsonElement, Annotations.class, null);

        assertThat(user)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", UUID.fromString("4f97c2c0-7d15-4914-a175-746d27f32e40"))
                .hasFieldOrPropertyWithValue("type", userType)
                .hasFieldOrPropertyWithValue("name", "User name")
                .hasFieldOrPropertyWithValue("avatarUrl", "https://url-to-image.com/image");

        if (user instanceof People) {
            assertThat(user)
                    .hasFieldOrPropertyWithValue("email", "user.name@mail.com");
        } else {
            assertThat(user)
                    .extracting(bot -> ((Bot) bot).getOwner())
                    .hasFieldOrPropertyWithValue("type", BotOwnerType.WORKSPACE)
                    .hasFieldOrPropertyWithValue("workspaces", true);
        }
    }

}