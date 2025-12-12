package moviestreamingservice.domain.city.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.cinema.dto.CinemaResponse;
import moviestreamingservice.domain.city.CityService;
import moviestreamingservice.domain.city.dto.CityResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/city")
@RequiredArgsConstructor
public class CityPublicController {
    private final CityService cityService;
    @GetMapping
    public ResponseEntity<List<CityResponse>> getAllCities(){
        return ResponseEntity.ok(cityService.getAllCities());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getCity(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(cityService.getCityById(id));
    }
    @GetMapping("/{cityId}/cinemas")
    public ResponseEntity<List<CinemaResponse>> getCinemas(@PathVariable @Positive Long cityId) {
        return ResponseEntity.ok(cityService.getCinemasByCity(cityId));
    }

}
