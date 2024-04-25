package br.com.gotorestaurant.application.controller;

import br.com.gotorestaurant.application.record.CreateResponse;
import br.com.gotorestaurant.application.record.ListResponse;
import br.com.gotorestaurant.application.record.ReservationVO;
import br.com.gotorestaurant.application.record.RestaurantVO;
import br.com.gotorestaurant.application.repository.entity.ReservationEntity;
import br.com.gotorestaurant.application.shared.ReservationMapper;
import br.com.gotorestaurant.application.shared.RestaurantMapper;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.records.Reservation;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@Tag(name = "Reservation")
public class ReservationController {

    @Autowired
    private IRestaurantService restaurantService;

    @PostMapping("/make")
    @Operation(summary = "This method is used to make restaurant reservation.")
    public ResponseEntity<CreateResponse<Boolean>> create(@RequestBody @Valid ReservationVO reservationVO, Long restaurantId) {
        Reservation reservation = ReservationMapper.toReservation(reservationVO);
        this.restaurantService.makeReservation(reservation, restaurantId);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            new CreateResponse<>(Boolean.TRUE, "Reservation created successfully")
        );
    }
}
