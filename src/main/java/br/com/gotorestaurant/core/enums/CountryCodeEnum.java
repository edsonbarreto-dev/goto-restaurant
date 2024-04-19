package br.com.gotorestaurant.core.enums;

public enum CountryCodeEnum {
    BRAZIL("55"),
    AFGHANISTAN("93"),
    ALBANIAN("355"),
    ALGERIA("213"),
    AMERICAN_SAMOA("1-1684"),
    ANDORRA("376"),
    ANGOLA("244"),
    ANGUILLA("1-1264"),
    ANTARCTICA("672"),
    AUSTRALIA("61"),
    AUSTRIA("43"),
    CANADA("1"),
    CHINA("86"),
    COLOMBIA("57"),
    EL_SALVADOR("503"),
    ISRAEL("972"),
    ITALY("39"),
    JAPAN("81");

    public String countryCode;
    CountryCodeEnum(String s) {
        countryCode = s;
    }

    //Source of information: https://countrycode.org
}
