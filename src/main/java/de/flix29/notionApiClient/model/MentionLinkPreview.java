package de.flix29.notionApiClient.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class MentionLinkPreview extends MentionObject {
    private String href;
    private String title;
    private String iconUrl;
    private String description;
    private String linkProvider;
    private String thumbnailUrl;

    public MentionLinkPreview(RichText richText) {
        super(richText);
    }

    public MentionLinkPreview href(String href) {
        this.href = href;
        return this;
    }

    public MentionLinkPreview title(String title) {
        this.title = title;
        return this;
    }

    public MentionLinkPreview iconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    public MentionLinkPreview description(String description) {
        this.description = description;
        return this;
    }

    public MentionLinkPreview linkProvider(String linkProvider) {
        this.linkProvider = linkProvider;
        return this;
    }

    public MentionLinkPreview thumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
        return this;
    }

    @Override
    public MentionType getType() {
        return MentionType.LINK_PREVIEW;
    }
}
