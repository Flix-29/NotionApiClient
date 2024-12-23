package de.flix29.notionApiClient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Color {

    BLUE("blue"),
    BLUE_BACKGROUND("blue_background"),
    BROWN("brown"),
    BROWN_BACKGROUND("brown_background"),
    DEFAULT("default"),
    GRAY("gray"),
    GRAY_BACKGROUND("gray_background"),
    GREEN("green"),
    GREEN_BACKGROUND("green_background"),
    ORANGE("orange"),
    ORANGE_BACKGROUND("orange_background"),
    PINK("pink"),
    PINK_BACKGROUND("pink_background"),
    PURPLE("purple"),
    PURPLE_BACKGROUND("purple_background"),
    RED("red"),
    RED_BACKGROUND("red_background"),
    YELLOW("yellow"),
    YELLOW_BACKGROUND("yellow_background");

    private final String color;

    public static Color fromString(String color) {
        for (Color c : Color.values()) {
            if (c.color.equals(color)) {
                return c;
            }
        }
        return DEFAULT;
    }
}
