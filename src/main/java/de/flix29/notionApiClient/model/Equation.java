package de.flix29.notionApiClient.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class Equation extends RichText {

    private String equation;

    public Equation(RichText richText) {
        super(richText.getAnnotations(), richText.getPlainText(), richText.getHref());
    }

    public Equation equation(String equation) {
        this.equation = equation;
        return this;
    }
}
