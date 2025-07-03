package de.flix29.notionApiClient.customDeserializer;

import com.google.gson.reflect.TypeToken;
import de.flix29.notionApiClient.model.RichText;
import de.flix29.notionApiClient.model.User;
import de.flix29.notionApiClient.model.block.Block;
import de.flix29.notionApiClient.model.block.blockContent.BlockContent;
import de.flix29.notionApiClient.model.database.databaseProperty.Property;

import java.lang.reflect.Type;
import java.util.List;

public class CustomModelTypes {

    public static final Type PROPERTY_LIST_TYPE = new TypeToken<List<Property>>(){}.getType();
    public static final Type RICH_TEXT_LIST_TYPE = new TypeToken<List<RichText>>(){}.getType();
    public static final Type USER_LIST_TYPE = new TypeToken<List<User>>(){}.getType();
    public static final Type BLOCK_LIST_TYPE = new TypeToken<List<Block>>(){}.getType();
    public static final Type BLOCK_CONTENT_LIST_TYPE = new TypeToken<List<BlockContent>>(){}.getType();

    private CustomModelTypes() {
    }
}
