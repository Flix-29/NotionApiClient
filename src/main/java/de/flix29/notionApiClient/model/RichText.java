package de.flix29.notionApiClient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public sealed class RichText permits Equation, Text, MentionObject {
    private Annotations annotations;
    private String plainText;
    private String href;

    public RichText annotations(Annotations annotations) {
        this.annotations = annotations;
        return this;
    }

    public RichText plainText(String plainText) {
        this.plainText = plainText;
        return this;
    }

    public RichText href(String href) {
        this.href = href;
        return this;
    }
}
