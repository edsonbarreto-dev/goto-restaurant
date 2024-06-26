package br.com.gotorestaurant.core.records;

import java.util.List;

public record Customer(
    String name,
    String email,
    String document,
    List<SocialMedia> socialMedia,
    List<Phone> phones
) {}
