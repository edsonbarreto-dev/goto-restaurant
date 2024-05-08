package br.com.gotorestaurant.application.record;

import br.com.gotorestaurant.core.enums.CountryCodeEnum;

public record PhoneVO(
    CountryCodeEnum countryCode,
    String codeArea,
    Long number
) {
}
