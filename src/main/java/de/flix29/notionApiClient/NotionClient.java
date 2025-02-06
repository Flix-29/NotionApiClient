package de.flix29.notionApiClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.flix29.notionApiClient.customDeserializer.*;
import de.flix29.notionApiClient.model.User;
import de.flix29.notionApiClient.model.block.Block;
import de.flix29.notionApiClient.model.database.Database;
import de.flix29.notionApiClient.model.page.Page;
import de.flix29.notionApiClient.model.database.databaseProperty.Property;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static de.flix29.notionApiClient.customDeserializer.CustomModelTypes.BLOCK_LIST_TYPE;
import static de.flix29.notionApiClient.customDeserializer.CustomModelTypes.USER_LIST_TYPE;

@SuppressWarnings({"unused", "FieldCanBeLocal", "Duplicates"})
public class NotionClient {

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Database.class, new CustomDatabaseDeserializer())
            .registerTypeAdapter(Block.class, new CustomBlockDeserializer())
            .registerTypeAdapter(BLOCK_LIST_TYPE, new CustomBlockListDeserializer())
            .registerTypeAdapter(User.class, new CustomUserDeserializer())
            .registerTypeAdapter(USER_LIST_TYPE, new CustomUserListDeserializer())
            .registerTypeAdapter(Page.class, new CustomPageDeserializer())
            .create();

    private final String NOTION_VERSION = "2022-06-28";
    private final String NOTION_API_URL = "https://api.notion.com/v1";
    private final String NOTION_DATABASE_URL = NOTION_API_URL + "/databases/$id$";
    private final String NOTION_BLOCK_URL = NOTION_API_URL + "/blocks/$id$";
    private final String NOTION_PAGE_URL = NOTION_API_URL + "/pages/$id$";
    private final String NOTION_BLOCK_CHILDREN_URL = NOTION_BLOCK_URL + "/children";
    private final String NOTION_USERS_URL = NOTION_API_URL + "/users";

    private final HttpRequest.Builder requestBuilder;

    public NotionClient(String apikey) {
        if (apikey == null || apikey.isEmpty()) {
            throw new IllegalArgumentException("API key must not be null or empty");
        }

        this.requestBuilder = HttpRequest.newBuilder()
                .header("Authorization", apikey)
                .header("Notion-Version", NOTION_VERSION)
                .header("Content-Type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody());
    }

    public Database getDatabase(String databaseId) throws IOException, InterruptedException {
        var databaseUri = buildUri(NOTION_DATABASE_URL, databaseId);
        var builder = requestBuilder.uri(URI.create(databaseUri)).build();
        var response = HttpClient.newHttpClient().send(builder, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Notion response error: " + response.statusCode() + " - " + response.body());
        }
        return gson.fromJson(response.body(), Database.class);
    }

    public Block getBlock(String blockId) throws IOException, InterruptedException {
        var blockUri = buildUri(NOTION_BLOCK_URL, blockId);
        var builder = requestBuilder.uri(URI.create(blockUri)).build();
        var response = HttpClient.newHttpClient().send(builder, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Notion response error: " + response.statusCode() + " - " + response.body());
        }
        return gson.fromJson(response.body(), Block.class);
    }

    public List<Block> getBlockChildren(String blockId) throws IOException, InterruptedException {
        var blockUri = buildUri(NOTION_BLOCK_CHILDREN_URL, blockId);
        var builder = requestBuilder.uri(URI.create(blockUri)).build();
        var response = HttpClient.newHttpClient().send(builder, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Notion response error: " + response.statusCode() + " - " + response.body());
        }
        return gson.fromJson(response.body(), BLOCK_LIST_TYPE);
    }

    public List<Block> getBlockChildrenRecursive(String blockId) throws IOException, InterruptedException {
        var blockUri = buildUri(NOTION_BLOCK_CHILDREN_URL, blockId);
        var builder = requestBuilder.uri(URI.create(blockUri)).build();
        var response = HttpClient.newHttpClient().send(builder, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Notion response error: " + response.statusCode() + " - " + response.body());
        }
        List<Block> blocks = gson.fromJson(response.body(), BLOCK_LIST_TYPE);
        blocks.forEach(block -> {
            if (block.isHasChildren()) {
                try {
                    block.getBlockContent().setChildren(getBlockChildrenRecursive(String.valueOf(block.getId())));
                } catch (IOException | InterruptedException ignored) {
                }
            }
        });

        return blocks;
    }

    public Page getPageProperties(String pageId) throws IOException, InterruptedException {
        var pageUri = buildUri(NOTION_PAGE_URL, pageId);
        var builder = requestBuilder.uri(URI.create(pageUri)).build();
        var response = HttpClient.newHttpClient().send(builder, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Notion response error: " + response.statusCode() + " - " + response.body());
        }
        return gson.fromJson(response.body(), Page.class);
    }

    public Property getPageProperty(String pageId, String propertyId) throws IOException, InterruptedException {
        var page = getPageProperties(pageId);
        return page.getProperties().stream()
                .filter(property -> property.getId().equals(propertyId))
                .findFirst()
                .orElse(null);
    }

    public List<User> listAllUsers() throws IOException, InterruptedException {
        var builder = requestBuilder.uri(URI.create(NOTION_USERS_URL)).build();
        var response = HttpClient.newHttpClient().send(builder, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Notion response error: " + response.statusCode() + " - " + response.body());
        }
        return gson.fromJson(response.body(), USER_LIST_TYPE);
    }

    public User getUser(String userId) throws IOException, InterruptedException {
        var userUri = NOTION_USERS_URL + "/" + userId;
        var builder = requestBuilder.uri(URI.create(userUri)).build();
        var response = HttpClient.newHttpClient().send(builder, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Notion response error: " + response.statusCode() + " - " + response.body());
        }
        return gson.fromJson(response.body(), User.class);
    }

    public User getCurrentUser() throws IOException, InterruptedException {
        var userUri = NOTION_USERS_URL + "/me";
        var builder = requestBuilder.uri(URI.create(userUri)).build();
        var response = HttpClient.newHttpClient().send(builder, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Notion response error: " + response.statusCode() + " - " + response.body());
        }
        return gson.fromJson(response.body(), User.class);
    }

    private String buildUri(String url, String id) {
        return url.replace("$id$", id);
    }
}
