# NotionApiClient

This is a simple API client for <a href="https://www.notion.so">Notion.so</a>. It is based on the official <a href="https://developers.notion.com/reference/intro">Notion API documentation</a>.

## How to use
Just add a file called 'application.properties' in the resources folder with the following content:
```properties
notion.apikey={your_api-key}
```
You can get your API key from the Notion integrations page. For more details, check the <a href="https://developers.notion.com/docs/authorization">official documentation</a>.

Then you can use the NotionApiClient and the methods provided in the NotionClient class to interact with the Notion API.
