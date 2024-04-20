package br.com.gotorestaurant.application.repository;

import br.com.gotorestaurant.application.repository.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, UUID> {
    RestaurantEntity findByDocument(String document);
}
