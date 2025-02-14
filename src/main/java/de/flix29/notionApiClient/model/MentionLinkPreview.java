package de.flix29.notionApiClient.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
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

    @Override
    public MentionType getType() {
        return MentionType.LINK_PREVIEW;
    }
}
