package br.com.gotorestaurant.application.repository;

import br.com.gotorestaurant.application.repository.entity.RestaurantEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IRestaurantRepository extends CrudRepository<RestaurantEntity, Long> {
    Optional<RestaurantEntity> findByDocument(@NotNull String document);

    @Transactional
    @Modifying
    @Query("update Restaurant r set r = :restaurant")
    void updateRestaurant(@NotNull RestaurantEntity restaurant);

    @Transactional
    @Modifying
    @Query("update Reservation r set r.restaurantEntity = :restaurant")
    void makeReservation(@NotNull RestaurantEntity restaurant);
}
