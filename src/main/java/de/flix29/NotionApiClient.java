package de.flix29;

import lombok.Getter;

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
    }
}
