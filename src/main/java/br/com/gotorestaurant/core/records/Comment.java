package br.com.gotorestaurant.core.records;

import br.com.gotorestaurant.core.entity.Customer;

public record Comment(
    String message,
    Customer customer
) {}
