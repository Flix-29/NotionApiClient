package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.OffsetDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomOffsetDateTimeDeserializerTest {

    private CustomOffsetDateTimeDeserializer customOffsetDateTimeDeserializer;

    @BeforeEach
    void setUp() {
        customOffsetDateTimeDeserializer = new CustomOffsetDateTimeDeserializer();
    }

    @Test
    void map_isNull() {
        var result = customOffsetDateTimeDeserializer.deserialize(null, OffsetDateTime.class, null);

        assertThat(result).isNull();
    }

    private static Stream<Arguments> map_isOK() {
        return Stream.of(
                Arguments.of("2025-01-01T12:00:00.000+00:00", OffsetDateTime.parse("2025-01-01T12:00:00.000+00:00")),
                Arguments.of("2025-01-01T12:00:00.000Z", OffsetDateTime.parse("2025-01-01T12:00:00.000+00:00")),
                Arguments.of("2025-01-01", OffsetDateTime.parse("2025-01-01T00:00:00.000+00:00"))
        );
    }

    @MethodSource
    @ParameterizedTest
    void map_isOK(String date, OffsetDateTime expectedDate) {
        var json = JsonParser.parseString("{\"date\": \"" + date + "\"}").getAsJsonObject();
        OffsetDateTime result = customOffsetDateTimeDeserializer.deserialize(json.get("date"), OffsetDateTime.class, null);

        assertThat(result)
                .isNotNull()
                .isEqualTo(expectedDate);
    }
}
