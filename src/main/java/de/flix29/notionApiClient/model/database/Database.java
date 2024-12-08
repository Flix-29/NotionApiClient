package de.flix29.notionApiClient.model.database;

import de.flix29.notionApiClient.model.*;
import de.flix29.notionApiClient.model.database.databaseProperty.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Database id(UUID id) {
        this.id = id;
        return this;
    }

    public Database createdTime(OffsetDateTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public Database createdBy(User createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Database lastEditedTime(OffsetDateTime lastEditedTime) {
        this.lastEditedTime = lastEditedTime;
        return this;
    }

    public Database lastEditedBy(User lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
        return this;
    }

    public Database titel(List<RichText> titel) {
        this.titel = titel;
        return this;
    }

    public Database description(List<RichText> description) {
        this.description = description;
        return this;
    }

    public Database icon(Icon icon) {
        this.icon = icon;
        return this;
    }

    public Database cover(File cover) {
        this.cover = cover;
        return this;
    }

    public Database properties(List<Property> properties) {
        this.properties = properties;
        return this;
    }

    public Database parent(Parent parent) {
        this.parent = parent;
        return this;
    }

    public Database url(String url) {
        this.url = url;
        return this;
    }

    public Database archived(boolean archived) {
        this.archived = archived;
        return this;
    }

    public Database deleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public Database inline(boolean inline) {
        this.inline = inline;
        return this;
    }

    public Database publicUrl(String publicUrl) {
        this.publicUrl = publicUrl;
        return this;
    }
}