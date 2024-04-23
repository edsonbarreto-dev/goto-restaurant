package br.com.gotorestaurant.application.presenter.restaurant;

import br.com.gotorestaurant.application.repository.IRestaurantRepository;
import br.com.gotorestaurant.application.repository.entity.RestaurantEntity;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.application.shared.RestaurantMapper;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RestaurantPresenter implements IRestaurantPresenter {

    @Autowired
    private IRestaurantRepository repository;

    @Override
    public UUID createRestaurant(Restaurant restaurantEntity) {
        RestaurantEntity add = RestaurantMapper.toRestaurantEntity(restaurantEntity);
        this.repository.save(add);
        return null;
    }

    @Override
    public void updateRestaurant(UUID uuid, Restaurant restaurantEntity) {
        Optional<RestaurantEntity> res = this.repository.findById(uuid);
        if (res.isPresent()) {
            this.repository.save(RestaurantMapper.toRestaurantEntity(restaurantEntity));
        }
    }

    @Override
    public Restaurant findByDocument(String document) {
        RestaurantEntity restaurantEntity = this.repository.findByDocument(document);
        return RestaurantMapper.toRestaurant(restaurantEntity);
    }

    @Override
    public List<Restaurant> findAll() {
        return new ArrayList<>();
    }

    @Override
    public List<Restaurant> findByName(String name) {
        return new ArrayList<>();
    }

    @Override
    public List<Restaurant> findByCapacity(int capacity) {
        return new ArrayList<>();
    }

    @Override
    public List<Restaurant> findByReservationExists() {
        return new ArrayList<>();
    }
}
