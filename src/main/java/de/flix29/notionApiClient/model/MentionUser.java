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
public final class MentionUser extends MentionObject {
    private String id;

    public MentionUser(RichText richText) {
        super(richText);
    }

    @Override
    public MentionType getType() {
        return MentionType.USER;
    }
}
