package br.com.gotorestaurant.core.records;

public record Address(
    String publicPlace,
    String number,
    String neighborhood,
    String city,
    String state,
    String country,
    String zipCode
) {}
