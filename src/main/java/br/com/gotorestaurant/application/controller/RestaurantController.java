package br.com.gotorestaurant.application.controller;

import br.com.gotorestaurant.application.record.RestaurantVO;
import br.com.gotorestaurant.core.entity.RestaurantEntity;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController("restaurant")
public class RestaurantController {

    @Autowired
    private IRestaurantService restaurantService;

    @GetMapping("all")
    public ResponseEntity<List<RestaurantEntity>> listAll() {
        List<RestaurantEntity> restaurantEntities = this.restaurantService.listAll();
        return ResponseEntity.ok().body(restaurantEntities);
    }

    @PostMapping("create")
    public ResponseEntity<UUID> create(@RequestBody RestaurantVO restaurant) {
        UUID created = this.restaurantService.create(restaurant);
        return ResponseEntity.ok(created);
    }
}
