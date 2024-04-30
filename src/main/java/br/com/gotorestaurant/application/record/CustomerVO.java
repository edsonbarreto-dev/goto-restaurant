package br.com.gotorestaurant.application.record;

public record CustomerVO(
        String document,
        String name,
        String email
) {
}
