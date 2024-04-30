package br.com.gotorestaurant.core.records;

import br.com.gotorestaurant.core.entity.Customer;

import java.time.LocalDate;
import java.util.List;

public record Reservation(
    Customer customer,
    LocalDate date,
    int numberOfPeople,
    boolean hasCancelled,
    boolean showedUp,
    List<BirthdayPerson> birthdays
) {}
