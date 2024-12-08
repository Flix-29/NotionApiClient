package de.flix29.notionApiClient.model.page;

import de.flix29.notionApiClient.model.File;
import de.flix29.notionApiClient.model.Icon;
import de.flix29.notionApiClient.model.Parent;
import de.flix29.notionApiClient.model.User;
import de.flix29.notionApiClient.model.database.databaseProperty.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Page id(String id) {
        this.id = id;
        return this;
    }

    public Page createdTime(OffsetDateTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public Page lastEditedTime(OffsetDateTime lastEditedTime) {
        this.lastEditedTime = lastEditedTime;
        return this;
    }

    public Page createdBy(User createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Page lastEditedBy(User lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
        return this;
    }

    public Page archived(boolean archived) {
        this.archived = archived;
        return this;
    }

    public Page deleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public Page icon(Icon icon) {
        this.icon = icon;
        return this;
    }

    public Page cover(File cover) {
        this.cover = cover;
        return this;
    }

    public Page properties(List<Property> properties) {
        this.properties = properties;
        return this;
    }

    public Page parent(Parent parent) {
        this.parent = parent;
        return this;
    }

    public Page url(String url) {
        this.url = url;
        return this;
    }

    public Page publicUrl(String publicUrl) {
        this.publicUrl = publicUrl;
        return this;
    }
}
