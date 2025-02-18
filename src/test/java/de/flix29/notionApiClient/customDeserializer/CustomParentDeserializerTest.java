package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;
import de.flix29.notionApiClient.model.Annotations;
import de.flix29.notionApiClient.model.Parent;
import de.flix29.notionApiClient.testdata.ParentTestdata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CustomParentDeserializerTest {

    private CustomParentDeserializer customParentDeserializer;

    @BeforeEach
    void setUp() {
        customParentDeserializer = new CustomParentDeserializer();
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
        var annotations = customParentDeserializer.deserialize(jsonElement, Annotations.class, null);

        assertThat(annotations).isNull();
    }

    @Test
    void map_isEmpty() throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/parent/Parent_Empty.json"));
        var expectedParent = ParentTestdata.parentEmpty();
        var parent = customParentDeserializer.deserialize(jsonElement, Parent.class, null);

        assertThat(parent)
                .usingRecursiveAssertion()
                .isEqualTo(expectedParent);
    }

    private static Stream<Arguments> map_isOk() {
        return Stream.of(
                Arguments.of("Parent_Page_AllSet.json", ParentTestdata.parentPageAllSet()),
                Arguments.of("Parent_Workspace_AllSet.json", ParentTestdata.parentWorkspaceAllSet())
        );
    }

    @MethodSource
    @ParameterizedTest
    void map_isOk(String fileName, Parent expectedParent) throws FileNotFoundException {
        var jsonElement = JsonParser.parseReader(new FileReader("src/test/resources/testdataJson/parent/" + fileName));
        var uuid = jsonElement.getAsJsonObject().get("page_id");
        expectedParent.setId(uuid == null ? null : UUID.fromString(uuid.getAsString()));
        var parent = customParentDeserializer.deserialize(jsonElement, Annotations.class, null);

        assertThat(parent)
                .usingRecursiveAssertion()
                .isEqualTo(expectedParent);
    }
}