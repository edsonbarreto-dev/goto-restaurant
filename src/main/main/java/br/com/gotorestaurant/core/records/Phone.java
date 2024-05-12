package br.com.gotorestaurant.core.records;

import br.com.gotorestaurant.core.enums.CountryCodeEnum;

public record Phone(
    CountryCodeEnum countryCode,
    String codeArea,
    Long number
) {}
