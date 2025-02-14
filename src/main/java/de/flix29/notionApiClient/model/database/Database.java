package de.flix29.notionApiClient.model.database;

import de.flix29.notionApiClient.model.*;
import de.flix29.notionApiClient.model.database.databaseProperty.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Database {
    private UUID id;
    private OffsetDateTime createdTime;
    private User createdBy;
    private OffsetDateTime lastEditedTime;
    private User lastEditedBy;
    private List<RichText> titel;
    private List<RichText> description;
    private Icon icon;
    private File cover;
    private List<Property> properties;
    private Parent parent;
    private String url;
    private boolean archived;
    private boolean deleted;
    private boolean inline;
    private String publicUrl;
}