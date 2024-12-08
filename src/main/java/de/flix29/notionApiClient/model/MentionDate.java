package de.flix29.notionApiClient.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class MentionDate extends MentionObject {
    private OffsetDateTime start;
    private OffsetDateTime end;
    private String timeZone;

    public MentionDate(RichText richText) {
        super(richText);
    }

    public MentionDate start(OffsetDateTime start) {
        this.start = start;
        return this;
    }

    public MentionDate end(OffsetDateTime end) {
        this.end = end;
        return this;
    }

    public MentionDate timeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    @Override
    public MentionType getType() {
        return MentionType.DATE;
    }
}
