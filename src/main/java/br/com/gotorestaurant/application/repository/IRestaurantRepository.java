package br.com.gotorestaurant.application.repository;

import br.com.gotorestaurant.application.repository.entity.RestaurantEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface IRestaurantRepository extends CrudRepository<RestaurantEntity, Long> {
    RestaurantEntity findByDocument(String document);
}
