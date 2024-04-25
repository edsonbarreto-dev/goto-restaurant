package br.com.gotorestaurant.application.record;

import br.com.gotorestaurant.core.records.*;

import java.time.LocalDate;
import java.util.List;

public record ReservationVO(
        Customer customer,
        LocalDate date,
        int numberOfPeople,
        boolean hasCancelled,
        boolean showedUp,
        List<BirthdayPerson> birthdays
) {
}
