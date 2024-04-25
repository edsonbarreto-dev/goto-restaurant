package br.com.gotorestaurant.application.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import br.com.gotorestaurant.application.record.CreateResponse;
import br.com.gotorestaurant.application.record.ListResponse;
import br.com.gotorestaurant.application.record.RestaurantVO;
import br.com.gotorestaurant.application.shared.RestaurantMapper;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantService;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    private IRestaurantService restaurantService;

    @GetMapping("all")
    public ResponseEntity<ListResponse<List<RestaurantVO>>> listAll() {
        List<Restaurant> restaurants = this.restaurantService.listAll();
        List<RestaurantVO> restaurantsVO = RestaurantMapper.toLitRestaurantVO(restaurants);
        return ResponseEntity.ok(new ListResponse<>(restaurantsVO, "Restaurant list successfully"));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<RestaurantVO> findByDocument(@PathVariable Long id) {
        Restaurant restaurant = this.restaurantService.findBy(id);
        RestaurantVO restaurantVO = RestaurantMapper.toRestaurantVO(restaurant);
        return ResponseEntity.ok(restaurantVO);
    }

    @GetMapping("/find/document/{document}")
    public ResponseEntity<RestaurantVO> findByDocument(@PathVariable String document) {
        Restaurant restaurant = this.restaurantService.findByDocument(document);
        RestaurantVO restaurantVO = RestaurantMapper.toRestaurantVO(restaurant);
        return ResponseEntity.ok(restaurantVO);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateResponse<Long>> create(@RequestBody @Valid RestaurantVO restaurantVO) {
        Restaurant restaurant = RestaurantMapper.toRestaurant(restaurantVO);
        Long id = this.restaurantService.create(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            new CreateResponse<>(id, "Success Create Restaurant")
        );
    }
}
