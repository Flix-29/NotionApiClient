package de.flix29.notionApiClient.model.database.databaseProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NumberFormat {

    ARGENTINE_PESO("argentine_peso"),
    BAHT("baht"),
    AUSTRALIAN_DOLLAR("australian_dollar"),
    CANADIAN_DOLLAR("canadian_dollar"),
    CHILEAN_PESO("chilean_peso"),
    COLOMBIAN_PESO("colombian_peso"),
    DANISH_KRONE("danish_krone"),
    DIRHAM("dirham"),
    DOLLAR("dollar"),
    EURO("euro"),
    FORINT("forint"),
    FRANC("franc"),
    HONG_KONG_DOLLAR("hong_kong_dollar"),
    KORUNA("koruna"),
    KRONA("krona"),
    LEU("leu"),
    LIRA("lira"),
    MEXICAN_PESO("mexican_peso"),
    NEW_TAIWAN_DOLLAR("new_taiwan_dollar"),
    NEW_ZEALAND_DOLLAR("new_zealand_dollar"),
    NORWEGIAN_KRONE("norwegian_krone"),
    NUMBER("number"),
    NUMBER_WITH_COMMAS("number_with_commas"),
    PERCENT("percent"),
    PHILIPPINE_PESO("philippine_peso"),
    POUND("pound"),
    PERUVIAN_SOL("peruvian_sol"),
    RAND("rand"),
    REAL("real"),
    RINGGIT("ringgit"),
    RIYAL("riyal"),
    RUBLE("ruble"),
    RUPEE("rupee"),
    RUPIAH("rupiah"),
    SHEKEL("shekel"),
    SINGAPORE_DOLLAR("singapore_dollar"),
    URUGUAYAN_PESO("uruguayan_peso"),
    YEN("yen"),
    YUAN("yuan"),
    WON("won"),
    ZLOTY("zloty");

    private final String format;

    public static NumberFormat fromString(String format) {
        for (NumberFormat numberFormat : values()) {
            if (numberFormat.format.equals(format)) {
                return numberFormat;
            }
        }
        throw new IllegalArgumentException("Unknown NumberFormat: " + format);
    }
}
