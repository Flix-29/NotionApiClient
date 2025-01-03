package de.flix29;

import de.flix29.notionApiClient.NotionClient;
import lombok.Getter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static java.lang.System.getProperty;

public class NotionApiClient {

    @Getter
    private static final Map<String, String> secrets = new HashMap<>();

    public static void main(String[] args) {
        try {
            var propFile = NotionApiClient.class.getClassLoader().getResourceAsStream("application.properties");
            var properties = new Properties(System.getProperties());
            properties.load(propFile);
            System.setProperties(properties);

            secrets.put("notionKey", getProperty("notion.apikey"));
        } catch (Exception ignored) { }

        NotionClient notionClient = new NotionClient();
        try {
            notionClient.getDatabase("11c595002da880309cbee45484897d43");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
