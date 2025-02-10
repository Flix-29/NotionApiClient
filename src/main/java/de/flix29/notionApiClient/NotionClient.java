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

/**
 * NotionClient is a simple Java client for the Notion API.<br>
 *
 * @see <a href="https://developers.notion.com/reference/intro">Notion API Reference</a>
 */
@Log
@SuppressWarnings({"unused", "FieldCanBeLocal", "Duplicates", "unchecked"})
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

    /**
     * Constructor for NotionClient.
     *
     * @param apikey Notion API key
     */
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

    /**
     * Get a database by its id.<br>
     * See <a href="https://developers.notion.com/reference/retrieve-a-database">API-Reference</a> for more information.
     *
     * @param databaseId The id of the database
     * @return The {@link Database} with the given id
     * @throws IOException if the request fails
     * @throws InterruptedException if the request fails
     */
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

    /**
     * Get a block by its id.<br>
     * See <a href="https://developers.notion.com/reference/retrieve-a-block">API-Reference</a> for more information.
     *
     * @param blockId The id of the block
     * @return The {@link Block} with the given id
     * @throws IOException if the request fails
     * @throws InterruptedException if the request fails
     */
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

    /**
     * Get the children of a block by its id.<br>
     * See <a href="https://developers.notion.com/reference/get-block-children">API-Reference</a> for more information.
     *
     * @param blockId The id of the block
     * @return The children of the block with the given id, represented as a list of {@link Block}
     * @throws IOException if the request fails
     * @throws InterruptedException if the request fails
     */
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

    /**
     * Get the children of a block with the given id and all its children.<br>
     * See <a href="https://developers.notion.com/reference/get-block-children">API-Reference</a> for more information.
     *
     * @param blockId The id of the block
     * @return The children of the block with the given id and all its children, represented as a list of {@link Block}
     * @throws IOException if the request fails
     * @throws InterruptedException if the request fails
     */
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
        return blocks;
    }

    /**
     * Get the properties of a page by its id.<br>
     * See <a href="https://developers.notion.com/reference/retrieve-a-page">API-Reference</a> for more information.
     *
     * @param pageId The id of the page
     * @return The properties of the {@link Page} with the given id
     * @throws IOException if the request fails
     * @throws InterruptedException if the request fails
     */
    public Page getPageProperties(String pageId) throws IOException, InterruptedException {
        log.info("Requesting page properties of page with id: " + pageId);
        var pageUri = buildUri(NOTION_PAGE_URL, pageId);
        var builder = requestBuilder.uri(URI.create(pageUri)).build();
        var response = HttpClient.newHttpClient().send(builder, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Notion response error: " + response.statusCode() + " - " + response.body());
        }

        return parseFromJson(response.body(), TypeToken.get(Page.class));
    }

    /**
     * Get a property of a page by its id.<br>
     * See <a href="https://developers.notion.com/reference/retrieve-a-page-property">API-Reference</a> for more information.
     *
     * @param pageId The id of the page
     * @param propertyId The id of the property
     * @return The {@link Property} with the given id of the page with the given id
     * @throws IOException if the request fails
     * @throws InterruptedException if the request fails
     */
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

    /**
     * List all users in the workspace.<br>
     * See <a href="https://developers.notion.com/reference/get-users">API-Reference</a> for more information.
     *
     * @return A list of all {@link User} in the workspace
     * @throws IOException if the request fails
     * @throws InterruptedException if the request fails
     */
    public List<User> listAllUsers() throws IOException, InterruptedException {
        log.info("Requesting all users");
        var builder = requestBuilder.uri(URI.create(NOTION_USERS_URL)).build();
        var response = HttpClient.newHttpClient().send(builder, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Notion response error: " + response.statusCode() + " - " + response.body());
        }
        return (List<User>) parseFromJson(response.body(), TypeToken.get(USER_LIST_TYPE));
    }

    /**
     * Get a user by its id.<br>
     * See <a href="https://developers.notion.com/reference/get-user">API-Reference</a> for more information.
     *
     * @param userId The id of the user
     * @return The {@link User} with the given id
     * @throws IOException if the request fails
     * @throws InterruptedException if the request fails
     */
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

    /**
     * Get the current user.<br>
     * See <a href="https://developers.notion.com/reference/get-self">API-Reference</a> for more information.
     *
     * @return The current {@link User}
     * @throws IOException if the request fails
     * @throws InterruptedException if the request fails
     */
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

    /**
     * Build the URI for the request.
     *
     * @param url The base URL
     * @param id The id to replace in the URL
     * @return The URI for the request
     */
    private String buildUri(@NotNull String url, @NotNull String id) {
        return url.replace("$id$", id);
    }

    /**
     * Parse a JSON string to a Java object using the {@link com.google.gson.Gson} library.
     *
     * @param json The JSON string
     * @param targetClass The target class
     * @param <T> The type of the target class
     * @return The Java object
     */
    private <T> T parseFromJson(@NotNull String json, @NotNull TypeToken<T> targetClass) {
        log.info("Trying to parse json to " + targetClass.getRawType().getSimpleName());
        var output = gson.fromJson(json, targetClass);
        log.info("Returning " + targetClass.getRawType().getSimpleName());
        return output;
    }
}
