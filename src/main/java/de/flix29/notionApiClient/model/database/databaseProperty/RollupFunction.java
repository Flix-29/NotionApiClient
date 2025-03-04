package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RollupFunction {

    AVERAGE("average"),
    CHECKED("checked"),
    COUNT_PER_GROUP("count_per_group"),
    COUNT("count"),
    COUNT_VALUES("count_values"),
    DATE_RANGE("date_range"),
    EARLIEST_DATE("earliest_date"),
    EMPTY("empty"),
    LATEST_DATE("latest_date"),
    MAX("max"),
    MEDIAN("median"),
    MIN("min"),
    NOT_EMPTY("not_empty"),
    PERCENT_CHECKED("percent_checked"),
    PERCENT_EMPTY("percent_empty"),
    PERCENT_NOT_EMPTY("percent_not_empty"),
    PERCENT_PER_GROUP("percent_per_group"),
    PERCENT_UNCHECKED("percent_unchecked"),
    RANGE("range"),
    UNCHECKED("unchecked"),
    UNIQUE("unique"),
    SHOW_ORIGINAL("show_original"),
    SHOW_UNIQUE("show_unique"),
    SUM("sum");

    private final String function;

    public static RollupFunction fromString(String function) {
        for (RollupFunction f : RollupFunction.values()) {
            if (f.function.equals(function)) {
                return f;
            }
        }
        return null;
    }
}
