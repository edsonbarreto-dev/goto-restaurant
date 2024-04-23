package br.com.gotorestaurant.application.controller;

import br.com.gotorestaurant.application.record.RestaurantVO;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    private IRestaurantService restaurantService;

    @GetMapping("all")
    public ResponseEntity<List<Restaurant>> listAll() {
        List<Restaurant> restaurantEntities = this.restaurantService.listAll();
        return ResponseEntity.ok().body(restaurantEntities);
    }

    @PostMapping("/create")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<UUID> create(@RequestBody @Valid RestaurantVO restaurant) {
        UUID created = this.restaurantService.create(restaurant);
        return ResponseEntity.ok(created);
    }
}
