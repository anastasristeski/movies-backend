package moviestreamingservice.domain.reservation.controller;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.reservation.Reservation;
import moviestreamingservice.domain.reservation.ReservationRequest;
import moviestreamingservice.domain.reservation.ReservationService;
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
    public Reservation createReservation(@PathVariable Long showTimeId,
                                         @RequestBody ReservationRequest request,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        return reservationService.createReservation(
                showTimeId,
                userDetails.getUsername(),
                request.seats()
        );
    }
    @GetMapping("/me")
    public List<Reservation> getMyReservations(@AuthenticationPrincipal UserDetails userDetails) {
        return reservationService.getUserReservations(userDetails.getUsername());
    }
    @DeleteMapping("/{reservationId}")
    public void cancelReservation(@PathVariable Long reservationId, @AuthenticationPrincipal UserDetails userDetails) {
         reservationService.cancelReservation(reservationId, userDetails.getUsername());
    }
    @GetMapping("/seats/{showTimeId}")
    public List<String> getAvailableSeats(@PathVariable Long showTimeId) {
        return reservationService.getAvailableSeats(showTimeId);
    }
}
