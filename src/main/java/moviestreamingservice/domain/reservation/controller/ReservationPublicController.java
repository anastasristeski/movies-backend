package moviestreamingservice.domain.reservation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.reservation.dto.ReservationRequest;
import moviestreamingservice.domain.reservation.ReservationService;
import moviestreamingservice.domain.reservation.dto.ReservationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationPublicController {
    private final ReservationService reservationService;
    @PostMapping("/{showTimeId}")
    public ResponseEntity<ReservationResponse> createReservation(@PathVariable Long showTimeId,
                                                                 @Valid @RequestBody ReservationRequest request,
                                                                 @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationService.createReservation(
                showTimeId,
                userDetails.getUsername(),
                request.seats()
        ));
    }
    @GetMapping("/me")
    public ResponseEntity<List<ReservationResponse>> getMyReservations(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(reservationService.getUserReservations(userDetails.getUsername()));
    }
    @GetMapping("/seats/{showTimeId}")
    public ResponseEntity<List<String>> getAvailableSeats(@PathVariable Long showTimeId) {
        return ResponseEntity.ok(reservationService.getAvailableSeats(showTimeId));
    }
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long reservationId, @AuthenticationPrincipal UserDetails userDetails) {
         reservationService.cancelReservation(reservationId, userDetails.getUsername());
         return ResponseEntity.noContent().build();
    }

}
