package br.com.gotorestaurant.application.presenter.restaurant;

import br.com.gotorestaurant.application.repository.IRestaurantRepository;
import br.com.gotorestaurant.application.repository.entity.BrandEntity;
import br.com.gotorestaurant.application.repository.entity.RestaurantEntity;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.application.shared.RestaurantMapper;
import br.com.gotorestaurant.core.exceptions.RestaurantNotFoundException;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class RestaurantPresenter implements IRestaurantPresenter {

    @Autowired
    private IRestaurantRepository repository;

    @Transactional
    @Override
    public Long createRestaurant(Restaurant restaurantEntity) {
        RestaurantEntity add = RestaurantMapper.toRestaurantEntity(restaurantEntity);
        RestaurantEntity entity = this.repository.save(add);
        return entity.getUuid();
    }

    @Override
    public void updateRestaurant(Long id, Restaurant restaurantEntity) {
        Optional<RestaurantEntity> res = this.repository.findById(id);
        if (res.isPresent()) {
            this.repository.save(RestaurantMapper.toRestaurantEntity(restaurantEntity));
        }
    }

    @Override
    public Restaurant findByDocument(String document) {
        RestaurantEntity restaurantEntity = this.repository.findByDocument(document);
        if (restaurantEntity == null) throw new RestaurantNotFoundException();
        return RestaurantMapper.toRestaurant(restaurantEntity);
    }

    @Override
    public List<Restaurant> findAll() {
        List<Restaurant> restaurants = new ArrayList<>();

        Iterable<RestaurantEntity> all = this.repository.findAll();

        all.forEach(r -> restaurants.add(RestaurantMapper.toRestaurant(r)));

        return restaurants;
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
