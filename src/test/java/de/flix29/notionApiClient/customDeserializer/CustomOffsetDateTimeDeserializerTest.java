package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

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

    @Test
    void map_isOK() {
        OffsetDateTime now = OffsetDateTime.now();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("start", now.toString());
        OffsetDateTime result = customOffsetDateTimeDeserializer.deserialize(jsonObject.get("start"), OffsetDateTime.class, null);

        assertThat(result)
                .isNotNull()
                .isEqualTo(now);
    }
}
