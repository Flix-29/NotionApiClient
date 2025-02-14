package de.flix29.notionApiClient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public sealed class RichText permits Equation, Text, MentionObject {
    private Annotations annotations;
    private String plainText;
    private String href;
}
