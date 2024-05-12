package br.com.gotorestaurant.core.records;

import br.com.gotorestaurant.core.enums.WorkFunctionEnum;

import java.util.List;

public record Employee(
    String name,
    String email,
    String document,
    String workSchedule,
    WorkFunctionEnum workFunction,
    List<SocialMedia> socialMedia,
    List<Phone> phones
) {}