package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;
import de.flix29.notionApiClient.model.User;
import de.flix29.notionApiClient.testdata.UserTestdata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static de.flix29.notionApiClient.customDeserializer.CustomModelTypes.USER_LIST_TYPE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomUserListDeserializerTest {

    private CustomUserListDeserializer customUserListDeserializer;

    @BeforeEach
    void setUp() {
        customUserListDeserializer = new CustomUserListDeserializer();
    }

    public static Stream<Arguments> map_isNull() {
        return Stream.of(
                Arguments.of((Object) null),
                Arguments.of(JsonNull.INSTANCE),
                Arguments.of(JsonParser.parseString("{\"results\": null}"))
        );
    }

    @MethodSource
    @ParameterizedTest
    void map_isNull(JsonElement jsonElement) {
        var users = customUserListDeserializer.deserialize(jsonElement, USER_LIST_TYPE, null);

        assertNull(users);
    }

    @Test
    void map_isEmpty() throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/user/UserList_empty.json"));
        var users = customUserListDeserializer.deserialize(jsonElement, USER_LIST_TYPE, null);

        assertThat(users).isEqualTo(Collections.emptyList());
    }

    @Test
    void map_isOkay() throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/user/UserList_allSet.json"));
        var expectedUser = correctIds(jsonElement, UserTestdata.userListAllSet());
        var users = customUserListDeserializer.deserialize(jsonElement, USER_LIST_TYPE, null);

        assertThat(users)
                .usingRecursiveAssertion()
                .isEqualTo(expectedUser);
    }

    private List<User> correctIds(JsonElement jsonElement, List<User> expectedUser) {
        var uuids = jsonElement.getAsJsonObject().get("results").getAsJsonArray()
                .asList().stream()
                .map(user -> UUID.fromString(user.getAsJsonObject().get("id").getAsString()))
                .toList();
        for (int i = 0; i < expectedUser.size(); i++) {
            expectedUser.get(i).setId(uuids.get(i));
        }
        return expectedUser;
    }
}