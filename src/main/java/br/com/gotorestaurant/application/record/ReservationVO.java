package br.com.gotorestaurant.application.record;

import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.records.BirthdayPerson;

import java.time.LocalDate;
import java.util.List;

public record ReservationVO(
        String documentRestaurant,
        Customer customer,
        LocalDate date,
        int numberOfPeople,
        boolean hasCancelled,
        boolean showedUp,
        List<BirthdayPerson> birthdays
) {
}
