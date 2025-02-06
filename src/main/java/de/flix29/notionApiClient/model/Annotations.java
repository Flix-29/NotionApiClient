package de.flix29.notionApiClient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Annotations {
    private boolean bold;
    private boolean italic;
    private boolean strikethrough;
    private boolean underline;
    private boolean code;
    private Color color;
    private Color backgroundColor;

    public Annotations bold(boolean bold) {
        this.bold = bold;
        return this;
    }

    public Annotations italic(boolean italic) {
        this.italic = italic;
        return this;
    }

    public Annotations strikethrough(boolean strikethrough) {
        this.strikethrough = strikethrough;
        return this;
    }

    public Annotations underline(boolean underline) {
        this.underline = underline;
        return this;
    }

    public Annotations code(boolean code) {
        this.code = code;
        return this;
    }

    public Annotations color(String color) {
        if (color != null && color.contains("background")) {
            this.backgroundColor = Color.fromString(color);
        } else {
            this.color = Color.fromString(color);
        }
        return this;
    }
}
