package br.com.gotorestaurant.application.controller;

import br.com.gotorestaurant.application.record.CustomerVO;
import br.com.gotorestaurant.application.record.UpdateResponse;
import br.com.gotorestaurant.application.shared.CustomerMapper;
import br.com.gotorestaurant.core.records.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import br.com.gotorestaurant.application.record.CreateResponse;
import br.com.gotorestaurant.application.record.RestaurantVO;
import br.com.gotorestaurant.application.shared.RestaurantMapper;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantService;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    private IRestaurantService restaurantService;

    @GetMapping("/find/document/{document}")
    public ResponseEntity<RestaurantVO> findByDocument(@PathVariable String document) {
        Restaurant restaurant = this.restaurantService.findByDocument(document);
        RestaurantVO restaurantVO = RestaurantMapper.toRestaurantVO(restaurant);
        return ResponseEntity.ok(restaurantVO);
    }

    @PostMapping
    public ResponseEntity<CreateResponse<Long>> create(@RequestBody @Valid RestaurantVO restaurantVO) {
        Restaurant restaurant = RestaurantMapper.toRestaurant(restaurantVO);
        Long id = this.restaurantService.create(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            new CreateResponse<>(id, "Success Create Restaurant")
        );
    }

    @PutMapping("/{document}/update/customer")
    public ResponseEntity<UpdateResponse<Boolean>> updateCustomer(@RequestBody CustomerVO customerVO, @PathVariable String document) {
        Customer customer = CustomerMapper.toCustomerRecord(customerVO);
        this.restaurantService.updateCustomer(customer, document);
        return ResponseEntity.ok().body(
            new UpdateResponse<>(true, "Success Update Customer Restaurant")
        );
    }
}
