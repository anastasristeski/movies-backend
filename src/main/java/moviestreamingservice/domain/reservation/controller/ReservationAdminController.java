package moviestreamingservice.domain.reservation.controller;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.reservation.Reservation;
import moviestreamingservice.domain.reservation.ReservationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/reservation")
public class ReservationAdminController {
    private final ReservationRepository reservationRepository;
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}
