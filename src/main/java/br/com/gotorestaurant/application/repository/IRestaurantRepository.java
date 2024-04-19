package br.com.gotorestaurant.application.repository;

import br.com.gotorestaurant.application.repository.entity.RestaurantEntityJPA;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IRestaurantRepository extends CrudRepository<RestaurantEntityJPA, UUID> {
    RestaurantEntityJPA findByDocument(String document);
    void updateBy(RestaurantEntityJPA restaurant);
}
