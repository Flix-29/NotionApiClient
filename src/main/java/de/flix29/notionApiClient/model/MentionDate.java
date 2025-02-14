package de.flix29.notionApiClient.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public final class MentionDate extends MentionObject {
    private OffsetDateTime start;
    private OffsetDateTime end;
    private String timeZone;

    public MentionDate(RichText richText) {
        super(richText);
    }

    @Override
    public MentionType getType() {
        return MentionType.DATE;
    }
}
