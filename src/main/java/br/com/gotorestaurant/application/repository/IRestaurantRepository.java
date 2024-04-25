package br.com.gotorestaurant.application.repository;

import br.com.gotorestaurant.application.repository.entity.RestaurantEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IRestaurantRepository extends CrudRepository<RestaurantEntity, Long> {
    RestaurantEntity findByDocument(@NotNull String document);
}
