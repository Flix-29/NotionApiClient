package de.flix29.notionApiClient.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class MentionPage extends MentionObject {
    private String id;

    public MentionPage(RichText richText) {
        super(richText);
    }

    public MentionPage id(String id) {
        this.id = id;
        return this;
    }

    @Override
    public MentionType getType() {
        return MentionType.PAGE;
    }
}
