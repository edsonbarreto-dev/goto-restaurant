package br.com.gotorestaurant.application.presenter.restaurant;

import br.com.gotorestaurant.application.repository.IRestaurantRepository;
import br.com.gotorestaurant.application.repository.entity.RestaurantEntity;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.application.shared.RestaurantMapper;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantPresenter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RestaurantPresenter implements IRestaurantPresenter {

    private IRestaurantRepository restaurantRepository;

    @Override
    public UUID createRestaurant(Restaurant restaurantEntity) {
        this.restaurantRepository.save(RestaurantMapper.toRestaurantEntityJPA(restaurantEntity));
        return null;
    }

    @Override
    public void updateRestaurant(UUID uuid, Restaurant restaurantEntity) {
        Optional<RestaurantEntity> res = this.restaurantRepository.findById(uuid);
        if (res.isPresent()) {
            this.restaurantRepository.save(RestaurantMapper.toRestaurantEntityJPA(restaurantEntity));
        }
    }

    @Override
    public Restaurant findByDocument(String document) {
        RestaurantEntity restaurantEntity = this.restaurantRepository.findByDocument(document);
        return RestaurantMapper.toRestaurantEntity(restaurantEntity);
    }

    @Override
    public List<Restaurant> findAll() {
        return List.of();
    }

    @Override
    public List<Restaurant> findByName(String name) {
        return List.of();
    }

    @Override
    public List<Restaurant> findByCapacity(int capacity) {
        return List.of();
    }

    @Override
    public List<Restaurant> findByReservationExists() {
        return List.of();
    }
}
