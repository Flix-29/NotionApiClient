package de.flix29.notionApiClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.flix29.notionApiClient.customDeserializer.*;
import de.flix29.notionApiClient.model.User;
import de.flix29.notionApiClient.model.block.Block;
import de.flix29.notionApiClient.model.database.Database;
import de.flix29.notionApiClient.model.page.Page;
import de.flix29.notionApiClient.model.database.databaseProperty.Property;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static de.flix29.notionApiClient.customDeserializer.CustomModelTypes.BLOCK_LIST_TYPE;
import static de.flix29.notionApiClient.customDeserializer.CustomModelTypes.USER_LIST_TYPE;

@SuppressWarnings({"unused", "FieldCanBeLocal", "Duplicates", "unchecked"})
@Log
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

    private final String NOTION_VERSION = "2022-06-28"; // latest version, see https://developers.notion.com/reference/versioning
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
        log.info("Requesting database with id: " + databaseId);
        var databaseUri = buildUri(NOTION_DATABASE_URL, databaseId);
        var builder = requestBuilder.uri(URI.create(databaseUri)).build();
        var response = HttpClient.newHttpClient().send(builder, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Notion response error: " + response.statusCode() + " - " + response.body());
        }

        return parseFromJson(response.body(), TypeToken.get(Database.class));
    }

    public Block getBlock(String blockId) throws IOException, InterruptedException {
        log.info("Requesting block with id: " + blockId);
        var blockUri = buildUri(NOTION_BLOCK_URL, blockId);
        var builder = requestBuilder.uri(URI.create(blockUri)).build();
        var response = HttpClient.newHttpClient().send(builder, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Notion response error: " + response.statusCode() + " - " + response.body());
        }

        return parseFromJson(response.body(), TypeToken.get(Block.class));
    }

    public List<Block> getBlockChildren(String blockId) throws IOException, InterruptedException {
        log.info("Requesting block children of block with id: " + blockId);
        var blockUri = buildUri(NOTION_BLOCK_CHILDREN_URL, blockId);
        var builder = requestBuilder.uri(URI.create(blockUri)).build();
        var response = HttpClient.newHttpClient().send(builder, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Notion response error: " + response.statusCode() + " - " + response.body());
        }

        return (List<Block>) parseFromJson(response.body(), TypeToken.get(BLOCK_LIST_TYPE));
    }

    public List<Block> getBlockChildrenRecursive(String blockId) throws IOException, InterruptedException {
        log.info("Requesting block children recursively of block with id: " + blockId);
        var blockUri = buildUri(NOTION_BLOCK_CHILDREN_URL, blockId);
        var builder = requestBuilder.uri(URI.create(blockUri)).build();
        var response = HttpClient.newHttpClient().send(builder, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Notion response error: " + response.statusCode() + " - " + response.body());
        }
        List<Block> blocks = (List<Block>) parseFromJson(response.body(), TypeToken.get(BLOCK_LIST_TYPE));
        blocks.forEach(block -> {
            if (block.isHasChildren()) {
                try {
                    block.getBlockContent().setChildren(getBlockChildrenRecursive(String.valueOf(block.getId())));
                } catch (IOException | InterruptedException ignored) {
                }
            }
        });
        log.info("Returning block children recursively");
        return blocks;
    }

    public Page getPageProperties(String pageId) throws IOException, InterruptedException {
        log.info("Requesting page properties of page with id: " + pageId);
        var pageUri = buildUri(NOTION_PAGE_URL, pageId);
        var builder = requestBuilder.uri(URI.create(pageUri)).build();
        var response = HttpClient.newHttpClient().send(builder, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Notion response error: " + response.statusCode() + " - " + response.body());
        }
        log.info("Returning page properties");
        return parseFromJson(response.body(), TypeToken.get(Page.class));
    }

    public Property getPageProperty(String pageId, String propertyId) throws IOException, InterruptedException {
        log.info("Requesting page property with id: " + propertyId + " of page with id: " + pageId);
        var page = getPageProperties(pageId);
        var property = page.getProperties().stream()
                .filter(currentProperty -> currentProperty.getId().equals(propertyId))
                .findFirst()
                .orElse(null);
        log.info("Returning page property");
        return property;
    }

    public List<User> listAllUsers() throws IOException, InterruptedException {
        log.info("Requesting all users");
        var builder = requestBuilder.uri(URI.create(NOTION_USERS_URL)).build();
        var response = HttpClient.newHttpClient().send(builder, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Notion response error: " + response.statusCode() + " - " + response.body());
        }
        return (List<User>) parseFromJson(response.body(), TypeToken.get(USER_LIST_TYPE));
    }

    public User getUser(String userId) throws IOException, InterruptedException {
        log.info("Requesting user with id: " + userId);
        var userUri = NOTION_USERS_URL + "/" + userId;
        var builder = requestBuilder.uri(URI.create(userUri)).build();
        var response = HttpClient.newHttpClient().send(builder, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Notion response error: " + response.statusCode() + " - " + response.body());
        }
        return parseFromJson(response.body(), TypeToken.get(User.class));
    }

    public User getCurrentUser() throws IOException, InterruptedException {
        log.info("Requesting current user");
        var userUri = NOTION_USERS_URL + "/me";
        var builder = requestBuilder.uri(URI.create(userUri)).build();
        var response = HttpClient.newHttpClient().send(builder, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Notion response error: " + response.statusCode() + " - " + response.body());
        }
        return parseFromJson(response.body(), TypeToken.get(User.class));
    }

    private String buildUri(@NotNull String url, @NotNull String id) {
        return url.replace("$id$", id);
    }

    private <T> T parseFromJson(@NotNull String json, @NotNull TypeToken<T> targetClass) {
        log.info("Trying to parse json to " + targetClass.getRawType().getSimpleName());
        var output = gson.fromJson(json, targetClass);
        log.info("Returning " + targetClass.getRawType().getSimpleName());
        return output;
    }
}
