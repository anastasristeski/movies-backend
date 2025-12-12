package moviestreamingservice.domain.city.dto;

import moviestreamingservice.domain.cinema.dto.CinemaResponse;

import java.util.List;

public record CityResponse(Long id, String name, int cinemaCount) {
}
