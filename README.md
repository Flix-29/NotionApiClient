# NotionApiClient

This is a simple API client for <a href="https://www.notion.so">Notion.so</a>. It is based on the
official <a href="https://developers.notion.com/reference/intro">Notion API documentation</a>.

## How to use

Just change the file called 'application.template' to 'application.properties' in the resources folder and add your API key.

```properties
notion.apikey={your_api-key}
```

You can get your API key from the Notion integrations page. For more details, check
the <a href="https://developers.notion.com/docs/authorization">official documentation</a>.

Then you can use the NotionApiClient and the methods provided in the NotionClient class to interact with the Notion API.

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
