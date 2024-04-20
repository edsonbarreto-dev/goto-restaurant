package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.ReservationEntity;
import br.com.gotorestaurant.core.records.Reservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class ReservationMapper {

    private ReservationMapper() {}

    public static ReservationEntity toReservationEntity(br.com.gotorestaurant.core.records.Reservation reservation) {
        ReservationEntity entity = new ReservationEntity();
        entity.setCustomerEntity(CustomerMapper.toCustomerEntity(reservation.customer()));
        entity.setDate(reservation.date());
        entity.setBirthdaysPersonEntity(BirthdayPersonMapper.toListBirthdayPersonEntity(reservation.birthdays()));
        entity.setShowedUp(reservation.showedUp());
        entity.setHasCancelled(reservation.hasCancelled());
        entity.setNumberOfPeople(reservation.numberOfPeople());
        return entity;
    }

    public static List<ReservationEntity> toListReservationEntity(List<br.com.gotorestaurant.core.records.Reservation> reservations) {
        List<ReservationEntity> entities = new ArrayList<>();
        for (br.com.gotorestaurant.core.records.Reservation reservation : reservations) {
            entities.add(toReservationEntity(reservation));
        }
        return entities;
    }

    public static List<Reservation> toListReservation(List<ReservationEntity> reservationEntity) {
        List<Reservation> entities = new ArrayList<>();
        for (ReservationEntity reservation : reservationEntity) {
            entities.add(toReservation(reservation));
        }
        return entities;
    }

    private static Reservation toReservation(ReservationEntity reservation) {
        return new Reservation(
            CustomerMapper.toCustomer(reservation.getCustomerEntity()),
            reservation.getDate(),
            reservation.getNumberOfPeople(),
            reservation.isHasCancelled(),
            reservation.isShowedUp(),
            BirthdayPersonMapper.toListBirthDayPerson(reservation.getBirthdaysPersonEntity())
        );
    }
}
