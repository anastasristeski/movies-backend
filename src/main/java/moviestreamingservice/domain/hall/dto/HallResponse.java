package moviestreamingservice.domain.hall.dto;

public record HallResponse(Long id, int hallNumber, int totalSeats, Long cinemaId) {
}
