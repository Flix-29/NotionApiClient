package de.flix29.notionApiClient.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract sealed class MentionObject extends RichText
        permits MentionDatabase, MentionDate, MentionLinkPreview, MentionPage, MentionTemplate, MentionUser {

    public MentionObject(RichText richText) {
        super(richText.getAnnotations(), richText.getPlainText(), richText.getHref());
    }

    public abstract MentionType getType();
}