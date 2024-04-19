package br.com.gotorestaurant.application.presenter.restaurant;

import br.com.gotorestaurant.application.repository.IRestaurantRepository;
import br.com.gotorestaurant.application.repository.entity.RestaurantEntityJPA;
import br.com.gotorestaurant.core.entity.RestaurantEntity;
import br.com.gotorestaurant.application.shared.RestaurantMapper;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RestaurantPresenter implements IRestaurantPresenter {

    @Autowired
    private IRestaurantRepository restaurantRepository;

    @Override
    public UUID createRestaurant(RestaurantEntity restaurantEntity) {
        this.restaurantRepository.save(RestaurantMapper.toRestaurantEntityJPA(restaurantEntity));
        return null;
    }

    @Override
    public void updateRestaurant(UUID uuid, RestaurantEntity restaurantEntity) {
        Optional<RestaurantEntityJPA> res = this.restaurantRepository.findById(uuid);
        if (res.isPresent()) {
            this.restaurantRepository.save(RestaurantMapper.toRestaurantEntityJPA(restaurantEntity));
        }
    }

    @Override
    public RestaurantEntity findByDocument(String document) {
        RestaurantEntityJPA restaurantEntityJPA = this.restaurantRepository.findByDocument(document);
        return RestaurantMapper.toRestaurantEntity(restaurantEntityJPA);
    }

    @Override
    public List<RestaurantEntity> findAll() {
        return List.of();
    }

    @Override
    public List<RestaurantEntity> findByName(String name) {
        return List.of();
    }

    @Override
    public List<RestaurantEntity> findByCapacity(int capacity) {
        return List.of();
    }

    @Override
    public List<RestaurantEntity> findByReservationExists() {
        return List.of();
    }
}
