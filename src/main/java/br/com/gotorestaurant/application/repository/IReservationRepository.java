package br.com.gotorestaurant.application.repository;

import br.com.gotorestaurant.application.repository.entity.ReservationEntity;
import br.com.gotorestaurant.application.repository.entity.RestaurantEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IReservationRepository extends CrudRepository<ReservationEntity, Long> {
    Optional<ReservationEntity> findByDate(@NotNull LocalDate date);

    @Transactional
    @Modifying
    @Query("update Reservation r set r.restaurantEntity = :restaurant")
    void makeReservation(@NotNull RestaurantEntity restaurant);
}
