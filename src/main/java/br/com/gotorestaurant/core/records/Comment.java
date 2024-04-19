package br.com.gotorestaurant.core.records;

public record Comment(
    String message,
    Customer customer
) {}
