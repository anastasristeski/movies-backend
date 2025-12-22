package moviestreamingservice.domain.city.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.city.CityService;
import moviestreamingservice.domain.city.dto.CityResponse;
import moviestreamingservice.domain.city.dto.CityRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/city")
public class CityAdminController {
    private final CityService cityService;
    @GetMapping
    public ResponseEntity<List<CityResponse>> getAllCities(){
        return ResponseEntity.ok(cityService.getAllCities());
    }
    @PostMapping
    public ResponseEntity<CityResponse> createCity(@Valid @RequestBody CityRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cityService.createCity(request));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CityResponse> updateCity(@PathVariable @Positive Long id, @Valid @RequestBody CityRequest request){
        return ResponseEntity.ok(cityService.updateCity(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}
