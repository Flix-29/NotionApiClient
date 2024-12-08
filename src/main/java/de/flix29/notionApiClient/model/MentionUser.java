package de.flix29.notionApiClient.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class MentionUser extends MentionObject {
    private String id;

    public MentionUser(RichText richText) {
        super(richText);
    }

    public MentionUser id(String id) {
        this.id = id;
        return this;
    }

    @Override
    public MentionType getType() {
        return MentionType.USER;
    }
}
