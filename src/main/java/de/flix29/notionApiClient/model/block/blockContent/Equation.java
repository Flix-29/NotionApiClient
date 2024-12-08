package de.flix29.notionApiClient.model.block.blockContent;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public final class Equation implements BlockContent {
    private String expression;

    public Equation expression(String expression) {
        this.expression = expression;
        return this;
    }
}
