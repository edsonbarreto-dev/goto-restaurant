package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.record.ReservationVO;
import br.com.gotorestaurant.application.repository.entity.ReservationEntity;
import br.com.gotorestaurant.core.records.Reservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class ReservationMapper {

    private ReservationMapper() {}

    public static ReservationEntity toReservationEntity(Reservation reservation) {
        if (reservation == null) return null;
        ReservationEntity entity = new ReservationEntity();
        entity.setCustomerEntity(CustomerMapper.toCustomerEntity(reservation.customer()));
        entity.setDate(reservation.date());
        entity.setBirthdaysPersonEntity(BirthdayPersonMapper.toListBirthdayPersonEntity(reservation.birthdays()));
        entity.setShowedUp(reservation.showedUp());
        entity.setHasCancelled(reservation.hasCancelled());
        entity.setNumberOfPeople(reservation.numberOfPeople());
        return entity;
    }

    public static List<ReservationEntity> toListReservationEntity(List<Reservation> reservations) {
        if (reservations == null) return null;
        List<ReservationEntity> entities = new ArrayList<>();
        for (Reservation reservation : reservations) {
            entities.add(toReservationEntity(reservation));
        }
        return entities;
    }

    public static List<Reservation> toListReservation(List<ReservationEntity> reservationEntity) {
        if (reservationEntity == null) return null;
        List<Reservation> entities = new ArrayList<>();
        for (ReservationEntity reservation : reservationEntity) {
            entities.add(toReservation(reservation));
        }
        return entities;
    }

    public static Reservation toReservation(ReservationEntity reservation) {
        if (reservation == null) return null;
        return new Reservation(
            CustomerMapper.toCustomer(reservation.getCustomerEntity()),
            reservation.getDate(),
            reservation.getNumberOfPeople(),
            reservation.isHasCancelled(),
            reservation.isShowedUp(),
            BirthdayPersonMapper.toListBirthDayPerson(reservation.getBirthdaysPersonEntity())
        );
    }

    public static Reservation toReservation(ReservationVO reservation) {
        if (reservation == null) return null;
        return new Reservation(
            reservation.customer(),
            reservation.date(),
            reservation.numberOfPeople(),
            reservation.hasCancelled(),
            reservation.showedUp(),
            reservation.birthdays()
        );
    }
}
