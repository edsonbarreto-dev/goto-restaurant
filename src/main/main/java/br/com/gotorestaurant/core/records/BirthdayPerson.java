package br.com.gotorestaurant.core.records;

import br.com.gotorestaurant.core.enums.GenderEnum;

public record BirthdayPerson(
    String name,
    int age,
    int birthday,
    int birthdayMonth,
    GenderEnum gender,
    boolean wishHouseCelebration
) {}
