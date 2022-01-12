package com.tenniscourts.reservations;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
@Api("Rest API reservation tennis court")
@CrossOrigin(value = "*")
public class ReservationController extends BaseRestController {

    private final ReservationService reservationService;

    @PostMapping
    @ApiOperation(value = "Save a new reservation")
    public ResponseEntity<Void> bookReservation(@RequestBody CreateReservationRequestDTO createReservationRequestDTO) {
        return ResponseEntity.created(locationByEntity(reservationService.bookReservation(createReservationRequestDTO).getId())).build();
    }

    public ResponseEntity<ReservationDTO> findReservation(Long reservationId) {
        return ResponseEntity.ok(reservationService.findReservation(reservationId));
    }

    public ResponseEntity<ReservationDTO> cancelReservation(Long reservationId) {
        return ResponseEntity.ok(reservationService.cancelReservation(reservationId));
    }

    @PostMapping("/reschedule")
    public ResponseEntity<ReservationDTO> rescheduleReservation(@RequestBody RescheduleReservationDTO rescheduleReservationDTO) {
        return ResponseEntity.ok(reservationService.rescheduleReservation(rescheduleReservationDTO));
    }
}
