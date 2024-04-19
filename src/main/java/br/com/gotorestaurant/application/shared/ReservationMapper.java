package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.ReservationEntityJPA;
import br.com.gotorestaurant.core.records.BirthdayPerson;
import br.com.gotorestaurant.core.records.Customer;
import br.com.gotorestaurant.core.records.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public abstract class ReservationMapper {

    private ReservationMapper() {}

    public static ReservationEntityJPA toReservationEntity(br.com.gotorestaurant.core.records.Reservation reservation) {
        ReservationEntityJPA entity = new ReservationEntityJPA();
        entity.setCustomerEntityJPA(CustomerMapper.toCustomerEntity(reservation.customer()));
        entity.setDate(reservation.date());
        entity.setBirthdays(BirthdayPersonMapper.toListBirthdayPersonEntity(reservation.birthdays()));
        entity.setShowedUp(reservation.showedUp());
        entity.setHasCancelled(reservation.hasCancelled());
        entity.setNumberOfPeople(reservation.numberOfPeople());
        return entity;
    }

    public static List<ReservationEntityJPA> toListReservationEntity(List<br.com.gotorestaurant.core.records.Reservation> reservations) {
        List<ReservationEntityJPA> entities = new ArrayList<>();
        for (br.com.gotorestaurant.core.records.Reservation reservation : reservations) {
            entities.add(toReservationEntity(reservation));
        }
        return entities;
    }

    public static List<Reservation> toListReservation(List<ReservationEntityJPA> reservationEntityJPA) {
        List<Reservation> entities = new ArrayList<>();
        for (ReservationEntityJPA reservation : reservationEntityJPA) {
            entities.add(toReservation(reservation));
        }
        return entities;
    }

    private static Reservation toReservation(ReservationEntityJPA reservation) {
        return new Reservation(
            CustomerMapper.toCustomer(reservation.getCustomerEntityJPA()),
            reservation.getDate(),
            reservation.getNumberOfPeople(),
            reservation.isHasCancelled(),
            reservation.isShowedUp(),
            BirthdayPersonMapper.toListBirthDayPerson(reservation.getBirthdays())
        );
    }
}
