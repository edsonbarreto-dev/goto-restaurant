package br.com.gotorestaurant.utils;

import br.com.gotorestaurant.application.repository.entity.ReservationEntity;
import br.com.gotorestaurant.core.records.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class ReservationHelper {

    public static ReservationEntity registrarReserva() {
        return ReservationEntity.builder()
                .id(geradorId())
                .date(LocalDate.now())
                .hasCancelled(false)
                .numberOfPeople(10)
                .showedUp(true)
                .reservedTableNumber(1)
                .customerEntity(CustomerHelper.registerCustomer())
                .restaurantEntity(RestaurantHelper.registerRestaurant())
                .build();
    }

    public static Reservation addReserva() {
        Reservation reserve = new Reservation(CustomerHelper.addCustomer(),LocalDate.now(),
                10, 2,false, false, null);
        return reserve;
    }

    public static Optional<ReservationEntity> registerReserve(){
        return Optional.ofNullable(registrarReserva());
    }
    public static List<ReservationEntity> registrarReservas() {
        var reserva = registrarReserva();
        List<ReservationEntity> lista = List.of(reserva);
        return lista;

    }

    public static Long geradorId() {
        Random r = new Random();
        return r.nextLong();
    }
}
