package de.flix29.notionApiClient.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.Map;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public final class MentionTemplate extends MentionObject {
    private String date;
    private String user;

    public MentionTemplate(RichText richText) {
        super(richText);
    }

    @Override
    public MentionType getType() {
        return MentionType.TEMPLATE_MENTION;
    }

    public Map<String, String> getPresentType() {
        if (date != null) {
            return Map.of("date", date);
        } else if (user != null) {
            return Map.of("user", user);
        } else {
            return Collections.emptyMap();
        }
    }
}
