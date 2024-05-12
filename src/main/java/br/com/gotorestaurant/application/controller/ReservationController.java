package br.com.gotorestaurant.application.controller;

import br.com.gotorestaurant.application.record.CreateResponse;
import br.com.gotorestaurant.application.record.ReservationVO;
import br.com.gotorestaurant.application.shared.ReservationMapper;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservation")
@Tag(name = "Reservation")
public class ReservationController {

    @Autowired
    private IReservationService reservationService;

    @PostMapping
    @Operation(summary = "This method is used to make restaurant reservation.")
    public ResponseEntity<CreateResponse<Boolean>> make(@RequestBody @Valid ReservationVO reservationVO) {
        this.reservationService.makeReservation(
            ReservationMapper.toReservation(reservationVO), reservationVO.documentRestaurant()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(
            new CreateResponse<>(Boolean.TRUE, "Reservation created successfully")
        );
    }


    @GetMapping
    @Operation(summary = "This method is used to list restaurant reservations.")
    public ResponseEntity<ListResponse<List<Reservation>>> findAll() {
        List<Reservation> reservations = this.reservationService.reservations();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ListResponse<>(reservations, "Reservations listed successfully")
        );
    }
}
