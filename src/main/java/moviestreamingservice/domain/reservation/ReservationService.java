package moviestreamingservice.domain.reservation;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.reservation.dto.ReservationResponse;
import moviestreamingservice.domain.showtime.ShowTime;
import moviestreamingservice.domain.showtime.ShowtimeRepository;
import moviestreamingservice.domain.user.User;
import moviestreamingservice.domain.user.UserRepository;
import moviestreamingservice.exception.BadRequestException;
import moviestreamingservice.exception.NotFoundException;
import moviestreamingservice.utilities.SeatUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ShowtimeRepository showTimeRepository;
    private final UserRepository userRepository;

    public ReservationResponse createReservation(Long showTimeId, String email, List<String> seats) {

        ShowTime showTime = showTimeRepository.findById(showTimeId)
                .orElseThrow(() -> new NotFoundException("Showtime not found"));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));

        List<String> hallSeats = showTime.getHall().getSeats();

        for (String seat : seats) {
            if (!SeatUtil.seatExists(seat, hallSeats)) {
                throw new BadRequestException("Invalid seat: " + seat);
            }
            boolean taken = reservationRepository
                    .existsByShowTime_IdAndSeatsContaining(showTimeId, seat);
            if (taken) {
                throw new BadRequestException("Seat already reserved: " + seat);
            }
        }
        Reservation reservation = Reservation.builder()
                .user(user)
                .showTime(showTime)
                .seats(seats)
                .status(ReservationStatus.UPCOMING)
                .build();

        return ReservationMapper.toResponse(reservationRepository.save(reservation));
    }

    public List<ReservationResponse> getUserReservations(String email) {
        return reservationRepository.findByUser_Email(email)
                .stream()
                .map(ReservationMapper::toResponse)
                .toList();
    }
    public void cancelReservation(Long reservationId, String email) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("Reservation not found"));

        if (!reservation.getUser().getEmail().equals(email)) {
            throw new BadRequestException("Not your reservation");
        }

        if (reservation.getStatus() != ReservationStatus.UPCOMING) {
            throw new RuntimeException("Only upcoming reservations can be cancelled");
        }

        reservation.setStatus(ReservationStatus.CANCELED);
        reservationRepository.save(reservation);
    }
    public List<String> getAvailableSeats(Long showTimeId) {

        ShowTime showTime = showTimeRepository.findById(showTimeId)
                .orElseThrow(() -> new NotFoundException("Showtime not found"));

        List<String> allSeats = showTime.getHall().getSeats();
        List<String> reserved = reservationRepository.findSeatsByShowTimeId(showTimeId);

        return allSeats.stream()
                .filter(seat -> !reserved.contains(seat))
                .toList();
    }
}
