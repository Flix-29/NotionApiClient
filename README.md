# NotionApiClient

This is a simple API client for <a href="https://www.notion.so">Notion.so</a>. It is based on the
official <a href="https://developers.notion.com/reference/intro">Notion API documentation</a>.

## How to use

First, you need to create a Notion integration.

1. Go to the <a href="https://www.notion.so/my-integrations" target="_blank">Notion integrations page</a>.
2. Click on the `+ New integration` button.
3. Fill in the required fields and click on the `Submit` button.
4. Copy the `Internal Integration Token` and save it in a safe place.
5. Share the integration with your workspace and add it to the Sites or Databases you want the Api to have access to.

After you have created your integration, you can import the `NotionApiClient` via maven or gradle.
### Maven
```xml
<dependency>
    <groupId>de.flix29</groupId>
    <artifactId>notion-api-client</artifactId>
    <version>0.2.0</version>
</dependency>
```

### Gradle
```groovy
implementation 'de.flix29:notion-api-client:0.2.0'
```

### Usage
You need to create a `NotionClient` object and pass the `Internal Integration Token` to the constructor.

```java
import de.flix29.notionApiClient.NotionClient;

NotionClient notionClient = new NotionClient("your-token");
```
Then, you can use the methods provided in the `NotionClient` class to interact with the Notion API.

## Supported Endpoints
Currently, only the `Get` endpoints are supported. These are the following:

### Database

- `GET` <a href="https://developers.notion.com/reference/retrieve-a-database">Retrieve a Database</a>

### Pages

- `GET` <a href="https://developers.notion.com/reference/retrieve-a-page">Retrieve a Page</a>
- `GET` <a href="https://developers.notion.com/reference/retrieve-a-page-property">Retrieve a Page property item</a>

### Blocks

- `GET` <a href="https://developers.notion.com/reference/retrieve-a-block">Retrieve a Block</a>
- `GET` <a href="https://developers.notion.com/reference/get-block-children">Retrieve a Block children</a>

### Users

- `GET` <a href="https://developers.notion.com/reference/get-users">List all Users</a>
- `GET` <a href="https://developers.notion.com/reference/get-user">Retrieve a User</a>
- `GET` <a href="https://developers.notion.com/reference/get-self">Retrieve your token's bot user</a>
