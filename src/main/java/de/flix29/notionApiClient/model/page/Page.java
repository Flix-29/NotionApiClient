package de.flix29.notionApiClient.model.page;

import de.flix29.notionApiClient.model.File;
import de.flix29.notionApiClient.model.Icon;
import de.flix29.notionApiClient.model.Parent;
import de.flix29.notionApiClient.model.User;
import de.flix29.notionApiClient.model.database.databaseProperty.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Page {
    private String id;
    private OffsetDateTime createdTime;
    private OffsetDateTime lastEditedTime;
    private User createdBy;
    private User lastEditedBy;
    private boolean archived;
    private boolean deleted;
    private Icon icon;
    private File cover;
    private List<Property> properties;
    private Parent parent;
    private String url;
    private String publicUrl;
}
