package moviestreamingservice.domain.reservation.controller;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.reservation.ReservationMapper;
import moviestreamingservice.domain.reservation.ReservationRepository;
import moviestreamingservice.domain.reservation.dto.ReservationResponse;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ReservationResponse>> getAllReservations() {
        return ResponseEntity.ok(
                reservationRepository.findAll()
                        .stream()
                        .map(ReservationMapper::toResponse)
                        .toList());
    }
}
