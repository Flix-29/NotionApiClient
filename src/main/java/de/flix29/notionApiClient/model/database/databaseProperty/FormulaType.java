package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FormulaType {

    STRING("string"),
    NUMBER("number"),
    BOOLEAN("boolean"),
    DATE("date");

    private final String type;

    public static FormulaType fromString(String type) {
        for (FormulaType formulaType : FormulaType.values()) {
            if (formulaType.type.equalsIgnoreCase(type)) {
                return formulaType;
            }
        }
        throw new IllegalArgumentException("No formula type with name " + type + " found");
    }
}
